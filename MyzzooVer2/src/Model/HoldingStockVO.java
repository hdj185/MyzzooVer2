package Model;

public class HoldingStockVO {
	//종목명, 평가손익, 수익률, 보유수량, 평가금액, 평균매입가, 현재가, 시가 총액 순서
	String StockName;
	String StockGainloss;
	String StockIncomeRate;
	String StockQuantity;
	String StockMarketPrice;
	String StockPurchasePrice;
	String StockCurrentPrice;
	
	
	public HoldingStockVO() {
	}


	public HoldingStockVO(String stockName, String stockGainloss, String stockIncomeRate, String stockQuantity,
			String stockMarketPrice, String stockPurchasePrice, String stockCurrentPrice) {
		super();
		StockName = stockName;
		StockGainloss = stockGainloss;
		StockIncomeRate = stockIncomeRate;
		StockQuantity = stockQuantity;
		StockMarketPrice = stockMarketPrice;
		StockPurchasePrice = stockPurchasePrice;
		StockCurrentPrice = stockCurrentPrice;
	}


	public String getStockName() {
		return StockName;
	}


	public void setStockName(String stockName) {
		StockName = stockName;
	}


	public String getStockGainloss() {
		return StockGainloss;
	}


	public void setStockGainloss(String stockGainloss) {
		StockGainloss = stockGainloss;
	}


	public String getStockIncomeRate() {
		return StockIncomeRate;
	}


	public void setStockIncomeRate(String stockIncomeRate) {
		StockIncomeRate = stockIncomeRate;
	}


	public String getStockQuantity() {
		return StockQuantity;
	}


	public void setStockQuantity(String stockQuantity) {
		StockQuantity = stockQuantity;
	}


	public String getStockMarketPrice() {
		return StockMarketPrice;
	}


	public void setStockMarketPrice(String stockMarketPrice) {
		StockMarketPrice = stockMarketPrice;
	}


	public String getStockPurchasePrice() {
		return StockPurchasePrice;
	}


	public void setStockPurchasePrice(String stockPurchasePrice) {
		StockPurchasePrice = stockPurchasePrice;
	}


	public String getStockCurrentPrice() {
		return StockCurrentPrice;
	}


	public void setStockCurrentPrice(String stockCurrentPrice) {
		StockCurrentPrice = stockCurrentPrice;
	}
	
	

}
