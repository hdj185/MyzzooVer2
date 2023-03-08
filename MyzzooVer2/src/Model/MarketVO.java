package Model;

public class MarketVO {
	String marketSectorName;	//종목명
	String marketCode;			//종목코드
	String marketCurrent;		//현재가
	String marketPre; 			//전일가
	String marketOpen;			//시가
	String marketHigh;			//고가
	String marketUpper;			//상한가
	String marketLow;			//저가
	String marketLower;			//하한가
	String marketTotal;			//시가총액
	
	
	public MarketVO() {}
	public MarketVO(String marketSectorName, String marketCode, String marketCurrent, String marketPre,
			String marketOpen, String marketHigh, String marketUpper, String marketLow, String marketLower,
			String marketTotal) {
		super();
		this.marketSectorName = marketSectorName;
		this.marketCode = marketCode;
		this.marketCurrent = marketCurrent;
		this.marketPre = marketPre;
		this.marketOpen = marketOpen;
		this.marketHigh = marketHigh;
		this.marketUpper = marketUpper;
		this.marketLow = marketLow;
		this.marketLower = marketLower;
		this.marketTotal = marketTotal;
	}
	
	public String getMarketSectorName() {
		return marketSectorName;
	}
	public void setMarketSectorName(String marketSectorName) {
		this.marketSectorName = marketSectorName;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getMarketCurrent() {
		return marketCurrent;
	}
	public void setMarketCurrent(String marketCurrent) {
		this.marketCurrent = marketCurrent;
	}
	public String getMarketPre() {
		return marketPre;
	}
	public void setMarketPre(String marketPre) {
		this.marketPre = marketPre;
	}
	public String getMarketOpen() {
		return marketOpen;
	}
	public void setMarketOpen(String marketOpen) {
		this.marketOpen = marketOpen;
	}
	public String getMarketHigh() {
		return marketHigh;
	}
	public void setMarketHigh(String marketHigh) {
		this.marketHigh = marketHigh;
	}
	public String getMarketUpper() {
		return marketUpper;
	}
	public void setMarketUpper(String marketUpper) {
		this.marketUpper = marketUpper;
	}
	public String getMarketLow() {
		return marketLow;
	}
	public void setMarketLow(String marketLow) {
		this.marketLow = marketLow;
	}
	public String getMarketLower() {
		return marketLower;
	}
	public void setMarketLower(String marketLower) {
		this.marketLower = marketLower;
	}
	public String getMarketTotal() {
		return marketTotal;
	}
	public void setMarketTotal(String marketTotal) {
		this.marketTotal = marketTotal;
	}
	
	@Override
	public String toString() {
		return "RankingListVO [marketSectorName=" + marketSectorName + ", marketCode=" + marketCode + ", marketCurrent="
				+ marketCurrent + ", marketPre=" + marketPre + ", marketOpen=" + marketOpen + ", marketHigh="
				+ marketHigh + ", marketUpper=" + marketUpper + ", marketLow=" + marketLow + ", marketLower="
				+ marketLower + ", marketTotal=" + marketTotal + "]";
	}
	
}
