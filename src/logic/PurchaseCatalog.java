package logic;

import java.util.List;

public interface PurchaseCatalog {
	void entryPurchase(PurchaseVo purchase);
//	Integer getNewPurchaseNo();
	
	Integer getNewPurchaseNo();
	
	
	List<PurchaseListVo> userPurchaseList(String userEmail);

	List<NumNo> getNum();
}
