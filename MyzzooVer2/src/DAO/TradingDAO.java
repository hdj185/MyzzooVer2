package DAO;

import java.sql.SQLException;

import View.MenuView;

public class TradingDAO extends DBConn {
	String code = "";
	
	public TradingDAO() {}
	public TradingDAO(String name) {
		setCode(name);
	}
	
	public String getCode() {
		return code;
	}
	
	//코드 구하기
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

	
	//holdingstock에서 id, code 일치하는 것중에서 quantity만 출력
	public int getQuantity() {
		int quantity = 0;
		
		try {
			getConn();
			sql = "select holding_quantity from stockHolding where companyCode=? and user_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, MenuView.userId);
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
	
	
	//holdingstock에서 id, code 일치하는 것중에서 purchase만 출력
	public int getPurchase() {
		int purchase = 0;
		
		try {
			getConn();
			sql = "select Purchase_price from stockHolding where companyCode=? and user_id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, MenuView.userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) purchase = Integer.parseInt(rs.getString(1));
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			dbClose();
		}
		
		return purchase;
	}
	
	
	//insert문 - 새로운 주식 매입
	public void insertStock(String quantity, String price) {
		try {
			getConn();
			sql = "insert into stockHolding"
					+ " (user_id, companyCode, holding_quantity, StocksSell, Purchase_price)"
					+ " values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MenuView.userId);
			pstmt.setString(2, code);
			pstmt.setString(3, quantity);
			pstmt.setString(4, quantity);
			pstmt.setString(5, price);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	
	//update문 - 보유 주식 매입, 부분 매도 시
	public void updateStock(String quantity, String price) {
		try {
			getConn();
			sql = "update stockHolding set holding_quantity=?, purchase_price=? where user_id=? and companyCode=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, quantity);
			pstmt.setString(2, price);
			pstmt.setString(3, MenuView.userId);
			pstmt.setString(4, code);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	
	//delete문 - 보유 주식 전체 매도 시
	public void deleteStock() {
		try {
			getConn();
			sql = "delete from stockHolding where user_id = ? and companyCode = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MenuView.userId);
			pstmt.setString(2, code);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	
	//존재 여부 확인
	public Boolean existsStock() {
		Boolean result = false;
		
		try {
			getConn();
			sql = "select exists(select 1 from stockHolding where user_id=? and companyCode=?);";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MenuView.userId);
			pstmt.setString(2, code);

			rs = pstmt.executeQuery();
			
			if(rs.next()) result = rs.getString(1).equals("1") ? true : false;

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
}
