package dao;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import logic.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {

	// 사용자 이메일 및 비밀번호 체크
	private static final String SELECT_BY_USEREMAIL_PASSWD = "SELECT * from member where user_email=? AND user_passwd=? and user_level=0";

	// 신규 사용자 계정 생성
	private static final String INSERT = "INSERT INTO member (user_email, user_passwd, passwd_inquiry, passwd_answer, user_alias, user_phone1,user_phone2,user_phone3, user_postcode, user_address1, user_address2)"
			+ " VALUES(:userEmail, :userPasswd, :passwdInquiry, :passwdAnswer, :userAlias, :userPhone1,:userPhone2,:userPhone3, :userPostcode, :userAddress1, :userAddress2)";

	private static final String CHANGE_INFO = "UPDATE member set  user_passwd=?, user_phone1=?, user_phone2=?, user_phone3=?, user_postcode=?, user_address1=?, user_address2=? WHERE user_email = ? ";
	private static final String OUT_MEMBER = "UPDATE member set  user_level=1 WHERE user_email = ? ";
	// 이메일 중복 체크용
	private static final String CHECK_USER_EMAIL = "SELECT count(*) from member where user_email=?";

	// 별명 중복 체크용
	private static final String CHECK_USER_ALIAS = "SELECT count(*) from member where user_alias=?";

	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// email과 비밀번호로 유저 클래스 생성
	@Override
	public MemberVo findByUserEmailAndUserPasswd(String userEmail,
			String userPasswd) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(
				MemberVo.class);
		return this.template.queryForObject(SELECT_BY_USEREMAIL_PASSWD, mapper,
				userEmail, userPasswd);
	}

	// 회원가입
	@Override
	public void create(MemberVo member) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				member);
		this.template.update(MemberDaoImpl.INSERT, parameterSource);
	}

	// 회원 email 중복 체크
	@Override
	public int checkUserEmail(String userEmail) {

		int result = this.template.queryForInt(CHECK_USER_EMAIL, userEmail);
		return result;
	}

	// 회원가입시 별명 중복 체크
	@Override
	public int checkUserAlias(String userAlias) {
		int result = this.template.queryForInt(CHECK_USER_ALIAS, userAlias);
		return result;
	}

	// 어드민용 총 가입자 수
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer(
			"select count(*) from member");

	private Integer getTotalCount() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_TOTAL_COUNT.toString());
	}

	// 어드민용 이달의 가입자 수
	private static final StringBuffer GET_TOTAL_COUNT_BY_THIS_MONTH = new StringBuffer(
			"select count(*) from member where substr(to_char(sysdate,'YYYYMMDD'),1,6) = substr(to_char(join_date,'YYYYMMDD'),1,6)");

	private Integer getTotalCountByThisMonth() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_TOTAL_COUNT_BY_THIS_MONTH
				.toString());
	}

	// 어드민용 회원 목록
	private static final StringBuffer SELECT_ALL = new StringBuffer(
			"select * from (select rownum rnum, user_email userEmail, user_alias userAlias, user_phone1 userPhone1,user_phone2 userPhone2,user_phone3 userPhone3, user_postcode userPostcode, user_address1 userAddress1, user_address2 userAddress2, user_level userLevel, user_point userPoint, user_num_of_article userNumOfArticle, user_num_of_comments userNumOfReply, user_num_of_practice userNumOfPractice, join_date userJoinDate from (select * from member )) where rnum>=? and rnum<=?");

	@Override
	public List<MemberVo> findAllMemberList(HttpServletRequest request,
			Integer pageNo) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(
				MemberVo.class);

		int limit = 10;
		Integer startrow = (pageNo - 1) * limit + 1;
		Integer endrow = startrow + limit - 1;
		int listCount = 0;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		List<MemberVo> memberList = null;
		listCount = this.getTotalCount();
		if (listCount > 0) {
			memberList = this.jdbcTemplate.query(
					MemberDaoImpl.SELECT_ALL.toString(), mapper, startrow,
					endrow); // 현재 페이지에 해당하는 글 목록
		} else {
			memberList = Collections.emptyList();
		}
		int maxPage = (int) ((double) listCount / limit + 0.95); // 올림처리

		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등)
		int startPage = (((int) ((double) pageNo / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10,20,30 등)
		int endPage = maxPage;
		if (endPage > startPage + 10 - 1) {
			endPage = startPage + 10 - 1;
		}

		// 공지사항 패이지에 보여줄 속성들 셋팅
		request.setAttribute("pageNo", new Integer(pageNo));
		request.setAttribute("maxPage", new Integer(maxPage));
		request.setAttribute("startPage", new Integer(startPage));
		request.setAttribute("endPage", new Integer(endPage));
		request.setAttribute("listCount", new Integer(listCount));
		request.setAttribute("thisMonth", this.getTotalCountByThisMonth());
		request.setAttribute("memberList", memberList);

		return memberList;
	}

	private static final StringBuffer SELECT_ALL_BY_USEREMAIL = new StringBuffer(
			"select user_email userEmail,user_passwd userPasswd, user_alias userAlias, user_phone1 userPhone1, user_phone2 userPhone2, user_phone3 userPhone3, user_postcode userPostcode, user_address1 userAddress1, user_address2 userAddress2 from member where user_email=?");

	@Override
	public MemberVo findmemberinfo(String userEmail) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(
				MemberVo.class);
		MemberVo memberList = null;
		memberList = this.jdbcTemplate.queryForObject(
				MemberDaoImpl.SELECT_ALL_BY_USEREMAIL.toString(), mapper,
				userEmail);

		return memberList;
	}

	// 비번찾긔
	private static final StringBuffer LOOKING_FOR_PWD = new StringBuffer(
			"SELECT * from member where user_email=? and passwd_inquiry=? and passwd_answer=?");

	@Override
	public MemberVo lookingForPwd(String userEmail, String passwdInquiry,
			String passwdAnswer) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(
				MemberVo.class);
		return this.template.queryForObject(LOOKING_FOR_PWD.toString(), mapper,
				userEmail, passwdInquiry, passwdAnswer);

	}

	@Override
	public void changeInfo(MemberVo memberVo, String userEmail) {
		// TODO Auto-generated method stub
		this.template.update(CHANGE_INFO, memberVo.getUserPasswd(),
				memberVo.getUserPhone1(), memberVo.getUserPhone2(),
				memberVo.getUserPhone3(), memberVo.getUserPostcode(),
				memberVo.getUserAddress1(), memberVo.getUserAddress2(),
				userEmail);
	}

	@Override
	public void outMember(String userEmail) {
		this.template.update(OUT_MEMBER, userEmail);
		
	}

}