package dao;

import logic.MemberVo;

public interface Member_DAO {
	void create(MemberVo member);
	MemberVo findByUserEmailAndUserPasswd(String userEmail, String userPasswd);

}