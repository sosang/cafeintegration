package dao;

import java.util.List;

import logic.PurchaseListVo;
import logic.PurchaseVo;

public interface PurchaseDao {

	
	void create(PurchaseVo purchaseVo); 
	
	Integer findMaxPurchaseNo();
	
	List<PurchaseListVo> findList(String userEmail);


}
