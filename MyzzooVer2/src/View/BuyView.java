package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class BuyView extends TradingView {
	public BuyView() {
		super();
		tradingType = "매입";
	}
	
	public BuyView(String name) {
		super("매입", name);
		init();
	}
	
	void init() {
		//수량 스피너 모델 지정
		quantitySpinner.setModel(getSpinModel(1, Long.MAX_VALUE, 1));
		
		//매도 버튼 이벤트 추가
		tradingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int spinValue = getSpinValue(quantitySpinner);	//유저가 지정한 수량
				int spinPrice = getSpinValue(priceSpinner);		//유저가 지정한 단가
				String msg = "";
				
				if(dao.existsStock()) {	//유저가 보유한 종목일 경우
					int quantity = dao.getQuantity();	//유저가 이미 보유한 주식 수량 
					int purchase = dao.getPurchase();	//유저가 이미 보유한 주식 단가
					int updateQuantity = spinValue + quantity;
					String updatePrice = Integer.toString((spinValue * spinPrice + quantity * purchase) / updateQuantity);
					
					dao.updateStock(Integer.toString(updateQuantity), updatePrice);
				} else { //유저가 보유하지 않은 종목일 경우
					dao.insertStock(Integer.toString(spinValue), Integer.toString(spinPrice));
				}
				
				msg = "[" + stockName + "] 종목을 " + spinValue + "주 매입하였습니다.";
				HoldingListView.refresh();
				JOptionPane.showMessageDialog(null, msg);
				frame.dispose();
			}
		});
	}
}
