package seguimientoCartera.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

//TODO: xd
@Repository
public class UserRepositoryMysqlLocal implements UserRepository {

	@Override
	public Usuario save(Usuario u) {
		return null;
	}

	@Override
	public Optional<Usuario> get(Long id) {
		return null;
	}

	@Override
	public Optional<Usuario> getId(Usuario u) {
		return null;
	}

	@Override
	public Optional<Usuario> delete(Long id) {
		return null;
	}

	@Override
	public Optional<Usuario> modify(Usuario u, String passNew) {
		return null;
	}

}
