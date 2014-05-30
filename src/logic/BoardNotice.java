package logic;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class BoardNotice implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private Integer bdNoNtc;
	
	@NotEmpty
	private String titleNtc;
	
	@NotEmpty
	private String contentNtc;
	
	private Date dateNtc;
	
	@NotNull
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
