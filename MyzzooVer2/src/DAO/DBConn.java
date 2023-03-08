package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("DB로딩 에러발생"+e.getMessage());
		}
	}
	
	String sql = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://222.119.100.81:3382/foxy";
	String username = "foxy";
	String userpwd = "gksehdwls";
	
	public DBConn() {}
	
	public void getConn() {
		try {
			conn = DriverManager.getConnection(url,username,userpwd);
		} catch(Exception e) {
			System.out.println("DB연결 에러발생"+e.getMessage());
		}
	}
	
	public void dbClose() {
		try {
			if(rs != null)		rs.close();
			if(pstmt != null)	pstmt.close();
			if(conn != null)	conn.close();
		} catch (Exception e) {
			System.out.println("DB종료가 실패하였습니다."+e.getMessage());
		}
	}
}
