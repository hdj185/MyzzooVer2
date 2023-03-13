package DAO;

import java.sql.SQLException;

public class TradingDAO extends DBConn {
	String code = "";
	
	public TradingDAO() {}
	public TradingDAO(String name) {
		setCode(name);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String name) {
		try {
			getConn();
			sql = "select CompanyCode from CompanyTable where CompanyName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) code = rs.getString(1);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			dbClose();
		}
	}
	
	public int getQuantity(String id) {
		int quantity = 0;
		
		try {
			getConn();
			//holdingstock에서 id, code 일치하는 것중에서 quantity만 출력
			sql = "select holding_quantity from stockHolding where companyCode=? and user_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) quantity = Integer.parseInt(rs.getString(1));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			dbClose();
		}
		
		return quantity;
	}
}
