package logic;

import java.io.Serializable;
import java.util.Date;

public class RecommendRecorder implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer recRecNo;
	private Integer bdNoRev;
	private String userEmail;
	private String userAlias;
	private Date recRecDate;
	private String userIp;
	public Integer getRecRecNo() {
		return recRecNo;
	}
	public void setRecRecNo(Integer recRecNo) {
		this.recRecNo = recRecNo;
	}
	public Integer getBdNoRev() {
		return bdNoRev;
	}
	public void setBdNoRev(Integer bdNoRev) {
		this.bdNoRev = bdNoRev;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	public Date getRecRecDate() {
		return recRecDate;
	}
	public void setRecRecDate(Date recRecDate) {
		this.recRecDate = recRecDate;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	

}
