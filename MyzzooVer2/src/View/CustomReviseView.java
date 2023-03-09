package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.CustomReviseDAO;
import Model.CustomReviseVO;


public class CustomReviseView extends JPanel implements ActionListener{
	String userId = "";
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
	JPanel lblPane= new JPanel();
	
		JLabel titleLbl = new JLabel("회원정보수정");
		JPanel namePane = new JPanel();
		
			JLabel nameLbl = new JLabel("이름");
			JTextField nameField = new JTextField(30);

		JPanel pwdPane = new JPanel();
			JLabel pwdLbl = new JLabel("비밀번호");
			JPasswordField pwdField = new JPasswordField(30);
			
		JPanel pwdCheckPane = new JPanel();
			JLabel pwdCheckLbl = new JLabel("비밀번호 확인");
			JPasswordField pwdCheckField = new JPasswordField(30);
			
		JPanel phoneNumPane = new JPanel();
			JLabel phoneNumLbl = new JLabel("연락처 '_' 없이 입력");
			JTextField phoneNumField = new JTextField(30);

		JPanel buttonPane = new JPanel();
			JButton saveBtn = new JButton("저장");
			JButton withdrawalBtn = new JButton("회원탈퇴");
			
		JPanel[] pane = {namePane,pwdPane,pwdCheckPane,phoneNumPane};
		JLabel[] lbl = {nameLbl,pwdLbl,pwdCheckLbl,phoneNumLbl};
		JTextField[] tf = {nameField,pwdField,pwdCheckField,phoneNumField};
		
		
		

	public CustomReviseView(String userId) {
		this.userId = userId;
		setLayout(new BorderLayout(250,50));
		add("North",new JLabel());
		add("East",new JLabel());
		add("West",new JLabel());
		add("South",new JLabel());
		add(main);
		main.setBackground(Color.white);
		main.setLayout(new GridLayout(0,1));
		main.add(titleLbl);
			titleLbl.setHorizontalAlignment(JLabel.CENTER);
			titleLbl.setOpaque(true);
			titleLbl.setBackground(Color.white);
			titleLbl.setFont(titleFnt);
		
		for(int i=0; i<pane.length;i++) {
			main.add(pane[i]);
			pane[i].setLayout(new BorderLayout(0,15));
			pane[i].setBackground(Color.white);
			pane[i].add("North",new JLabel());
			pane[i].add("East",new JLabel());
			pane[i].add("West",new JLabel());
			pane[i].add("South",new JLabel());
			pane[i].add("West", lbl[i]);
			pane[i].add("East", tf[i]);
			lbl[i].setOpaque(true);
			lbl[i].setFont(fnt);
			lbl[i].setBackground(Color.white);
			lbl[i].setHorizontalAlignment(JLabel.CENTER);
			tf[i].setFont(fnt);
		}
				
		main.add(buttonPane);
		buttonPane.setBackground(Color.white);
			buttonPane.add(saveBtn);
				saveBtn.setFont(fnt);
				saveBtn.setForeground(Color.white);
				saveBtn.setBackground(new Color(0,130,255));
			buttonPane.add(withdrawalBtn);
				withdrawalBtn.setFont(fnt);
				withdrawalBtn.setForeground(Color.white);
				withdrawalBtn.setBackground(new Color(0,130,255));
		
		setBackground(Color.white);
		setSize(1000,1200);
		setVisible(true);
		
		saveBtn.addActionListener(this);
		withdrawalBtn.addActionListener(this);
		
		getPrintInformation();
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			String id = this.userId;
			CustomReviseDAO dao = new CustomReviseDAO();
			List<CustomReviseVO> lst = dao.blankCheck(id);
			CustomReviseVO vo = new CustomReviseVO();
			// 적혀있는 부분이 공백일 경우 전에 저장했던 것을 불러와 그대로 저장한다.
			vo = lst.get(0);
			if(btn.equals("저장")) {
				if(!pwdField.getText().equals(pwdCheckField.getText())) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
				} else if(!pwdField.getText().equals("")) { 
					if(pwdField.getText().length()<6 || pwdField.getText().length()>20) {    
						JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능 합니다.");
					} else if(checkPWDMethod(pwdField.getText())==1) {
						JOptionPane.showMessageDialog(this, "비밀번호에는 특수문자 !@#만 포함 가능 합니다.");
					} else {
						CustomInfoUpdate();
					}
				} else if(pwdField.getText().equals("")){
					JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.");					
				}
			} else if(btn.equals("회원탈퇴")) {
				String msg = "고객의 모든 정보가 삭제됩니다.\n탈퇴하시겠습니까?";
				int state = JOptionPane.showOptionDialog(this, msg, "회원탈퇴", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null,null, obj);
				// yes 누르면 0 no 누르면 1 리턴
				if(state==0) {
					// db 연결하여 삭제
					int result = dao.ReviseDelete(LoginView.user_id.getText());
					if(result>0) {
						JOptionPane.showMessageDialog(this, "회원탈퇴 되었습니다\n이용해주셔서 감사합니다");
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(this, "회원탈퇴에 실패하였습니다 \n 관리자에게 문의하시기 바랍니다");
					}
					
				}
			}
		}
	}
	
	// 회원정보 수정 업데이트 메소드
	public void CustomInfoUpdate() {
		String id = this.userId;
		CustomReviseDAO dao = new CustomReviseDAO();
		String un = nameField.getText();
		String up = pwdField.getText();
		String upn = phoneNumField.getText();
		
		// 공백에 대응하도록 하도록 현재 저장되어 있는 값들을 불러온다 
		List<CustomReviseVO> lst = dao.blankCheck(id);
		CustomReviseVO vo = new CustomReviseVO();
		// 적혀있는 부분이 공백일 경우 전에 저장했던 것을 불러와 그대로 저장한다.
		vo = lst.get(0);
		if(un.equals("")) un = vo.getUser_name();
		if(up.equals("")) up = vo.getUser_pwd();
		if(upn.equals("")) upn = vo.getUser_phoneNum();
		
		
		int result = dao.ReviseUpdate(id, un, up, upn);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "회원정보 수정이 완료되었습니다");
			//this.setVisible(false);
			//new HoldingList().createWindow(userId);
		} else {
			JOptionPane.showMessageDialog(this, "회원정보 수정 실패하였습니다 \n 관리자에게 문의하시기 바랍니다");
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
	

	public void getPrintInformation() {
		CustomReviseDAO dao = new CustomReviseDAO();
		String id = this.userId;
		List<CustomReviseVO> name = dao.setMypage(id);
		for(int i=0; i<name.size(); i++) {
			CustomReviseVO vo = name.get(i);
			nameField.setText(vo.getUser_name());
			phoneNumField.setText(vo.getUser_phoneNum());
		}
		
	}
	
}
