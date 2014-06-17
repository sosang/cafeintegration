package logic;

import java.io.Serializable;
import java.util.Date;

public class BoardNotice implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer bdNoNtc;
	
	private String titleNtc;
	
	private String contentNtc;
	
	private Date dateNtc;
	
	private Integer countNtc;

	public Integer getBdNoNtc() {
		return bdNoNtc;
	}

	public void setBdNoNtc(Integer bdNoNtc) {
		this.bdNoNtc = bdNoNtc;
	}

	public String getTitleNtc() {
		return titleNtc;
	}

	public void setTitleNtc(String titleNtc) {
		this.titleNtc = titleNtc;
	}

	public String getContentNtc() {
		return contentNtc;
	}

	public void setContentNtc(String contentNtc) {
		this.contentNtc = contentNtc;
	}

	public Date getDateNtc() {
		return dateNtc;
	}

	public void setDateNtc(Date dateNtc) {
		this.dateNtc = dateNtc;
	}

	public Integer getCountNtc() {
		return countNtc;
	}

	public void setCountNtc(Integer countNtc) {
		this.countNtc = countNtc;
	}
	
	
}
