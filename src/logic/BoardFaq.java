package logic;

import java.io.Serializable;

public class BoardFaq implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer bdNoFaq;
	private String titleFaq;
	private String contentFaq;
	public Integer getBdNoFaq() {
		return bdNoFaq;
	}
	public void setBdNoFaq(Integer bdNoFaq) {
		this.bdNoFaq = bdNoFaq;
	}
	public String getTitleFaq() {
		return titleFaq;
	}
	public void setTitleFaq(String titleFaq) {
		this.titleFaq = titleFaq;
	}
	public String getContentFaq() {
		return contentFaq;
	}
	public void setContentFaq(String contentFaq) {
		this.contentFaq = contentFaq;
	}
	
}
