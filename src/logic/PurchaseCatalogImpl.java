package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PurchaseDao;
import dao.PurchaseLineDao;

@Service
public class PurchaseCatalogImpl implements PurchaseCatalog {

	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private PurchaseLineDao purchaseLineDao;

	@Override
	public void entryPurchase(PurchaseVo purchase) {
		// TODO Auto-generated method stub
		this.purchaseDao.create(purchase);
		List<PurchaseLineVo> purchaseLineList = purchase.getPurchaseList();
		for (PurchaseLineVo purchaseLine : purchaseLineList) {
			this.purchaseLineDao.create(purchaseLine);
		}

	}

	@Override
	public Integer getNewPurchaseNo() {
		// TODO Auto-generated method stub
		
		int newPurchaseNo = this.purchaseDao.findMaxPurchaseNo().intValue()+1;
		return new Integer(newPurchaseNo);
	}

	@Override
	public List<PurchaseListVo> userPurchaseList(String userEmail) {
		// TODO Auto-generated method stub
		return this.purchaseDao.findList(userEmail);
	}


	// @Override
	// public Integer getNewPurchaseNo() {
	// int newPurchaseNo = this.purchaseDao.findMaxPurchaseNo().intValue()+1;
	// return new Integer(newPurchaseNo);
	// }
	//
	
	

}
