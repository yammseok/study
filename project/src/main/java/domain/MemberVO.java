package domain;

public class MemberVO {
	private String email;
	private String pwd;
	private String nick_name;
	private String reg_at;
	private String last_login;
	public MemberVO() {}
	//login email pwd
	public MemberVO(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}
	//join modify = eamil pwd nick_name;
	public MemberVO(String email, String pwd, String nick_name) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nick_name = nick_name;
	}
	//list =all
	public MemberVO(String email, String pwd, String nick_name, String reg_at, String last_login) {
		super();
		this.email = email;
		this.pwd = pwd;
		this.nick_name = nick_name;
		this.reg_at = reg_at;
		this.last_login = last_login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getReg_at() {
		return reg_at;
	}
	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	
	
	

	
}
