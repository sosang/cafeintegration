package dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import logic.AdminVo;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	private SimpleJdbcTemplate template;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		
	}
	
	private static final String SELECT_BY_USEREMAIL_PASSWD = "SELECT * from adminstrator where admin_email=? AND admin_passwd=?";
	@Override
	public AdminVo getAdminInfo(String adminEmail, String adminPasswd) {
		RowMapper<AdminVo> mapper = new BeanPropertyRowMapper<AdminVo>(AdminVo.class);
		return this.template.queryForObject(SELECT_BY_USEREMAIL_PASSWD, mapper, adminEmail, adminPasswd);
	}

}
