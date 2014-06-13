package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.MemberVo;

public interface MemberDao {
	void create(MemberVo member);	//회원가입
	MemberVo findByUserEmailAndUserPasswd(String userEmail, String userPasswd);
	int checkUserEmail(String userEmail);	//회원Email 중복체크
	int checkUserAlias(String UserAlias);	//회원 별명 중복체크
	List<MemberVo> findAllMemberList(HttpServletRequest request, Integer pageNo);
//	List<MemberVo> memberLocationMap();		//가입회원 지역정보
}
