package logic;

public class PurchaseLineVo {
	private PurchaseVo purchase;
	private Integer purchaseLineNo;
	private ItemVo item;
	private Integer numOfProduct;

	
	public PurchaseLineVo(PurchaseVo purchase,Integer purchaseLineNo,CartVo cart){
		this.purchase = purchase;
		this.purchaseLineNo = purchaseLineNo;
		this.item = cart.getItemVo();
		this.numOfProduct = cart.getCartNumOfProduct();	

	}
	
//	public PurchaseLineVo(PurchaseVo purchase, Integer purchaseLineNo, Integer numOfProduct ,ItemVo item){
//		this.purchase = purchase;
//		this.purchaseLineNo = purchaseLineNo;
//		this.item = item;
//		this.numOfProduct = numOfProduct;
//	}

	public PurchaseVo getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseVo purchase) {
		this.purchase = purchase;
	}

	public Integer getPurchaseLineNo() {
		return purchaseLineNo;
	}

	public void setPurchaseLineId(Integer purchaseLineNo) {
		this.purchaseLineNo = purchaseLineNo;
	}

	public ItemVo getItem() {
		return item;
	}

	public void setItem(ItemVo item) {
		this.item = item;
	}

	public Integer getNumOfProduct() {
		return numOfProduct;
	}

	public void setNumOfProduct(Integer numOfProduct) {
		this.numOfProduct = numOfProduct;
	}


	
	
	
	
	

}
