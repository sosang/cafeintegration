package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	
	// 어드민에서 회원목록 불러오기
	@Override
	public List<MemberVo> getMemberList(HttpServletRequest request,
			Integer pageNo) {
		// TODO Auto-generated method stub
		return this.memberCatalog.findAllMember(request,pageNo);
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
	public List<MemberVo> getCheckedUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return this.memberCatalog.getCheckedUserEmail(userEmail);
	}

	// 아이템 등록하기
	@Override
	public void itemReg(ItemVo itemVo) {
		// TODO Auto-generated method stub
		this.itemCatalog.setNewItem(itemVo);
	}

	// 상품 새 번호 얻기
	@Override
	public int getNewItemNo() {
		// TODO Auto-generated method stub
		return this.itemCatalog.getNewItemNo();
	}

	// 상품 이미지 경로 저장
	@Override
	public void setFilePath(int newItemNo, String forDb) {
		// TODO Auto-generated method stub
		this.itemCatalog.setFilePath(newItemNo, forDb);
		
	}

	// 상품내용 수정
	@Override
	public void itemUpdate(ItemVo itemVo, Integer itemNo) {
		// TODO Auto-generated method stub
		this.itemCatalog.itemUpdate(itemVo, itemNo);
	}
	
	// 수정된 상품 내용의 이미지파일 경로 갱신
	@Override
	public void updateFilePath(Integer itemNo, String forDb) {
		// TODO Auto-generated method stub
		this.itemCatalog.updateFilePath(itemNo, forDb);
	}

	// 상품 세부내용을 위한 파일경로 불르기
	@Override
	public String getFilePathTo(Integer itemNo) {
		// TODO Auto-generated method stub
		return this.itemCatalog.getFilePathTo(itemNo);
	}

	// 상품 삭제
	@Override
	public void deleteBoth(Integer itemNo) {
		// TODO Auto-generated method stub
		this.itemCatalog.delete(itemNo);
	}

	@Override
	public MemberVo getMember(String userEmail) {
		// TODO Auto-generated method stub
		return this.memberCatalog.getMember(userEmail);
	}


	
}
