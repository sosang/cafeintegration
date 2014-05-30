package logic;

public interface MemberCatalog {
	void entryMember(MemberVo member);
	MemberVo getMemberByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	MemberVo getCheckedUserEmail(String userEmail);
}
