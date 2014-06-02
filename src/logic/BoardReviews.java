package logic;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class BoardReviews implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer bdNoRev;
	private String userEmail;
	private String userAlias;
	@NotEmpty
	private String titleRev;
	@NotEmpty
	private String contentRev;
	private Integer countRev;
	private Integer recommendRev;
	private Integer refRev;
	private Integer reStep;
	private Integer reLevel;
	private Date dateRev;
	private String userIp;
	private String filePath1;
	private String filePath2;
	private String filePath3;
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
	public String getTitleRev() {
		return titleRev;
	}
	public void setTitleRev(String titleRev) {
		this.titleRev = titleRev;
	}
	public String getContentRev() {
		return contentRev;
	}
	public void setContentRev(String contentRev) {
		this.contentRev = contentRev;
	}
	public Integer getCountRev() {
		return countRev;
	}
	public void setCountRev(Integer countRev) {
		this.countRev = countRev;
	}
	public Integer getRecommendRev() {
		return recommendRev;
	}
	public void setRecommendRev(Integer recommendRev) {
		this.recommendRev = recommendRev;
	}
	public Integer getRefRev() {
		return refRev;
	}
	public void setRefRev(Integer refRev) {
		this.refRev = refRev;
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
	public Date getDateRev() {
		return dateRev;
	}
	public void setDateRev(Date dateRev) {
		this.dateRev = dateRev;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getFilePath1() {
		return filePath1;
	}
	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}
	public String getFilePath2() {
		return filePath2;
	}
	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}
	public String getFilePath3() {
		return filePath3;
	}
	public void setFilePath3(String filePath3) {
		this.filePath3 = filePath3;
	}
	
		
}
