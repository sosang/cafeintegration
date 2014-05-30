package logic;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BoardQa implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer bdNoQa;
	@NotNull
	private String userEmail;
	@NotNull
	private String userAlias;
	@NotEmpty
	private String titleQa;
	@NotEmpty
	private String contentQa;
	private Integer countQa;
	private Integer refQa;
	private Integer reStep;
	private Integer reLevel;
	private Date dateQa;
	private String userIp;
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
	public String getTitleQa() {
		return titleQa;
	}
	public void setTitleQa(String titleQa) {
		this.titleQa = titleQa;
	}
	public String getContentQa() {
		return contentQa;
	}
	public void setContentQa(String contentQa) {
		this.contentQa = contentQa;
	}
	public Integer getCountQa() {
		return countQa;
	}
	public void setCountQa(Integer countQa) {
		this.countQa = countQa;
	}
	public Integer getRefQa() {
		return refQa;
	}
	public void setRefQa(Integer refQa) {
		this.refQa = refQa;
	}
	public Integer getReStep() {
		return reStep;
	}
	public void setReStep(Integer reStep) {
		this.reStep = reStep;
	}
	public Integer getReLevel() {
		return reLevel;
	}
	public void setReLevel(Integer reLevel) {
		this.reLevel = reLevel;
	}
	public Date getDateQa() {
		return dateQa;
	}
	public void setDateQa(Date dateQa) {
		this.dateQa = dateQa;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
}
