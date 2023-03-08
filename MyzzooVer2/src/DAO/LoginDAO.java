package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.LoginVO;

public class LoginDAO extends DBConn {
	public LoginDAO() {}
	public List<LoginVO> LoginAllSelect(){
		List<LoginVO> lst = new ArrayList<LoginVO>();
		try {
			getConn();
			sql = "select user_id, user_pwd from users";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LoginVO vo = new LoginVO(rs.getString(0),rs.getString(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	public int getLogin(String user_id,String user_pwd){
		List<LoginVO> lst = new ArrayList<LoginVO>();
		
		int state = 0;
		try {
			getConn();
			sql = "select user_id, user_pwd from users where user_id = ? and user_pwd = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) state = 1;
			
		} catch(Exception e) {
			System.out.println("DB 아이디 비밀번호 확인에러"+e.getMessage());
		} finally {
			dbClose();
		}
		return state;
	}
}
