package logic;

import java.io.Serializable;
import java.util.Date;

public class BoardReviewsComments implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer bdNoRevComments;
	private Integer bdNoRev;
	private String userEmail;
	private String userAlias;
	private String bdRevCommentsContent;
	private Date bdRevCommentsDate;
	private String bdRevCommentsIp;
	
	public Integer getBdNoRevComments() {
		return bdNoRevComments;
	}
	public void setBdNoRevComments(Integer bdNoRevComments) {
		this.bdNoRevComments = bdNoRevComments;
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
	public String getBdRevCommentsContent() {
		return bdRevCommentsContent;
	}
	public void setBdRevCommentsContent(String bdRevCommentsContent) {
		this.bdRevCommentsContent = bdRevCommentsContent;
	}
	public Date getBdRevCommentsDate() {
		return bdRevCommentsDate;
	}
	public void setBdRevCommentsDate(Date bdRevCommentsDate) {
		this.bdRevCommentsDate = bdRevCommentsDate;
	}
	public String getBdRevCommentsIp() {
		return bdRevCommentsIp;
	}
	public void setBdRevCommentsIp(String bdRevCommentsIp) {
		this.bdRevCommentsIp = bdRevCommentsIp;
	}
	
}
