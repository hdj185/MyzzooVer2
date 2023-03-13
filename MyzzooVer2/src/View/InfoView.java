package View;

import javax.swing.JFrame;

public class InfoView {

	private JFrame frame;
	String name;

	public InfoView(String name) {
		this.name = name;
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("MY ZZOO - " + name + " 종목 정보");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
	}

	
}
