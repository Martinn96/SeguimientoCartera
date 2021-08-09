package seguimientoCartera.stats;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Asset {
	private String symbol;
	private int cantidad;
	private double price;
}
