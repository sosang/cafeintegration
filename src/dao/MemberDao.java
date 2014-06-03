package dao;

import logic.MemberVo;

public interface MemberDao {
	void create(MemberVo member);
	MemberVo findByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	int checkUserEmail(String userEmail);
}
