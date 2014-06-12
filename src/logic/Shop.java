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
	int getCheckedUserEmail(String userEmail);	//userEmail 중복체크용
	int getCheckedUserAlias(String userAlias);	//userAlias 중복체크용
	void itemReg(ItemVo itemVo);	// 아이템 등록
	int getNewItemNo();	// 최신상품번호 얻기
	void setFilePath(int newItemNo, String forDb);	// 상품 이미지 등록
	void itemUpdate(ItemVo itemVo, Integer itemNo);	// 상품내용 수정
	void updateFilePath(Integer itemNo, String forDb);	// 수정된 상품 내용의 이미지파일 경로 갱신
	String getFilePathTo(Integer itemNo);	// 세부내용 보기를 위한 파일경로 불르기
	void deleteBoth(Integer itemNo);	// 상품 삭제
	
}