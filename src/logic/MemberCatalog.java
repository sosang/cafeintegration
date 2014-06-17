package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface MemberCatalog {
	void entryMember(MemberVo member);	//회원가입
	MemberVo getMemberByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	int getCheckedUserEmail(String userEmail);	//회원 Email 중복 체크
	int getCheckedUserAlias(String userAlias);	//회원 별명(Alias) 중복 체크
	List<MemberVo> findAllMember(HttpServletRequest request, Integer pageNo);
	MemberVo getfindMemberInfo(String userEmail);
	
}
