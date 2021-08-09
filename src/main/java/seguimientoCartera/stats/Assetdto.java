package seguimientoCartera.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Assetdto{
    @JsonProperty("01. symbol") 
    public String Symbol;
    @JsonProperty("02. open") 
    public String Open;
    @JsonProperty("03. high") 
    public String High;
    @JsonProperty("04. low") 
    public String Low;
    @JsonProperty("05. price") 
    public String Price;
    @JsonProperty("06. volume") 
    public String Volume;
    @JsonProperty("07. latest trading day") 
    public String LatestTradingDay;
    @JsonProperty("08. previous close") 
    public String PreviousClose;
    @JsonProperty("09. change") 
    public String Change;
    @JsonProperty("10. change percent") 
    public String ChangePercent;
    
    public static class Root{
        @JsonProperty("Global Quote") 
        public Assetdto asset;
    }

}

