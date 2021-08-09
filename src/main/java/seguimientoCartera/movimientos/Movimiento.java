package seguimientoCartera.movimientos;

import java.util.Date;

import lombok.Data;

@Data
public class Movimiento {
	private Long id;
	private Long userId;
	private double valor;
	private int cantidad;
	private Date fecha;
	private String ticket;
}
