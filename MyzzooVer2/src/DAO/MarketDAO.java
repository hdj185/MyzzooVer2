package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import Model.MarketVO;

public class MarketDAO extends DBConn {
	public MarketDAO() {}
	
	public ArrayList<MarketVO> select() throws SQLException {
		getConn();
		ArrayList<MarketVO> list = new ArrayList<MarketVO>();
		sql = "select CompanyName, CompanyCode, CompanyTotal from CompanyTable ORDER BY cast(CompanyTotal as unsigned) DESC";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MarketVO m = new MarketVO();
			
			m.setMarketSectorName(rs.getString(1));
			m.setMarketCode(rs.getString(2));
			m.setMarketTotal(rs.getString(3));
			
			try {
				m = new Crawler(m.getMarketCode()).getData(m);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			list.add(m);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		dbClose();
		
		return list;
	}
}
