package View.User;
/* 로그인 View */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.LoginDAO;
import View.MenuView;

public class LoginView extends JFrame implements ActionListener {
	Font titleFnt = new Font("휴먼둥근헤드라인",Font.PLAIN,32);
	Font fnt = new Font("굴림체",Font.BOLD,14);
	JPanel mainPane = new JPanel();
	JPanel loginPane = new JPanel();
		JPanel topPane = new JPanel();
			JLabel loginTitle = new JLabel("MY ZZOO");
			
		JPanel centerPane = new JPanel();
			//이미지
			static ImageIcon icon = new ImageIcon("src/img/stockphoto.png");
			static Image im = icon.getImage();
			Image im2 = im.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
			ImageIcon icon2 = new ImageIcon(im2);
			JLabel lbl = new JLabel(icon2);
			
		JPanel southPane = new JPanel();
		
			JPanel southCenterPane = new JPanel();
			
			JPanel idPane = new JPanel();
				JLabel idLbl = new JLabel("아이디");
				public static JTextField user_id = new JTextField(20);
				
			JPanel pwdPane = new JPanel();
				JLabel pwdLbl = new JLabel("비밀번호");
				JPasswordField user_pwd = new JPasswordField(20);
				
			JPanel btnPane = new JPanel();
				JButton joinBtn = new JButton("회원가입");
				JButton loginBtn = new JButton("로그인");
	
		JPanel mainSouthPane = new JPanel();
		
	public LoginView() {
		//////////////////////////레이아웃////////////////////////
		add(mainPane);
		
		// 메인 패널 레이아웃 그리드백레이아웃 
		mainPane.setLayout(new GridBagLayout());
		
		//그리드백 제약사항을 정의
		GridBagConstraints gbc = new GridBagConstraints();
		
		// 전체를 그리드백레이웃으로 채운다
		gbc.fill = GridBagConstraints.BOTH;
	
		// moving
		gbc.weightx=1.0;
		gbc.weighty=1.5;
		gbc.gridx=0;
		gbc.gridy=0;
		
		// grid top
		gbc.weightx=1.0;
		gbc.weighty=3.0;
		gbc.gridx=0;
		gbc.gridy=1;
		mainPane.add(loginPane,gbc);
			loginPane.setLayout(new BorderLayout());
			loginPane.add(loginTitle);
			// 제목 폰트 설정
			loginTitle.setFont(titleFnt);
			loginTitle.setHorizontalAlignment(JLabel.CENTER);
			loginPane.setBackground(Color.white);
	
		// grid center
		gbc.weightx=1.0;
		gbc.weighty=3.0;
		gbc.gridx=0;
		gbc.gridy=2;
		mainPane.add(centerPane,gbc);
			centerPane.setLayout(new BorderLayout());
			centerPane.setBackground(Color.white);
			centerPane.add(lbl);
			
		
		//grid south
		gbc.weightx=1.0;
		gbc.weighty=3.5;
		gbc.gridx=0;
		gbc.gridy=3;
		mainPane.add(southPane,gbc);
			southCenterPane.setLayout(new GridLayout(3,1));
			// 아이디 패널
			southCenterPane.add(idPane);
				idPane.add(idLbl);
				idPane.add(user_id);
				idPane.setFont(fnt);
				idPane.setBackground(Color.white);
			//패스워드 패널
			southCenterPane.add(pwdPane);
				pwdPane.add(pwdLbl);
				pwdPane.add(user_pwd);
				pwdPane.setFont(fnt);
				pwdPane.setBackground(Color.white);
			// 회원가입 로그인 버튼 패널
			southCenterPane.add(btnPane);
				btnPane.add(joinBtn);
					joinBtn.setBackground(new Color(0,130,255));
					joinBtn.setForeground(Color.white);
				btnPane.add(loginBtn);
					loginBtn.setBackground(new Color(0,130,255));
					loginBtn.setForeground(Color.white);
				btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				btnPane.setBackground(Color.white);
			southPane.add(southCenterPane);
			southPane.setBackground(Color.white);
			southCenterPane.setBackground(Color.white);
					
		// 전체화면 풀릴경우 기본화면 크기
		setResizable(false);
		setSize(1000, 900);
		// 화면 보이기 여부
		setVisible(true);
		// 자원해제, 끄기
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 타이틀
		setTitle("MY ZZOO");
		setIconImage(im);
		// 이벤트
		joinBtn.addActionListener(this);
		loginBtn.addActionListener(this);
		// 키 이벤트 (로그인)
		user_pwd.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					getLoginData();
				}
			}

		});
		
		//가운데 띄우기
		this.setLocationRelativeTo(null);
	}

//////////////////////////이벤트 등록//////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventBtn = e.getSource();
		if(eventBtn==joinBtn) {
			new SignUpView();
			dispose();
		} else if(eventBtn==loginBtn) {
			getLoginData();
		}
	}

	////////////////////////// 로그인 db //////////////////////
	public void getLoginData() {
		String userid = user_id.getText();
		String userpwd = user_pwd.getText();
		String admin[] = {"ghdrlfehd"};
		LoginDAO dao = new LoginDAO();
		int customLogin = 0;
		for(int i=0;i<admin.length;i++) {
			if(user_id.equals(admin[i])) {
				int loginCheck = dao.getLogin(userid, userpwd);
				if(loginCheck == 0)JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 틀렸습니다. ");
				else if(loginCheck==1) {
					customLogin = 1;
					JOptionPane.showMessageDialog(this, "로그인 성공! ");
					dispose();
					break;
				}
			}
		}
		
		if(customLogin==0) {
			if(user_id.equals("")) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요. ");
			} else if(user_pwd.equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요. ");
			} else {
				int loginCheck = dao.getLogin(userid, userpwd);
				if(loginCheck == 0) JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 틀렸습니다.");
				else if(loginCheck ==1 ) {
					JOptionPane.showMessageDialog(this, "로그인 성공! ");
					
					new MenuView(userid);
					dispose(); // 화면 켜지면 main 종료
				}
			}
		}
	}
	
}//class end
