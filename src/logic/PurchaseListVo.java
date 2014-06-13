package logic;

import java.util.Date;

public class PurchaseListVo {

	private Integer numOfProduct;
	private Date timeOfPurchase;
	private String itemName;
	private String photo;
	private Integer price;

	public Integer getNumOfProduct() {
		return numOfProduct;
	}

	public void setNumOfProduct(Integer numOfProduct) {
		this.numOfProduct = numOfProduct;
	}

	public Date getTimeOfPurchase() {
		return timeOfPurchase;
	}

	public void setTimeOfPurchase(Date timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

}
