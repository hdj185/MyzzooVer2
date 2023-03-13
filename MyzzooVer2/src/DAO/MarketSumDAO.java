package DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MarketSumDAO extends DBConn {
	
	public MarketSumDAO() {
		getCode();
	}
	
	//DB의 CompanyTable의 CompanyCode를 읽어들이는 메소드
	public void getCode() {
		try {
			getConn();
			sql = "select CompanyCode from CompanyTable";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				crawler(rs.getString(1));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	//들어온 코드로 크롤링, 시가총액 받기
	public Elements crawler(String code) {
		Elements list = null;
		String URL = "https://finance.naver.com/item/main.naver?code=" + code;
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			list = doc.select("#_market_sum"); //데이터 받아오기
			String totalStr = list.get(0).text().replace(",", "").replace("조 ", "");
			setMarketSum(totalStr, code);
			System.out.println(code + " DB update 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//시가총액 DB에 넣기
	public void setMarketSum(String total, String code) {
		try {
			String sqlup = "update CompanyTable set CompanyTotal=? where CompanyCode=?";
			
			PreparedStatement pstmt2 = conn.prepareStatement(sqlup);
			pstmt2.setString(1, total);
			pstmt2.setString(2, code);
			pstmt2.executeUpdate();
			pstmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
