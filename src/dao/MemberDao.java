package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.MemberVo;

public interface MemberDao {
	void create(MemberVo member);
	MemberVo findByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	int checkUserEmail(String userEmail);	//userEmail 중복 체크
	int checkUserAlias(String UserAlias);	//userAlias(별명) 중복 체크
	List<MemberVo> findAllMemberList(HttpServletRequest request, Integer pageNo);
	MemberVo findmemberinfo(String userEmail);
}
