package logic;

public class CartVo {
	private Integer cartNo;
	private String userEmail;
	private ItemVo itemVo;
	private Integer cartNumOfProduct;
	private Integer cartSubTotal;
	
	public CartVo(ItemVo itemVo, Integer cartNumOfProduct, String userEmail, Integer cartSubTotal){
		this.itemVo = itemVo;
		this.cartNumOfProduct = cartNumOfProduct;
		this.userEmail = userEmail;
		this.cartSubTotal = cartSubTotal;
	}
	public CartVo(ItemVo itemVo, Integer cartNumOfProduct){
		this.itemVo = itemVo;
		this.cartNumOfProduct = cartNumOfProduct;
	}
	
	public void addCartNumOfProduct(Integer addCartNumOfProduct){
		int addCartNumOfProductInt = addCartNumOfProduct.intValue();
		int existCartNumOfProductInt = getCartNumOfProduct().intValue();
		setCartNumOfProduct(new Integer(addCartNumOfProductInt+existCartNumOfProductInt));
	}
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
	public ItemVo getItemVo() {
		return this.itemVo;
	}
	public void setItemVo(ItemVo itemVo) {
		this.itemVo = itemVo;
	}
	
	
	
}
