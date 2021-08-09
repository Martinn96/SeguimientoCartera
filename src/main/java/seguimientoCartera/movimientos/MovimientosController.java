package seguimientoCartera.movimientos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import seguimientoCartera.stats.Asset;
import seguimientoCartera.stats.PortafolioService;

@RestController
@RequestMapping("/operaciones")
public class MovimientosController {

	MovimientoService moviService;
	PortafolioService portaService;

	public MovimientosController(MovimientoService moviService, PortafolioService portaService) {
		this.moviService = moviService;
		this.portaService = portaService;
	}

	@PostMapping
	public ResponseEntity<String> add(@RequestBody Movimiento movimiento) {
		Long id = moviService.save(movimiento).getId();
		return ResponseEntity.status(HttpStatus.CREATED).body("movimiento añadido, id:" + id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> remove(@PathVariable Long id) throws NotFoundException {

		Optional<Movimiento> m = moviService.delete(id);
		if (m.isPresent())
			return ResponseEntity.status(HttpStatus.GONE)
					.body(String.format("se ha borrado el el movimiento id: %d ticket: %s cantidades: %s fecha: %s ",
							m.get().getId(), m.get().getTicket(), m.get().getCantidad(), m.get().getFecha()));
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}

	@GetMapping("/{id}")
	public Movimiento get(@PathVariable Long id) throws NotFoundException {
		Optional<Movimiento> m = moviService.get(id);
		if (m.isPresent())
			return m.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");

	}

	@GetMapping("/tenencias")
	public List<Asset> tenencias() {
		return portaService.holdings();

	}

	@GetMapping()
	public List<Movimiento> list() {
		return moviService.listAll();
	}
}
