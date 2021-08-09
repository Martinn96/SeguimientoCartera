package seguimientoCartera.movimientos;

import java.util.List;
import java.util.Optional;

public interface MovimientoRepository {
	Movimiento save(Movimiento m);
	Optional<Movimiento> get(Long id);
	Optional<Movimiento> delete(Long id);
	List<Movimiento> listAll();
}
