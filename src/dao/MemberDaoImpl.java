package dao;

import javax.sql.DataSource;



import logic.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class MemberDaoImpl implements MemberDao {

//	사용자 이메일 및 비밀번호 체크
	private static final String SELECT_BY_USEREMAIL_PASSWD = "SELECT * from member where user_email=? AND user_passwd=?";

//	신규 사용자 계정 생성
	private static final String INSERT = "INSERT INTO member (user_email, user_passwd, user_alias, user_phone1,user_phone2,user_phone3, user_postcode, user_address1, user_address2)"
			+ " VALUES(:userEmail, :userPasswd, :userAlias, :userPhone1,:userPhone2,:userPhone3, :userPostcode, :userAddress1, :userAddress2)";

//	이메일 중복 체크용
	private static final String CHECK_USER_EMAIL = "SELECT user_email from member where user_email=?";

	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
	}


	@Override
	public MemberVo findByUserEmailAndUserPasswd(String userEmail,
			String userPasswd) {
		RowMapper<MemberVo> mapper = new BeanPropertyRowMapper<MemberVo>(MemberVo.class);
		return this.template.queryForObject(SELECT_BY_USEREMAIL_PASSWD, mapper, userEmail, userPasswd);
	}


	@Override
	public void create(MemberVo member) {
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
		this.template.update(MemberDaoImpl.INSERT, parameterSource);
	}


	@Override
	public MemberVo checkUserEmail(String userEmail) {
		this.template.queryForInt(CHECK_USER_EMAIL, userEmail);
		System.out.println(this.template.queryForInt(CHECK_USER_EMAIL, userEmail));
		return new MemberVo();
	}

}