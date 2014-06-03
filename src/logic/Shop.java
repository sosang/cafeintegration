package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface Shop {
	MemberVo getMemberByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	void entryMember(MemberVo member);
	List<MemberVo> getMemberList(HttpServletRequest request, Integer pageNo);
	List<ItemVo> getItemList();

	ItemVo getItemByItemNo(Integer itemNo);

	void entryCart(CartVo cart);

	List<CartVo> getCartList(String userEmail);

	void clearCart(String userEmail);

	void checkout(String userEmail, String receiver,
			String recphone, String recaddr, String recpostcode, String remarks);

	void mypageCartclear(Integer itemNo);
	
	List<PurchaseListVo> mypagePurchase(String userEmail);
	MemberVo getCheckedUserEmail(String userEmail);	//userEmail 중복체크용
}