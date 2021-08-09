package seguimientoCartera.stats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import seguimientoCartera.movimientos.Movimiento;
import seguimientoCartera.movimientos.MovimientoRepositoryLocal;
import seguimientoCartera.stockApi.ApiClientService;

@Service
public class PortafolioService {
	
	MovimientoRepositoryLocal repo;
	ApiClientService apiService;
	
	public PortafolioService(MovimientoRepositoryLocal repo, ApiClientService apiService) {
		this.repo = repo;
		this.apiService = apiService;
	}



	public List<Asset> holdings(){
			List<Movimiento> historico = repo.listAll();
			List<String> allTickets = new ArrayList<>();
			List<Asset> assets = new ArrayList<>();
			for (Movimiento movimiento : historico) {
				if(allTickets.isEmpty() || !allTickets.contains(movimiento.getTicket())) 
					allTickets.add(movimiento.getTicket());
			} 
			for (String ticket : allTickets) {
				Asset asset = new Asset(ticket, 0, 0);
				for (Movimiento movimiento : historico) {
					if(movimiento.getTicket().equals(ticket)) {
						int temp = asset.getCantidad();
						asset.setCantidad( temp + movimiento.getCantidad());
					}
				}
				asset = apiService.getPrice(asset);
				assets.add(asset);
			}
		return assets;
	}
}
