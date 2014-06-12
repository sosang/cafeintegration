package logic;

public class PurchaseLineVo {

	private PurchaseVo purchase;
	private Integer purchaseLineNo;
	private Integer itemNo;
	private Integer numOfProduct;

	public PurchaseLineVo(PurchaseVo purchase, Integer purchaseLineNo,
			CartVo cart) {
		this.purchase = purchase;
		this.purchaseLineNo = purchaseLineNo;
		this.itemNo = cart.getItemNo();
		this.numOfProduct = cart.getCartNumOfProduct();
	}

	// public PurchaseLineVo(PurchaseVo purchase, Integer purchaseLineNo,
	// Integer numOfProduct ,ItemVo item){
	// this.purchase = purchase;
	// this.purchaseLineNo = purchaseLineNo;
	// this.item = item;
	// this.numOfProduct = numOfProduct;
	// }

	public PurchaseVo getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseVo purchase) {
		this.purchase = purchase;
	}

	public Integer getPurchaseLineNo() {
		return purchaseLineNo;
	}

	public void setPurchaseLineNo(Integer purchaseLineNo) {
		this.purchaseLineNo = purchaseLineNo;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public Integer getNumOfProduct() {
		return numOfProduct;
	}

	public void setNumOfProduct(Integer numOfProduct) {
		this.numOfProduct = numOfProduct;
	}

}
