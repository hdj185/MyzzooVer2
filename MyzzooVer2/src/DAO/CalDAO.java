package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import Service.Crawler;


public class CalDAO extends DBConn  {
	
	// 총 평가 금액 
	public int totalStock(String id) throws SQLException, ParseException {
		getConn();
		int sum = 0;
	// id 부분에 user id를 입력 한다
	// 해당 유저의 모든 평가 금액( 현재가 * 수량 )을 받아오고 , <- 쉼펴를 제거한뒤, 모든 값들을 더한다
		
		sql = "select companyCode, holding_quantity "
				+ "from stockHolding "
				+ "where user_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int current = Integer.parseInt(new Crawler(rs.getString(1)).currentPrice().replace(",",  ""));
			int quantity = Integer.parseInt(rs.getString(2));
			sum += current * quantity;
		}
		
		dbClose();
		return sum;
	}
	
	
// 	총 매입 금액   ( 평균 매입 금액 * 보유수량  )		//String type 매개변수
	public String totalAvg(String id) {
		String str=null;
		String sql = "select sum(holding_quantity*Purchase_price) as sum from stockHolding " + 
				"where user_id = ? " + 
				"group by user_id";  
		try {
			getConn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())   str = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose(); //db close
		}

		return str;  // 반환
	}
	
	
	// 손익률
		public Double profitRate(String id) throws NumberFormatException, SQLException, ParseException {

			return (double)Math.round( totalStock(id) / Double.parseDouble(totalAvg(id)) * 100.0 * 100.0  - 100.0 * 100.0) / 100.0;
		}
		
		
		// 손익 계산
		public int profit(String id) throws NumberFormatException, SQLException, ParseException {
			return totalStock(id) - Integer.parseInt(totalAvg(id));
		}
	
}
