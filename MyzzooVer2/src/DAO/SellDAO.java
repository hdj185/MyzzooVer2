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
}
