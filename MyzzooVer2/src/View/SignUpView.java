package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.SignUpDAO;
import Model.SignUpVO;

public class SignUpView extends JFrame implements ActionListener {
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,32);
	JLabel titleLbl = new JLabel("회 원 가 입");
	JLabel idLbl = new JLabel("아이디");
	JTextField user_id = new JTextField(30);
	JButton duplicateBtn = new JButton("중복 확인");
	JLabel pwdLbl = new JLabel("비밀번호");
	JPasswordField user_pwd = new JPasswordField(30);
	JLabel pwdcheckLbl = new JLabel("비밀번호 확인");
	JPasswordField user_pwdCheck = new JPasswordField(30);
	JLabel nameLbl = new JLabel("이름");
	JTextField user_name = new JTextField(30);
	JLabel telLbl = new JLabel("연락처");
	JTextField user_phoneNum = new JTextField(30);
	JButton signUpBtn = new JButton("회원가입");
	JButton cancelBtn = new JButton("취소");
	SignUpDAO dao = new SignUpDAO();
	public SignUpView() {
		setLayout(null);
		int x=300;
		int x1=410;
		add(titleLbl).setBounds(450,100,200,30);
		titleLbl.setFont(titleFnt);
		
		add(idLbl).setBounds(x,200,100,30); add(user_id).setBounds(x1,200,250,30); add(duplicateBtn).setBounds(670,200,110,30);
		idLbl.setFont(fnt);
		user_id.setFont(fnt);
		duplicateBtn.setFont(fnt);
		duplicateBtn.setBackground(new Color(0,130,255));
		duplicateBtn.setForeground(Color.white);
		
		add(pwdLbl).setBounds(x,250,100,30); add(user_pwd).setBounds(x1,250,250,30); 
		pwdLbl.setFont(fnt);
		user_pwd.setFont(fnt);
		
		add(pwdcheckLbl).setBounds(x,300,100,30); add(user_pwdCheck).setBounds(x1, 300, 250, 30); 
		pwdcheckLbl.setFont(fnt);
		user_pwdCheck.setFont(fnt);
		
		add(nameLbl).setBounds(x,350,100,30); add(user_name).setBounds(x1,350,250,30);
		nameLbl.setFont(fnt);
		user_name.setFont(fnt);
		
		add(telLbl).setBounds(x,450,100,30); add(user_phoneNum).setBounds(x1,450,250,30);
		telLbl.setFont(fnt);
		user_phoneNum.setFont(fnt);
				
		add(signUpBtn).setBounds(400,630,100,30); add(cancelBtn).setBounds(530,630,100,30);
		signUpBtn.setFont(fnt);
		signUpBtn.setBackground(new Color(0,130,255));
		signUpBtn.setForeground(Color.white);
		signUpBtn.setEnabled(false);
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(0,130,255));
		cancelBtn.setForeground(Color.white);
		
		getContentPane().setBackground(Color.white);
		setSize(1000, 900);
		setLocationRelativeTo(null);  
		setVisible(true);
		
		duplicateBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if(btn.equals("회원가입")) {
				String id = user_id.getText();
				String password = user_pwd.getText();
				String pwdCheck = user_pwdCheck.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력하세요. ");
				} else if(password.equals("")) {
					JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요. ");
				} else if(password.length()<6 || password.length()>20) {    
					JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능 합니다. ");
				} else if(pwdCheck.equals("")) {
					JOptionPane.showMessageDialog(this, "비교할 비밀번호를 입력해 주세요. ");
				} else if(!password.equals(pwdCheck)) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다. ");
				} else if(checkPWDMethod(password)==1) {
					JOptionPane.showMessageDialog(this, "비밀번호 특수문자는 !@#만 포함 가능 합니다. ");
				} else if(user_name.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이름을 입력하세요. ");
				} else if(user_phoneNum.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "연락처를 입력하세요. ");
				}else {
					SignUpVO vo = new SignUpVO(user_id.getText(),user_pwd.getText(),
							user_name.getText(),
							user_phoneNum.getText());
									
					int result = dao.SignUpInsert(vo);
					if(result>0) { // 회원등록 성공함
						JOptionPane.showMessageDialog(this, "회원가입에 성공하였습니다\n원활한 이용을 위하여\n로그인 해주시기 바랍니다. ");
					} else { //회원등록 실패함
						JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다\n 관리자에게 문의해 주시기 바랍니다. ");
					}
					dispose();
					new LoginView();
				}
			} else if(btn.equals("취소")) {
				dispose();
				new LoginView();
			} else if(btn.equals("중복 확인")) {
				String idSearch = user_id.getText();
				System.out.println(idSearch.length());
				if(idSearch.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다. ");
					//id 특수문자 포함 확인
				} else if(idSearch.length() < 6 || idSearch.length() > 15) {
					JOptionPane.showMessageDialog(this, "아이디는 6자리 이상, 15자리 이하만 가능 합니다. ");
				} else if(checkIDMethod(idSearch)==1){
					JOptionPane.showMessageDialog(this, "아이디는 특수문자 포함이 불가능합니다. ");
				} else {
					List<SignUpVO> result = dao.getidCheck(idSearch);
					if(result.size()==0) {
						JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다. ");
						signUpBtn.setEnabled(true);
						user_id.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(this, "등록되어 있는 아이디 입니다");
					}
						
				} 
			}
		}
	}
	
	public int checkIDMethod(String id) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<id.length(); i++) {
			alpha = id.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
			}
		}
		return check;
	}
	
	public int checkPWDMethod(String pwd) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<pwd.length(); i++) {
			alpha = pwd.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=32 || code>=36 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
				
			}
		}
		return check;
	}
}
