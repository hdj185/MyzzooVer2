package View;

public class SellView extends TradingView {
	SellView() {
		super();
	}
	
	SellView(String name, String id) {
		super("매도", name, id);
	}
	
	void init() {
		quantitySpinner.setModel(getSpinModel(1, 100000, 1));
	}
}
