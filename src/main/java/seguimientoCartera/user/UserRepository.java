package seguimientoCartera.user;
import java.util.Optional;

public interface UserRepository {
	Usuario save(Usuario u);
	Optional<Usuario> get(Long id);
	Optional<Usuario> getId(Usuario u);
	Optional<Usuario> delete(Long id);
	Optional<Usuario> modify(Usuario u, String passNew);
}
