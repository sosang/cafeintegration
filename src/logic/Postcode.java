package logic;

import java.io.Serializable;

public class Postcode implements Serializable {
	private static final long serialVersionUID = 1L;
	private String zipcode;
	private String sido;
	private String sigungu;
	private String eupmyeondong;
	private String ri;
	private String beonji;
	private String bldg;
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getEupmyeondong() {
		return eupmyeondong;
	}
	public void setEupmyeondong(String eupmyeondong) {
		this.eupmyeondong = eupmyeondong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getBeonji() {
		return beonji;
	}
	public void setBeonji(String beonji) {
		this.beonji = beonji;
	}
	public String getBldg() {
		return bldg;
	}
	public void setBldg(String bldg) {
		this.bldg = bldg;
	}


}
