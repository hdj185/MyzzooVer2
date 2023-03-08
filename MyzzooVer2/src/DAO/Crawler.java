package DAO;

import java.io.IOException;
import java.text.ParseException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import Model.MarketVO;

public class Crawler {
	static String code = "005930";
	Elements todaylist;

	public Crawler() throws ParseException {
		this(code);
	};

	//크롤러
	public Crawler(String code) throws ParseException { //들어온 코드로 크롤링
		this.code = code;
		String URL = "https://finance.naver.com/item/main.naver?code=" + code;
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			todaylist = doc.select(".new_totalinfo dl>dd"); //데이터 받아오기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//주식 순위 vo에 크롤링한 데이터 넣기
	public MarketVO getData(MarketVO data) {
		MarketVO vo = data;
		
		vo.setMarketCurrent(valueSplit(3));
		vo.setMarketPre(valueSplit(4));
		vo.setMarketOpen(valueSplit(5));
		vo.setMarketHigh(valueSplit(6));
		vo.setMarketUpper(valueSplit(7));
		vo.setMarketLow(valueSplit(8));
		vo.setMarketLower(valueSplit(9));
		
		return vo;
	}
	
	//현재가 구하기
	public String currentPrice() {
		return valueSplit(3);
	}
	
	//크롤링한 값을 추출하는 메소드 
	public String valueSplit(int i) {
		String str = "";
		try {
			str = todaylist.get(i).text().split(" ")[1];
		} catch (Exception e) {
			str = "error";
			e.printStackTrace();
		}
		return str;
	}
	
	
}//class end
