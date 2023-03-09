package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.CustomReviseVO;

public class CustomReviseDAO extends DBConn {
public CustomReviseDAO() {}
	
	// 회원 정보 수정(업데이트)
	public int ReviseUpdate(String user_id, String user_name,String user_pwd,String user_phoneNum) {
		int result = 0;
		
		try {
			getConn();
			sql = "update users set user_name=?,user_pwd=?,user_phoneNum=? where user_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_pwd);
			pstmt.setString(3, user_phoneNum);
			pstmt.setString(4, user_id);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return result;
	}
	
	// 저장되어 있는 값이 공백인지 확인하고 공백이면 저장되어 있는 값을 넣기 위해 미리 불러오는 메소드
	public List<CustomReviseVO> blankCheck(String user_id) {
		List<CustomReviseVO> result = new ArrayList<CustomReviseVO>();
		try {
			getConn();
			
			sql = "select user_name, user_pwd, user_phoneNum from users where user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReviseVO vo = new CustomReviseVO();
				vo.setUser_name(rs.getString(1));
				vo.setUser_pwd(rs.getString(2));
				vo.setUser_phoneNum(rs.getString(3));
				
				result.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	public int ReviseDelete(String user_id) {
		int result = 0;
		try {
			getConn();
			sql = "delete from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	
	public List<CustomReviseVO> setMypage(String id) {
		List<CustomReviseVO> lst = new ArrayList<CustomReviseVO>();
		
		try {
			getConn();
			sql = "select user_name,user_phoneNum from users where user_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReviseVO vo = new CustomReviseVO();
				
				vo.setUser_name(rs.getString(1));
				vo.setUser_phoneNum(rs.getString(2));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}

}
