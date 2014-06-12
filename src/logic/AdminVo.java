package logic;

import java.io.Serializable;

public class AdminVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String adminEmail;
	private String adminGrade;
	private String adminPasswd;
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminGrade() {
		return adminGrade;
	}
	public void setAdminGrade(String adminGrade) {
		this.adminGrade = adminGrade;
	}
	public String getAdminPasswd() {
		return adminPasswd;
	}
	public void setAdminPasswd(String adminPasswd) {
		this.adminPasswd = adminPasswd;
	}
	
	

}
