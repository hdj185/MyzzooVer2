package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import DAO.SellDAO;
import Service.Crawler;

public class TradingView {

	private JFrame frame;
	private String tradingType = "매도";	//매도인지 매입인지
	private String code = "005930";		//주식 코드
	
	private JPanel centerPanel;
	private JPanel bottomPanel;
	
	private JPanel namePanel;
	private JPanel quantityPanel;
	private JPanel pricePanel;
	private JPanel calBtnPanel;
	private JPanel calLabelPanel;
	
	private JSpinner quantitySpinner;	//수량 필드
	private JSpinner priceSpinner;		//단가 필드
	private JLabel nameLbl;	//종목 이름 라벨
	private JLabel calLbl;	//계산 결과 라벨
	private JButton calculBtn;
	private JButton sellBtn;
	private JButton cancelBtn;
	
	public TradingView() {
		initialize();
	}

	//창 생성
	private void initialize() {
		frame = new JFrame("MY ZZOO - " + tradingType);
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
		frame.getContentPane().setLayout(layout);
		
		//Panel 설정
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		
		//center, bottom 패널 레이아웃
		centerPanel.setLayout(new GridLayout(5, 1, 10, 10));
		bottomPanel.setLayout(new GridLayout(1, 2, 10, 10));
		
		//name 패널
		namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(1, 2, 10, 10));
		nameLbl = new JLabel(getName());
		namePanel.add(new JLabel("종목"));
		namePanel.add(nameLbl);
		
		//quantity 패널
		quantityPanel = new JPanel();
		quantityPanel.setLayout(new GridLayout(1, 2, 10, 10));
		quantitySpinner = new JSpinner(getSpinModel(1, 100000, 1));
		quantityPanel.add(new JLabel("수량"));
		quantityPanel.add(quantitySpinner);

		//price 패널
		pricePanel = new JPanel();
		pricePanel.setLayout(new GridLayout(1, 2, 10, 10));
		priceSpinner = new JSpinner(getSpinModel(getCurrent(), 100000, 100));
		pricePanel.add(new JLabel("단가"));
		pricePanel.add(priceSpinner);
		
		//매매 금액 패널
		calBtnPanel = new JPanel();
		calBtnPanel.setLayout(new GridLayout(1, 2, 10, 10));
		calculBtn = new JButton("계산");
		calculBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calLbl.setText(getSellingPrice());
			}
		});
		calBtnPanel.add(new JLabel("매매금액"));
		calBtnPanel.add(calculBtn);
		
		//매매 금액 계산 결과 패널
		calLabelPanel = new JPanel();
		calLabelPanel.setLayout(new GridLayout(1, 2, 10, 10));
		calLbl = new JLabel();
		calLabelPanel.add(new JLabel("계산결과"));
		calLabelPanel.add(calLbl);
		
		//bottom 버튼
		sellBtn = new JButton(tradingType);
		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		//centerPanel 배치
		centerPanel.add(namePanel);
		centerPanel.add(quantityPanel);
		centerPanel.add(pricePanel);
		centerPanel.add(calBtnPanel);
		centerPanel.add(calLabelPanel);
		
		//bottomPanel 배치
		bottomPanel.add(sellBtn);
		bottomPanel.add(cancelBtn);
		
		JLabel Titlelbl = new JLabel(tradingType);
		Titlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(Titlelbl, BorderLayout.NORTH);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	//종목 이름 받기
	String getName() {
		return new SellDAO().getName(code);
	}
	
	//스핀 모델 설정
	SpinnerNumberModel getSpinModel(int init, int max, int increased) {
		return new SpinnerNumberModel(init, 1, max, increased);	//초기값, 최소값, 최대값, 증가값 순서
	}
	
	//매매단가 계산
	String getSellingPrice() {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(getSpinValue(quantitySpinner) * getSpinValue(priceSpinner)) + "원";
	}

	//Spinner 값 받기
	int getSpinValue(JSpinner spin) {
		return ((SpinnerNumberModel)spin.getModel()).getNumber().intValue();
	}
	
	//현재 시장가 받기
	int getCurrent() {
		String str = "";
		try {
			str = new Crawler(code).currentPrice();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(str.replace(",", ""));
	}
	
}