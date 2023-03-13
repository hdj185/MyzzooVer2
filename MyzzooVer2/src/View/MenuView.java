package View;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import View.List.HoldingListView;
import View.List.RankingListView;

public class MenuView extends JFrame{

	public static JFrame frame;	//기본창
	public static String userId;	//로그인한 user id
	static JTabbedPane tabbedPane;

	public MenuView() {}
	public MenuView(String userId) {
		initialize(userId);
	}

	// 창 만들기 ----------------------------------------------------//
	private void initialize(String user_id) {
		userId = user_id;
		frame = new JFrame("MY ZZOO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frame);
		frame.setSize(1000, 900);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
	}

	private static void createUI(final JFrame frame) {
		// tab 생성 ----------------------------------------------------//
		tabbedPane = new JTabbedPane();
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		// 종목순위 탭 ----------------------------------------------------//
		new RankingListView();
		JPanel RankingPane = RankingListView.createUI();
		tabbedPane.addTab("종목순위", null, RankingPane,"종목순위");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		// 보유주식 탭  ----------------------------------------------------//
		new HoldingListView();
		JPanel HoldingPane = HoldingListView.createUI();
		tabbedPane.addTab("보유주식", null, HoldingPane,"보유주식");
		
		
		// 마이페이지 탭  ----------------------------------------------------//     
		JPanel mypagePane = new CustomReviseView(userId);
		
		mypagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("마이페이지", null, mypagePane, "마이페이지");
	}
}
