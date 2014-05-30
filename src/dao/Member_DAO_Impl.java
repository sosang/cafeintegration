package dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import logic.MemberVo;

@Repository
public class Member_DAO_Impl implements Member_DAO {

	private static final String SELECT_BY_USEREMAIL_PASSWD = "SELECT user_email, user_passwd, user_alias,"
			+ " user_phone_num1, user_phone_num2, user_phone_num3, user_postcode, user_address1, user_address2, user_level, user_point, user_num_of_article, user_num_of_reply, user_num_of_practice"
			+ " from member where user_email=? AND user_passwd=?";

	private static final String INSERT = "INSERT INTO member (user_email, user_passwd, user_alias, user_phone_num1, user_phone_num2, user_phone_num3, user_postcode, user_address1, user_address2)"
			+ " VALUES(:userEmail, :userPasswd, :userAlias, :userPhone1, :userPhone2, :userPhone3, :userPostcode, :userAddress1, :userAddress2)";

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
		this.template.update(Member_DAO_Impl.INSERT, parameterSource);
	}

}