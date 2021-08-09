package seguimientoCartera.movimientos;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import seguimientoCartera.user.UserService;

@Repository
public class MovimientoRepositoryLocal implements MovimientoRepository {

	NamedParameterJdbcTemplate namedTemplate;
	UserService userService;
	
	MovimientoRepositoryLocal(DataSource ds,UserService userService) {
		namedTemplate = new NamedParameterJdbcTemplate(ds);
		this.userService = userService;
	}

	@Override
	public Movimiento save(Movimiento m) {

		KeyHolder holder = new GeneratedKeyHolder();
		namedTemplate.update("insert into movimiento (userId, ticket, valor, cantidad) values(:userId, :ticket, :valor, :cantidad) ",
				new MapSqlParameterSource("ticket", m.getTicket()).addValue("valor", m.getValor()).addValue("cantidad",
						m.getCantidad()).addValue("userId",userService.getSession().getUsuario().getId()),
				holder, new String[] { "id" });
		Long idGenerated = holder.getKey().longValue();

		m = get(idGenerated).get();

		return m;
	}

	@Override
	public Optional<Movimiento> get(Long id) {
		try {
			Movimiento m = namedTemplate.queryForObject("Select * from movimiento where id = :id AND userId = :userId",
					new MapSqlParameterSource("id", id).addValue("userId", userService.getSession().getUsuario().getId()), new BeanPropertyRowMapper<Movimiento>(Movimiento.class));
			return Optional.of(m);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Movimiento> delete(Long id) {
		Optional<Movimiento> m = get(id);
		if(m.isPresent())
			namedTemplate.update("DELETE FROM Movimiento WHERE id = :id AND userId = :userId", new MapSqlParameterSource("id", id).addValue("userId", userService.getSession().getUsuario().getId()));
		return m;
	}

	@Override
	public List<Movimiento> listAll() {
		return namedTemplate.query("Select * from movimiento WHERE userId = :userId", new MapSqlParameterSource("userId", userService.getSession().getUsuario().getId()) , new BeanPropertyRowMapper<Movimiento>(Movimiento.class));
	}

	

}
