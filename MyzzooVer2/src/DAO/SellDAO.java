package DAO;

import java.sql.SQLException;

public class SellDAO extends DBConn {
	public SellDAO() {}
	
	public String getName(String code) {
		String name = "";
		
		try {
			getConn();
			sql = "select CompanyName from CompanyTable where CompanyCode = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) name = rs.getString(1);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			dbClose();
		}
		
		return name;
	}
}
