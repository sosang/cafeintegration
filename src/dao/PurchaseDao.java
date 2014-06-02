package dao;

import java.util.List;

import logic.NumNo;
import logic.PurchaseListVo;
import logic.PurchaseVo;

public interface PurchaseDao {
	
	List<NumNo> findNum();
	
	void create(PurchaseVo purchaseVo); 
	
	Integer findMaxPurchaseNo();
	
	List<PurchaseListVo> findList(String userEmail);


}
