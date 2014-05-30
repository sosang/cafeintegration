package logic;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class BoardQaComments implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer bdNoQaComments;
	private Integer bdNoQa;
	private String userEmail;
	private String userAlias;
	@NotNull
	private String bdQaCommentsContent;
	private Date bdQaCommentsDate;
	private String bdQaCommentsIp;
	public Integer getBdNoQaComments() {
		return bdNoQaComments;
	}
	public void setBdNoQaComments(Integer bdNoQaComments) {
		this.bdNoQaComments = bdNoQaComments;
	}
	public Integer getBdNoQa() {
		return bdNoQa;
	}
	public void setBdNoQa(Integer bdNoQa) {
		this.bdNoQa = bdNoQa;
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
	public String getBdQaCommentsContent() {
		return bdQaCommentsContent;
	}
	public void setBdQaCommentsContent(String bdQaCommentsContent) {
		this.bdQaCommentsContent = bdQaCommentsContent;
	}
	public Date getBdQaCommentsDate() {
		return bdQaCommentsDate;
	}
	public void setBdQaCommentsDate(Date bdQaCommentsDate) {
		this.bdQaCommentsDate = bdQaCommentsDate;
	}
	public String getBdQaCommentsIp() {
		return bdQaCommentsIp;
	}
	public void setBdQaCommentsIp(String bdQaCommentsIp) {
		this.bdQaCommentsIp = bdQaCommentsIp;
	}
	

}
