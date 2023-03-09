package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.SignUpVO;

public class SignUpDAO extends DBConn{

	public SignUpDAO() {}
	// 회원가입 db insert
	public int SignUpInsert(SignUpVO vo) {
		int result = 0;
		try{
			getConn();
			sql = "insert into users(user_id, "
					+ " user_pwd,user_name,user_phoneNum) "
					+ " values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getUser_pwd());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setString(4, vo.getUser_phoneNum());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// 회원 아이디 비밀번호 검색, 회원 유무 확인
	public List<SignUpVO> getidCheck(String user_id){
		List<SignUpVO> lst = new ArrayList<SignUpVO>();		
		try {
			getConn();
			sql = "select user_id from users where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SignUpVO vo = new SignUpVO();
				vo.setUser_id(rs.getString(1));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
}