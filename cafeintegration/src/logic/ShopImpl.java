package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopImpl implements Shop {

	@Autowired
	private MemberCatalog memberCatalog;
	@Autowired
	private ItemCatalog itemCatalog;

	@Autowired
	private CartCatalog cartCatalog;

	@Autowired
	private PurchaseCatalog purchaseCatalog;

	@Override
	public MemberVo getMemberByUserEmailAndUserPasswd(String userEmail,
			String userPasswd) {
		// TODO Auto-generated method stub
		return this.memberCatalog.getMemberByUserEmailAndUserPasswd(userEmail, userPasswd);
	}

	@Override
	public void entryMember(MemberVo member) {
		// TODO Auto-generated method stub
		this.memberCatalog.entryMember(member);

	}

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
	public UseCart getCart() {
		// TODO Auto-generated method stub
		return new UseCart();
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
	public void checkout(UseCart cart,String userEmail, String receiver,
			String recpostcode, String recphone, String recaddr, String remarks) {
		// TODO Auto-generated method stub
		PurchaseVo purchase = createPurchase(cart,userEmail, receiver, recpostcode, recphone,
				recaddr, remarks);
		entryPurchase(purchase);

	}

	private PurchaseVo createPurchase(UseCart cart,String userEmail,String receiver,
			String recpostcode, String recphone, String recaddr, String remarks) {
		PurchaseVo purchase = new PurchaseVo();
		
		purchase.setUserEmail(userEmail);
		purchase.setPurchaseNo(getNewPurchaseNo());
		purchase.setRecaddr(recaddr);
		purchase.setReceiver(receiver);
		purchase.setRecphone(recphone);
		purchase.setRecpostcode(recpostcode);
		purchase.setRemarks(remarks);
        
		List<CartVo> itemList = cart.getCartList();

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
	public MemberVo getCheckedUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return this.memberCatalog.getCheckedUserEmail(userEmail);
	}
}
