package Model;

public class CustomReviseVO {
	private String user_id;
	private String user_name;
	private String user_pwd;
	private String user_phoneNum;
	
	
	public CustomReviseVO() {}
	public CustomReviseVO(String user_id,String user_name, String user_pwd, String user_phoneNum) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_phoneNum = user_phoneNum;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_phoneNum() {
		return user_phoneNum;
	}
	public void setUser_phoneNum(String user_phoneNum) {
		this.user_phoneNum = user_phoneNum;
	}
	@Override
	public String toString() {
		return "CustomReviseVO [user_id=" + user_id + ", user_name=" + user_name + ", user_pwd=" + user_pwd
				+ ", user_phoneNum=" + user_phoneNum + "]";
	}
	
	

}