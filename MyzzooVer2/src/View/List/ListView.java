package View.List;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import View.MenuView;
import View.User.LoginView;

public class ListView {
	static JPanel listPane;

	static JLabel titleLabel;
	static JButton RefreshBtn;
	static JButton logoutBtn;
	static JScrollPane scrollpane;
	
	ListView() {
		listPane = new JPanel();
	}
	
	public static void init() {
		// 타이틀
		titleLabel = new JLabel();
		titleLabel.setBounds(340, 50, 310, 75);
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 35));
		
		
		// 새로고침 - 버튼 클릭하면 새로고침
		RefreshBtn = new JButton("새로고침");
		RefreshBtn.setBounds(750, 30, 90, 25);
		RefreshBtn.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		
		
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
		
		
		//패널에 컴포넌트 추가
		listPane.setLayout(null);
		listPane.add(titleLabel);
		listPane.add(RefreshBtn);
		listPane.add(logoutBtn);
		listPane.setBackground(Color.white);
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	
	//테이블 레이아웃
	public static JTable getTable() {
		JTable tbl = new JTable();
		
		resizeColumnWidth(tbl);
		tbl.setRowHeight(25);	//셀 높이 조정
		tbl.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		tbl.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //table 행을 하나만 선택할 수 있게 고정
		
		return tbl;
	}
	
	
	//table model reset
	static void resetModel(JTable tbl) {
		DefaultTableModel model = (DefaultTableModel) tbl.getModel();
		model.setNumRows(0);			//table의 model 0으로
	}
	
	
	//기준 시간 넣기
	static void setTimeLabel(JLabel timeLb) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		timeLb.setText("기준 시간 : " + timestamp);
	}
	
	
	//수정 금지하는 model 생성
	static DefaultTableModel createModel(String[] header) {
		return new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
	}
	
	
	//셀 크기 자동 조절
	public static void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 10; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

	
	//선택한 테이블 행 종목명 받기
	static String getSelectName(JTable tbl) {
		int row = tbl.getSelectedRow();
		return (String) (tbl.getModel()).getValueAt(row, 0);
	}
}
