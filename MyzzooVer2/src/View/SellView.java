package View;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SellView {

	private JFrame frame;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JTextField quantityField;	//수량 필드
	private JTextField priceField;		//단가 필드
	private JButton calculBtn;
	private JButton sellBtn;
	private JButton cancelBtn;
	
	public SellView() {
		initialize();
	}

	//창 생성
	private void initialize() {
		frame = new JFrame("MY ZZOO - 매도");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		
		createUI();
	}
	
	//UI
	private void createUI() {
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		frame.setLayout(layout);
		
		//centerPanel
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 2, 10, 10));
		bottomPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		quantityField = new JTextField();
		priceField = new JTextField();
		calculBtn = new JButton("계산");
		
		sellBtn = new JButton("매도");
		cancelBtn = new JButton("취소");
		
		centerPanel.add(new JLabel("종목"));
		centerPanel.add(new JLabel("이름"));
		centerPanel.add(new JLabel("수량"));
		centerPanel.add(quantityField);
		centerPanel.add(new JLabel("단가"));
		centerPanel.add(priceField);
		centerPanel.add(new JLabel("매매 금액"));
		centerPanel.add(calculBtn);
		
		bottomPanel.add(sellBtn);
		bottomPanel.add(cancelBtn);
		
		frame.add(new JLabel("매도"), BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

}
