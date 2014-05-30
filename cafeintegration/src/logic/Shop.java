package logic;

import java.util.List;

import logic.MemberVo;

public interface Shop {
	MemberVo getMemberByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	void entryMember(MemberVo member);
	List<ItemVo> getItemList();

	ItemVo getItemByItemNo(Integer itemNo);

	UseCart getCart();

	void entryCart(CartVo cart);

	void clearCart(String userEmail);

	List<CartVo> getCartList(String userEmail);

	void checkout(UseCart cart, String userEmail,String recevier, String recpostcode,
			String recphone, String recaddr, String remakrs);
	
	MemberVo getCheckedUserEmail(String userEmail);	//userEmail 중복체크용
}
