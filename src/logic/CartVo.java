package logic;

public class CartVo {

	private Integer cartNo;
	private String userEmail;
	private Integer itemNo;
	private String itemName;
	private String photo;

	private Integer price;

	private Integer cartNumOfProduct;
	private Integer cartSubTotal;

	public Integer getCartNo() {
		return cartNo;
	}

	public void setCartNo(Integer cartNo) {
		this.cartNo = cartNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

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

	public Integer getCartNumOfProduct() {
		return cartNumOfProduct;
	}

	public void setCartNumOfProduct(Integer cartNumOfProduct) {
		this.cartNumOfProduct = cartNumOfProduct;
	}

	public Integer getCartSubTotal() {
		return cartSubTotal;
	}

	public void setCartSubTotal(Integer cartSubTotal) {
		this.cartSubTotal = cartSubTotal;
	}

}
