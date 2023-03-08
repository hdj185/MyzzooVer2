package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.HoldingDAO;
import Model.HoldingStockVO;

public class ListView {
	static JPanel listPane;
	static String userId; //유저 id

	static JLabel titleLabel;
	static JLabel timeLabel;
	static JButton RefreshBtn;
	static JButton logoutBtn;
	static JScrollPane scrollpane;
	static JTable table = new JTable();
	
	ListView() {
		listPane = new JPanel();
	}
	
	ListView(String userId) {
		this.userId = userId;
		listPane = new JPanel();
	}
	
	public static void init() {
		// 타이틀
		titleLabel = new JLabel("《 보유주식 잔고 》");
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
//		RefreshBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				refresh();
//			}
//		});
		
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
		getTable();
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(45, 200, 900, 500);
		
		
		listPane.setLayout(null);
		listPane.add(titleLabel);
		listPane.add(timeLabel);
		listPane.add(RefreshBtn);
		listPane.add(logoutBtn);
		listPane.add(scrollpane);
		
	}
	
	//테이블 레이아웃
	public static void getTable() {
		table.setRowHeight(25);	//셀 높이 조정
		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
	}
	
	/*
	//refresh 버튼
	static void refresh() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);			//table의 model 0으로
		table.setModel(getContent());	//table의 model 넣어줌
	}
	*/
}
