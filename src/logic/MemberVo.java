package logic;

import java.io.Serializable;

public class MemberVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userEmail;
	private String userPasswd;
	private String userAlias;
	private String userPhone1;
	private String userPhone2;
	private String userPhone3;
	private String userPostcode;
	private String userAddress1;
	private String userAddress2;
	private int userLevel;
	private int userPoint;
	private int userNumOfArticle;
	private int userNumOfReply;
	private int userNumOfPractice;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	public String getUserPhone1() {
		return userPhone1;
	}
	public void setUserPhone1(String userPhone1) {
		this.userPhone1 = userPhone1;
	}
	public String getUserPhone2() {
		return userPhone2;
	}
	public void setUserPhone2(String userPhone2) {
		this.userPhone2 = userPhone2;
	}
	public String getUserPhone3() {
		return userPhone3;
	}
	public void setUserPhone3(String userPhone3) {
		this.userPhone3 = userPhone3;
	}
	public String getUserPostcode() {
		return userPostcode;
	}
	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}
	public String getUserAddress1() {
		return userAddress1;
	}
	public void setUserAddress1(String userAddress1) {
		this.userAddress1 = userAddress1;
	}
	public String getUserAddress2() {
		return userAddress2;
	}
	public void setUserAddress2(String userAddress2) {
		this.userAddress2 = userAddress2;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	public int getUserNumOfArticle() {
		return userNumOfArticle;
	}
	public void setUserNumOfArticle(int userNumOfArticle) {
		this.userNumOfArticle = userNumOfArticle;
	}
	public int getUserNumOfReply() {
		return userNumOfReply;
	}
	public void setUserNumOfReply(int userNumOfReply) {
		this.userNumOfReply = userNumOfReply;
	}
	public int getUserNumOfPractice() {
		return userNumOfPractice;
	}
	public void setUserNumOfPractice(int userNumOfPractice) {
		this.userNumOfPractice = userNumOfPractice;
	}


}