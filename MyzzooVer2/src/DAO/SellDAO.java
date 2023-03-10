package DAO;

import java.sql.SQLException;

public class SellDAO extends DBConn {
	public SellDAO() {}
	
	public String getCode(String name) {
		String code = "";
		
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
		
		return code;
	}
	
	public String getQuantity(String name) {
		String code = "";
		
		try {
			getConn();
			//holdingstock에서 id, code 일치하는 것중에서 quantity만 출력
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
		
		return code;
	}
}
