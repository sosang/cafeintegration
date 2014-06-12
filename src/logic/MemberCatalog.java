package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface MemberCatalog {
	void entryMember(MemberVo member);
	MemberVo getMemberByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	int getCheckedUserEmail(String userEmail);
	List<MemberVo> findAllMember(HttpServletRequest request, Integer pageNo);
	MemberVo getMember(String userEmail);
}
