package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.MemberVo;

public interface MemberDao {
	void create(MemberVo member);
	MemberVo findByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	int checkUserEmail(String userEmail);
	List<MemberVo> findAllMemberList(HttpServletRequest request, Integer pageNo);
	MemberVo findmember(String userEmail);
	int checkUserAlias(String UserAlias);
}
