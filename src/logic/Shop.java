package logic;

import java.util.List;

public interface Shop {
	MemberVo getMemberByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	void entryMember(MemberVo member);
	List<ItemVo> getItemList();

	ItemVo getItemByItemNo(Integer itemNo);

	void entryCart(CartVo cart);

	List<CartVo> getCartList(String userEmail);

	void clearCart(String userEmail);

	void checkout(String userEmail, String receiver,
			String recphone, String recaddr, String recpostcode, String remarks);

	void mypageCartclear(Integer itemNo);
	
	List<PurchaseListVo> mypagePurchase(String userEmail);
	int getCheckedUserEmail(String userEmail);	
	
	MemberVo getMember(String userEmail);
	
	// void entryCart(CartVo cart);
	//
	// List<CartVo> getCartList(String userEmail);

	// void checkout(UseCart cart, String recevier, String recpostcode,
	// String recphone, String recaddr, String remakrs);

	// void checkout(UseCart cart,String userEmail);
}