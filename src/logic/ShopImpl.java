package logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopImpl implements Shop {

	@Autowired
	private ItemCatalog itemCatalog;

	@Autowired
	private CartCatalog cartCatalog;

	@Autowired
	private PurchaseCatalog purchaseCatalog;

	public void setItemCatalog(ItemCatalog itemCatalog) {
		this.itemCatalog = itemCatalog;
	}

	@Override
	public List<ItemVo> getItemList() {
		// TODO Auto-generated method stub
		return this.itemCatalog.getItemList();
	}

	@Override
	public ItemVo getItemByItemNo(Integer itemNo) {
		// TODO Auto-generated method stub
		return this.itemCatalog.getItemByItemNo(itemNo);
	}


	@Override
	public void entryCart(CartVo cart) {
		// TODO Auto-generated method stub
		this.cartCatalog.entryCart(cart);
	}

	@Override
	public List<CartVo> getCartList(String userEmail) {
		// TODO Auto-generated method stub
		return this.cartCatalog.getCartList(userEmail);
	}

	@Override
	public void clearCart(String userEmail) {
		this.cartCatalog.clearCart(userEmail);
	}

	@Override
	public void checkout(String userEmail, String receiver,
			String recpostcode, String recphone, String recaddr, String remarks) {
		// TODO Auto-generated method stub
		PurchaseVo purchase = createPurchase(userEmail, receiver,
				recpostcode, recphone, recaddr, remarks);
		entryPurchase(purchase);

	}
//	@Override
//	private int[] number(){
//		
//		
//	}

	private PurchaseVo createPurchase( String userEmail,
			String receiver, String recphone, String recaddr,
			String recpostcode, String remarks) {
		PurchaseVo purchase = new PurchaseVo();

		purchase.setUserEmail(userEmail);
		purchase.setPurchaseNo(getNewPurchaseNo());
		purchase.setRecaddr(recaddr);
		purchase.setReceiver(receiver);
		purchase.setRecphone(recphone);
		purchase.setRecpostcode(recpostcode);
		purchase.setRemarks(remarks);
		
		List<CartVo> itemList = this.cartCatalog.getCartList(userEmail);
		System.out.println(itemList.get(0).getItemNo());
		for (int i = 0; i < itemList.size(); i++) {
			CartVo itemSet = (CartVo) itemList.get(i);
			int purchaseLineNo = i + 1;

			PurchaseLineVo purchaseLine = createPurchaseLineVo(purchase,
					purchaseLineNo, itemSet);
			purchase.addPurchaseLine(purchaseLine);
		}
		return purchase;

	}

	private Integer getNewPurchaseNo() {
		// TODO Auto-generated method stub
		return this.purchaseCatalog.getNewPurchaseNo();
	}

	private void entryPurchase(PurchaseVo purchase) {
		this.purchaseCatalog.entryPurchase(purchase);

	}

	private PurchaseLineVo createPurchaseLineVo(PurchaseVo purchase,
			Integer purchaseLineNo, CartVo itemSet) {
		return new PurchaseLineVo(purchase, purchaseLineNo, itemSet);
	}

	@Override
	public void mypageCartclear(Integer itemNo) {
		// TODO Auto-generated method stub
		this.cartCatalog.somedeleteCart(itemNo);

	}

	@Override
	public List<PurchaseListVo> mypagePurchase(String userEmail) {
		// TODO Auto-generated method stub
		return this.purchaseCatalog.userPurchaseList(userEmail);
	}

	@Override
	public List<NumNo> getNum() {
		// TODO Auto-generated method stub
		return this.purchaseCatalog.getNum();
	}


	
	//시행착오 주석
	
	/*
	 * @Override public Purchase_VO getPurchaseByPurchaseNo(Integer purchaseNo)
	 * { // TODO Auto-generated method stub return
	 * this.purchaseCatalog.getPurchaseByPurchaseNo(purchaseNo); }
	 */

	// @Override
	// public List<Purchase_VO> getPurchaseList() {
	// // TODO Auto-generated method stub
	// return this.purchaseCatalog.getPurchaseList();
	// }
	//
	// @Override
	// public Purchase_VO getPurchaseByPurchaseNo(Integer purchaseNo) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	// @Override
	// public void checkout(UseCart cart,String userEmail) {
	// // TODO Auto-generated method stub
	// PurchaseVo purchase = createPurchase(cart,userEmail);
	// entryPurchase(purchase);
	//
	// }
	//
	// private PurchaseVo createPurchase(UseCart cart,String userEmail) {
	// PurchaseVo purchase = new PurchaseVo();
	// purchase.setPurchaseNo(getNewPurchaseNo());
	// purchase.setUserEmail(userEmail);
	//
	// List<CartVo> itemList = cart.getCartList();
	// for (int i = 0; i < itemList.size(); i++) {
	// CartVo itemSet = (CartVo) itemList.get(i);
	// int purchaseLineNo = i + 1;
	// PurchaseLineVo purchaseLine = createPurchaseLineVo(purchase,
	// purchaseLineNo, itemSet);
	// purchase.addPurchaseLine(purchaseLine);
	// }
	// return purchase;
	//
	// }
	//

	// @Override
	// public void checkout(UseCart cart, String receiver,
	// String recpostcode, String recphone, String recaddr, String remarks) {
	// // TODO Auto-generated method stub
	// PurchaseVo purchase = createPurchase(cart, receiver, recpostcode,
	// recphone,
	// recaddr, remarks);
	// entryPurchase(purchase);
	//
	// }
	//
	// private PurchaseVo createPurchase(UseCart cart,String receiver,
	// String recpostcode, String recphone, String recaddr, String remarks) {
	// PurchaseVo purchase = new PurchaseVo();
	//
	// List<CartVo> itemList = cart.getCartList();
	//
	//
	// for (int i = 0; i < itemList.size(); i++) {
	// CartVo itemSet = (CartVo) itemList.get(i);
	// int purchaseLineNo = i + 1;
	// PurchaseLineVo purchaseLine = createPurchaseLineVo(purchase,
	// purchaseLineNo, itemSet);
	// purchase.addPurchaseLine(purchaseLine);
	// }
	//
	// purchase.setRecaddr(recaddr);
	// purchase.setReceiver(receiver);
	// purchase.setRecphone(recphone);
	// purchase.setRecpostcode(recpostcode);
	// purchase.setRemarks(remarks);
	// return purchase;
	//
	// }

	// @Override
	// public void entryCart(CartVo cart) {
	// // TODO Auto-generated method stub
	// this.cartCatalog.entryCart(cart);
	// }
	//
	// @Override
	// public List<CartVo> getCartList(String userEmail) {
	// // TODO Auto-generated method stub
	// return this.cartCatalog.getCartList(userEmail);
	// }

}
