package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import Model.HoldingStockVO;
import Service.Crawler;

public class HoldingDAO extends DBConn {
	public HoldingDAO() {}
	
	public ArrayList<HoldingStockVO> select(String userId) throws SQLException, NumberFormatException, ParseException {
		getConn();
		ArrayList<HoldingStockVO> list = new ArrayList<HoldingStockVO>();
		
		sql = "select c.CompanyCode, c.CompanyName, s.holding_quantity, s.Purchase_price "
				+ "from stockHolding s left outer join CompanyTable c "
				+ "on c.CompanyCode = s.companyCode "
				+ "where s.user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		
		//list에 추가
		while(rs.next())
			list.add(getVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		
		rs.close();
		pstmt.close();
		conn.close();
		dbClose();
		
		return list;
	}
	
	HoldingStockVO getVO(String code, String name, String quantity, String Purchase) throws NumberFormatException, ParseException {
		HoldingStockVO m = new HoldingStockVO();
		
		//현재가
		String currentStr = new Crawler(code).currentPrice();
		int current = Integer.parseInt(currentStr.replace(",", ""));
		//보유수량 int형
		int stockQuantity = Integer.parseInt(quantity);
		//평균 매입가 int형
		int stockPrice = Integer.parseInt(Purchase);
		//평가금액 = 현재가*수량
		int marketPrice = current * stockQuantity;
		//매입가 = 매입단가*수량
		int buyPrice = stockPrice * stockQuantity;
		//평가손익=평가금액-매입가
		int gainloss = marketPrice - buyPrice;
		//손익률=(현재가 - 평균매입가)/평균매입가
		double incomeRate = 100 * (current - stockPrice) / (double)stockPrice;
		
		m.setStockName(name);
		m.setStockGainloss((gainloss > 0 ? "+" : "") + String.format("%,d", gainloss));
		m.setStockIncomeRate(String.format("%.2f", incomeRate) + "%");
		m.setStockQuantity(String.format("%,d", stockQuantity));
		m.setStockMarketPrice(String.format("%,d", marketPrice));
		m.setStockPurchasePrice(String.format("%,d", stockPrice));
		m.setStockCurrentPrice(currentStr);
		
		return m;
	}
	
	
}
