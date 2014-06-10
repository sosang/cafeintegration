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


	private static final String SELECT_BY_USEREMAIL_PASSWD = "SELECT * from member where user_email=? AND user_passwd=?";


	private static final String INSERT = "INSERT INTO member (user_email, user_passwd, user_alias, user_phone1,user_phone2,user_phone3, user_postcode, user_address1, user_address2)"
			+ " VALUES(:userEmail, :userPasswd, :userAlias, :userPhone1,:userPhone2,:userPhone3, :userPostcode, :userAddress1, :userAddress2)";


	
	
	private static final String CHECK_USER_EMAIL = "SELECT count(*) from member where user_email=?";

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
	public int checkUserEmail(String userEmail) {
		return this.template.queryForInt(CHECK_USER_EMAIL, userEmail);
	}


	@Override
	public MemberVo findmember(String userEmail) {
		// TODO Auto-generated method stub
		return null;
	}


}