package logic;

import java.io.Serializable;
import java.util.Date;

public class ItemVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer itemNo;
	private String itemName;
	private String origin;
	private String grade;
	private String processing;
	private Date roastingDate;
	private String roastingLevel;
	private String itemInfo;
	private String photo;
	private Integer price;
	private Integer totalProduct;
	private Integer defExchange;
	private Integer defRefund;

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public Date getRoastingDate() {
		return roastingDate;
	}

	public void setRoastingDate(Date roastingDate) {
		this.roastingDate = roastingDate;
	}

	public String getRoastingLevel() {
		return roastingLevel;
	}

	public void setRoastingLevel(String roastingLevel) {
		this.roastingLevel = roastingLevel;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Integer totalProduct) {
		this.totalProduct = totalProduct;
	}

	public Integer getDefExchange() {
		return defExchange;
	}

	public void setDefExchange(Integer defExchange) {
		this.defExchange = defExchange;
	}

	public Integer getDefRefund() {
		return defRefund;
	}

	public void setDefRefund(Integer defRefund) {
		this.defRefund = defRefund;
	}

}
