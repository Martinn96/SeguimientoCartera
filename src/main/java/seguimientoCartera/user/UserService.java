package seguimientoCartera.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seguimientoCartera.config.SessionInfo;

@Service
public class UserService {
	UserRepositoryLocal repo;

	@Autowired
	private SessionInfo sessionInfo;
	
	public UserService(UserRepositoryLocal repo) {
		this.repo = repo;
	}
	
	public boolean login(Usuario u) {
		Optional<Usuario> user = repo.getId(u);
		return user.isPresent();
	}
	
	public boolean save(Usuario u) {
		return repo.save(u) != null; 
	}
	
	public Optional<Usuario> modify(Usuario u, String passNew) {
		return repo.modify(u, passNew);
	}
	
	public Optional<Usuario> get(Long id) {
		return repo.get(id);
	}
	
	public Optional<Usuario> getId(Usuario user) {
		return repo.getId(user);
	}
	
	public Optional<Usuario> delete(Long id) {
		return repo.delete(id);
	}
	
	public SessionInfo getSession() {
		return sessionInfo;
	}
}
