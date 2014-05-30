package dao;

import java.util.List;

import logic.PurchaseVo;

public interface PurchaseDao {
	List<PurchaseVo> findAll(String userEmail);
	
	void create(PurchaseVo purchaseVo); 
	
	Integer findMaxPurchaseNo();


}
