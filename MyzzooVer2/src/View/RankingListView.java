package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.MarketDAO;
import Model.MarketVO;


public class RankingListView extends ListView {
	static JTable table;
	static JLabel timeLabel;
	
	RankingListView() {
		super();
	}
	
	
	public static JPanel createUI() {
		init();

		titleLabel.setText("《 시가 총액 순위 》");				// 타이틀 설정

		// 기준 시간
		timeLabel = new JLabel("기준 시간 : ");
		timeLabel.setBounds(45, 180, 200, 15);
		timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		setTimeLabel(timeLabel); //시간 갱신
		listPane.add(timeLabel);
		
		//보유주식 table 생성
		table = getTable();
		table.setModel(getContent());						// table 내용 넣기
		table.setDefaultRenderer(Object.class, new MyColorRender(1)); //컬러 지정
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(45, 200, 900, 500); // scrollpane 크기 및 위치
		listPane.add(scrollpane);
		
		//새로고침 버튼 이벤트
		RefreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		return listPane;
	}
	
	
	//테이블 model 생성
	public static DefaultTableModel getContent() {
		String[] header = {"종목명", "현재가", "전일가", "시가", "고가", "상한가", "저가", "하한가", "시가총액"};
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
			record[1] = vo.getMarketCurrent();
			record[2] = vo.getMarketPre();
			record[3] = vo.getMarketOpen();
			record[4] = vo.getMarketHigh();
			record[5] = vo.getMarketUpper();
			record[6] = vo.getMarketLow();
			record[7] = vo.getMarketLower();
			record[8] = marketCap(vo.getMarketTotal());
			
			model.addRow(record);
		}

		return model;
	}// getContent end

	
	//시가총액 조, 억 단위 붙이기
	static String marketCap(String total) {
		String result = "";
		int len = total.length();
		DecimalFormat df = new DecimalFormat("#,###");
		
		if(len > 4) {
			result = total.substring(0, len - 4) + "조";	//앞자리
			int lastDigit = Integer.parseInt(total.substring(len - 4)); //조 뒷자리 숫자 int형
			if (lastDigit > 0) result += " " + df.format(lastDigit) + "억";
		} else {
			result = df.format(Integer.parseInt(total)) + "억";
		}
		
		return result;
	}//marketCap end
	
	
	//새로고침 버튼
	static void refresh() {
		resetModel(table);			// 테이블 지우기
		setTimeLabel(timeLabel);			// 시간 갱신
		table.setModel(getContent()); //table의 model 넣어줌
	}
}//class end
