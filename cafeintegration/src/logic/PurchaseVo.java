package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseVo {

	private List<PurchaseLineVo> purchaseList = new ArrayList<PurchaseLineVo>();
	
	private Integer purchaseNo;
	private String userEmail;
	private Date timeOfPurchase;
	private Date purCanceltime;
	private Integer purSent;
	private String receiver;
	private String recphone;
	private String recaddr;
	private String recpostcode;
	private String remarks;

	public void addPurchaseLine(PurchaseLineVo purchaseLine){
		this.purchaseList.add(purchaseLine);
	}

	public List<PurchaseLineVo> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseLineVo> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public Integer getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(Integer purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getTimeOfPurchase() {
		return timeOfPurchase;
	}

	public void setTimeOfPurchase(Date timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}

	public Date getPurCanceltime() {
		return purCanceltime;
	}

	public void setPurCanceltime(Date purCanceltime) {
		this.purCanceltime = purCanceltime;
	}

	public Integer getPurSent() {
		return purSent;
	}

	public void setPurSent(Integer purSent) {
		this.purSent = purSent;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getRecphone() {
		return recphone;
	}

	public void setRecphone(String recphone) {
		this.recphone = recphone;
	}

	public String getRecaddr() {
		return recaddr;
	}

	public void setRecaddr(String recaddr) {
		this.recaddr = recaddr;
	}

	public String getRecpostcode() {
		return recpostcode;
	}

	public void setRecpostcode(String recpostcode) {
		this.recpostcode = recpostcode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	

}
