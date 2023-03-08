package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import DAO.MarketDAO;
import Model.MarketVO;


public class RankingListView extends ListView {
	RankingListView() {
		super();
	}
	
	RankingListView(String userId) {
		super(userId);
	}
	
	public static JPanel createUI() {
		init();

		titleLabel.setText("《 시가 총액 순위 》");	// 타이틀 설정
		setTimeLabel(); 									//시간 갱신
		table.setModel(getContent());				// table 내용 넣기
		scrollpane.setBounds(45, 200, 900, 500); //scrollpane 크기 및 위치
		
		//새로고침 버튼 이벤트
		RefreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh(table);			// 테이블 지우기
				setTimeLabel();			// 시간 갱신
				table.setModel(getContent()); //table의 model 넣어줌				
			}
		});
		
		return listPane;
	}
	
	//테이블 model 생성
	public static DefaultTableModel getContent() {
		String[] header = {"종목명", "종목코드", "현재가", "전일가", "시가", "고가", "상한가", "저가", "하한가", "시가총액"};
		DefaultTableModel model = createModel(header);
		
		//데이터 받기
		ArrayList<MarketVO> list = null;
		try {
			list = new MarketDAO().select();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Object record[] = new Object[header.length];
		
		for (int i = 0; i < list.size(); i++) {
			MarketVO vo = list.get(i);
			
			record[0] = vo.getMarketSectorName();
			record[1] = vo.getMarketCode();
			record[2] = vo.getMarketCurrent();
			record[3] = vo.getMarketPre();
			record[4] = vo.getMarketOpen();
			record[5] = vo.getMarketHigh();
			record[6] = vo.getMarketUpper();
			record[7] = vo.getMarketLow();
			record[8] = vo.getMarketLower();
			record[9] = marketCap(vo.getMarketTotal());
			
			model.addRow(record);
		}

		return model;
	}// getContent end

	//시가총액 조, 억 단위 붙이기
	static String marketCap(String total) {
		int totalLen = total.length();
		String stockPrice;
		
		if(totalLen > 3) {
			stockPrice = total.substring(0, totalLen - 3) + "조";
			if (!total.substring(totalLen - 3).equals("000"))
				stockPrice += " " + total.substring(totalLen - 3) + "억";
		} else {
			stockPrice = total + "억";
		}
		
		return stockPrice;
	}//marketCap end
	
}//class end
