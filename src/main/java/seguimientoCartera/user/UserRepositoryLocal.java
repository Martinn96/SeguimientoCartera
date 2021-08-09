package seguimientoCartera.user;

import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryLocal implements UserRepository {

	NamedParameterJdbcTemplate namedTemplate;

	UserRepositoryLocal(DataSource ds) {
		namedTemplate = new NamedParameterJdbcTemplate(ds);
	}

	@Override
	public Usuario save(Usuario u) {
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			namedTemplate.update("insert into usuario (username, password, mail) values(:username, :password, :mail)",
					new MapSqlParameterSource("username", u.getUsername()).addValue("password", u.getPassword()).addValue("mail",
							u.getMail()),
					holder, new String[] { "id" });
			Long idGenerated = holder.getKey().longValue();

			u = get(idGenerated).get();

			return u;
		} catch (DuplicateKeyException e) {
			return null;
		}

	}

	@Override
	public Optional<Usuario> get(Long id) {
		try {
			Usuario u = namedTemplate.queryForObject("Select * from usuario where id = :id",
					new MapSqlParameterSource("id", id), new BeanPropertyRowMapper<Usuario>(Usuario.class));
			return Optional.of(u);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Usuario> getId(Usuario u) {
		try {
			u = namedTemplate.queryForObject("Select * from usuario where username =:username AND password = :password;",
					new MapSqlParameterSource("username", u.getUsername()).addValue("password", u.getPassword()),
					new BeanPropertyRowMapper<Usuario>(Usuario.class));
			return Optional.of(u);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Usuario> delete(Long id) {
		Optional<Usuario> u = get(id);
		if (u.isPresent())
			namedTemplate.update("DELETE FROM usuario WHERE id = :id", new MapSqlParameterSource("id", id));
		return u;
	}

	@Override
	public Optional<Usuario> modify(Usuario u, String passNew) {

		Optional<Usuario> uConId = getId(u);
		if (uConId.isPresent()) {
			u.setId(uConId.get().getId());
			namedTemplate.update("Update usuario SET password = :passnew WHERE username = :username AND password = :passold ;",
					new MapSqlParameterSource("passnew", passNew).addValue("username", u.getUsername())
							.addValue("passold", u.getPassword()));
			Optional<Usuario> uTemp = get(u.getId());
			return uTemp;
		} else {
			return null;
		}
	}

}
