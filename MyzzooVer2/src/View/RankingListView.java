package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.MarketDAO;
import Model.MarketVO;


public class RankingListView {
	
	static JPanel listPane;
	static String userId; //유저 id
	
	static JLabel titleLabel;
	static JLabel timeLabel;
	static JButton RefreshBtn;
	static JButton logoutBtn;
	static JScrollPane scrollpane;
	static JTable table;

	RankingListView() {
		listPane = new JPanel();
	}
	
	RankingListView(String userId) {
		this.userId = userId;
		listPane = new JPanel();
	}
	
	public static JPanel createUI() {
		// 타이틀
		titleLabel = new JLabel("《   종목 리스트   》");
		titleLabel.setBounds(340, 50, 310, 75);
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		
		// 기준 시간
		timeLabel = new JLabel();
		timeLabel.setBounds(45, 180, 200, 15);
		timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		
		
		// 새로고침 - 버튼 클릭하면 새로고침
		RefreshBtn = new JButton("새로고침");
		RefreshBtn.setBounds(750, 30, 90, 25);
		RefreshBtn.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		RefreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		
		// 로그아웃 - 버튼 클릭하면 처음 로그인 페이지로 이동
		logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(850, 30, 90, 25);
		logoutBtn.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuView.frame.dispose();
				new LoginView();
			}
		});

		//테이블 생성
		table = getTable();
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(45, 200, 900, 500);
		
		
		listPane.setLayout(null);
		listPane.add(titleLabel);
		listPane.add(timeLabel);
		listPane.add(RefreshBtn);
		listPane.add(logoutBtn);
		listPane.add(scrollpane); // 보유주식 테이블
		
		return listPane;
	}
	
	//테이블 작성
	public static JTable getTable() {
		JTable table = new JTable(getContent());
		table.setRowHeight(25);	//셀 높이 조정
		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
        table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		return table;
	}
	

	//테이블 model 생성
	public static DefaultTableModel getContent() {
		String[] header = {"종목명", "종목코드", "현재가", "전일가", "시가", "고가", "상한가", "저가", "하한가", "시가총액"};
		DefaultTableModel model = new DefaultTableModel(header, 0) {// table 수정 금지시키기
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		
		//기준 시간 넣기
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		timeLabel.setText("기준 시간 : " + timestamp);
		
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
			
			//시가총액 조, 억 단위 붙이기
			String total = vo.getMarketTotal();
			int totalLen = total.length();
			String stockPrice = (totalLen > 3 ?
					total.substring(0, totalLen - 3) + "조 " + total.substring(totalLen - 3) :
						total) + "억";
			
			record[0] = vo.getMarketSectorName();
			record[1] = vo.getMarketCode();
			record[2] = vo.getMarketCurrent();
			record[3] = vo.getMarketPre();
			record[4] = vo.getMarketOpen();
			record[5] = vo.getMarketHigh();
			record[6] = vo.getMarketUpper();
			record[7] = vo.getMarketLow();
			record[8] = vo.getMarketLower();
			record[9] = stockPrice;
			
			model.addRow(record);
		}
		
		return model;
	}// getContent end
	
	//refresh 버튼
	static void refresh() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);			//table의 model 0으로
		table.setModel(getContent());	//table의 model 넣어줌
	}
	
}
