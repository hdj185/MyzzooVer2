package View.Trading;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.List.HoldingListView;

public class SellView extends TradingView {
	private int quantity;
	
	SellView() {
		super();
		tradingType = "매도";
	}
	
	public SellView(String name) {
		super("매도", name);
		init();
	}
	
	void init() {
		quantity = dao.getQuantity();
		
		//수량 스피너 모델 지정
		quantitySpinner.setModel(getSpinModel(1, quantity, 1));
		
		//매도 버튼 이벤트 추가
		tradingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int spinValue = getSpinValue(quantitySpinner);
				String msg = "";
				if(spinValue < quantity) {	//보유수량보다 적게 선택했을 때
					dao.updateStock(Integer.toString(quantity - spinValue), Integer.toString(dao.getPurchase()));
					msg = "[" + stockName + "] 종목을 " + spinValue + "주 매도하였습니다.";
				} else { //보유수량 전체를 선택했을 때
					dao.deleteStock(code);
					msg = "[" + stockName + "] 종목을 전체 매도하였습니다.";
				}
				HoldingListView.refresh();					//보유주식 창 새로고침
				JOptionPane.showMessageDialog(null, msg);	//알림창 출력
				frame.dispose();
			}
		});
	}
}
