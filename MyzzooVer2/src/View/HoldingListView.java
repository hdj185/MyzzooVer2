package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.CalDAO;
import DAO.HoldingDAO;
import Model.HoldingStockVO;


public class HoldingListView extends ListView {
	static JTable table;
	static JTable calTable;
	static JScrollPane calPane;
	static JLabel timeLabel;
	static JButton sellBtn;
	
	HoldingListView() {
		super();
	}
	
	HoldingListView(String userId) {
		super(userId);
	}
	
	
	
	public static JPanel createUI() {
		init();

		titleLabel.setText("《 보유 주식 잔고 》");	// 타이틀 설정
		
		// 기준 시간
		timeLabel = new JLabel("기준 시간 : ");
		timeLabel.setBounds(45, 180, 200, 15);
		timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		setTimeLabel(timeLabel); //시간 갱신
		listPane.add(timeLabel);
		
		//보유주식 table 생성
		table = getTable();
		table.setModel(getContent());				// table 내용 넣기
		table.setDefaultRenderer(Object.class, new MyColorRender(2)); //컬러 지정
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(45, 245, 900, 455); //scrollpane 크기 및 위치
		listPane.add(scrollpane);
		
		//calTable 생성
		calTable = getTable();
		calTable.setModel(getCalContent());
		calTable.setDefaultRenderer(Object.class, new MyColorRender(0)); //서식 지정
		calPane = new JScrollPane(calTable);
		calPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); //스크롤바 없애기
		calPane.setBounds(45, 200, 900, 45);
		listPane.add(calPane);
		
		
		//매도 버튼 생성
		sellBtn = new JButton("매도");
		sellBtn.setBounds(850, 710, 90, 25);
		sellBtn.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		sellBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SellView();
			}
		});
		listPane.add(sellBtn);
		
		
		//새로고침 버튼 이벤트
		RefreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh(table);
				refresh(calTable);
				setTimeLabel(timeLabel); //시간 갱신
				table.setModel(getContent()); //table의 model 넣어줌
				calTable.setModel(getCalContent());
			}
		});
		
		return listPane;
	}

	
	
	//테이블 model 생성
	public static DefaultTableModel getContent() {
		String[] header = {"종목명", "평가손익", "수익률", "보유수량", "평가금액", "평균매입가", "현재가"};
		DefaultTableModel model = createModel(header);
		
		//데이터 받기
		ArrayList<HoldingStockVO> list = null;
		try {
			list = new HoldingDAO().select(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object record[] = new Object[header.length];
		
		for (int i = 0; i < list.size(); i++) {
			HoldingStockVO vo = list.get(i);
			
			record[0] = vo.getStockName();
			record[1] = vo.getStockGainloss();
			record[2] = vo.getStockIncomeRate();
			record[3] = vo.getStockQuantity();
			record[4] = vo.getStockMarketPrice();
			record[5] = vo.getStockPurchasePrice();
			record[6] = vo.getStockCurrentPrice();
			
			model.addRow(record);
		}
		
		return model;
	}// getContent end
	
	
	
	public static DefaultTableModel getCalContent() {
		String[] header = {"총평가액", "총매입", "손익률", "손익계산"};
		DefaultTableModel model = createModel(header);
		
		Object record[] = new Object[header.length];
		CalDAO cal = new CalDAO();
		DecimalFormat df = new DecimalFormat("#,###");		
		try {
			record[0] = df.format(cal.totalStock(userId));
			record[1] = df.format(Integer.parseInt(cal.totalAvg(userId)));
			record[2] = cal.profitRate(userId) + "%";
			record[3] = df.format(cal.profit(userId));
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		model.addRow(record);
		
		return model;
	}
		
}//class end
