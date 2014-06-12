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
	private static final String SELECT_BY_USEREMAIL_PASSWD = "SELECT * from member where user_email=? AND user_passwd=?";

	// 신규 사용자 계정 생성
	private static final String INSERT = "INSERT INTO member (user_email, user_passwd, user_alias, user_phone1,user_phone2,user_phone3, user_postcode, user_address1, user_address2)"
			+ " VALUES(:userEmail, :userPasswd, :userAlias, :userPhone1,:userPhone2,:userPhone3, :userPostcode, :userAddress1, :userAddress2)";

	// 이메일 중복 체크용
	private static final String CHECK_USER_EMAIL = "SELECT user_email from member where user_email=?";

	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public MemberVo findByUserEmailAndUserPasswd(String userEmail,
			String userPasswd) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(
				MemberVo.class);
		return this.template.queryForObject(SELECT_BY_USEREMAIL_PASSWD, mapper,
				userEmail, userPasswd);
	}

	@Override
	public void create(MemberVo member) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				member);
		this.template.update(MemberDaoImpl.INSERT, parameterSource);
	}

	@Override
	public List<MemberVo> checkUserEmail(String userEmail) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(
				MemberVo.class);
		List<MemberVo> user = null;

		user = this.template.query(CHECK_USER_EMAIL, mapper, userEmail);
		return user;
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

			System.out.println(memberList.get(0).getUserAddress1());
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
				MemberDaoImpl.SELECT_ALL_BY_USEREMAIL.toString(), mapper, userEmail);
		

		return memberList;
	}

}