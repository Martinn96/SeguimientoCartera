package seguimientoCartera.movimientos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MovimientoService {
	
	MovimientoRepositoryLocal repo;
	
	public MovimientoService(MovimientoRepositoryLocal repo) {
		this.repo = repo;
	}

	public Movimiento save(Movimiento movimiento) {
		return repo.save(movimiento);
	}
	
	public Optional<Movimiento> get(Long id) {
		return repo.get(id);
	}
	
	public Optional<Movimiento> delete(Long id) {
		return repo.delete(id);
	}
	
	public List<Movimiento> listAll() {
		return repo.listAll();
	}
}
