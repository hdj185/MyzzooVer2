package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellView extends TradingView {
	private int quantity;
	
	SellView() {
		super();
	}
	
	SellView(String name) {
		super("매도", name);
		init();
	}
	
	void init() {
		String userId = MenuView.userId;
		quantity = dao.getQuantity(userId);
		
		//수량 스피너 모델 지정
		quantitySpinner.setModel(getSpinModel(1, quantity, 1));
		
		//매도 버튼 이벤트 추가
		tradingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int spinValue = getSpinValue(quantitySpinner);
				if(spinValue < quantity) {	//보유수량보다 적게 선택했을 때
					
				} else { //보유수량 전체를 선택했을 때
					
				}
			}
		});
	}
}
