package seguimientoCartera.stockApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import seguimientoCartera.stats.Asset;
import seguimientoCartera.stats.Assetdto;

@Service
public class ApiClientService {
	
	@Autowired
	private RestTemplate restTemplate;

	public Asset getPrice(Asset asset) {
		final String uri = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&apikey=WK909V4BCCSAFCFH&symbol=";
		Assetdto assetdto = restTemplate.getForObject(uri + asset.getSymbol(), Assetdto.Root.class).asset;
		asset.setPrice(Double.valueOf(assetdto.getPrice()));
		return asset;
	}
	
}
