package dao;

import java.util.List;

import javax.sql.DataSource;

import logic.Postcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PostcodeDao {
		private static final String POSTCODE_AND_DEFAULT_ADDRESS = "SELECT zipcode, sido, sigungu,"
				+ " eupmyeondong, ri, beonji, bldg"
				+ " from postcode where eupmyeondong like ?"
				+ " ORDER BY zipcode, bldg asc";

		private SimpleJdbcTemplate template;

		@Autowired
		public void setDataSource(DataSource dataSource){
			this.template = new SimpleJdbcTemplate(dataSource);
		}


		public List<Postcode> findByEupmyeondong(String eupmyeondong) {
			//입력한 '읍/면/동' 정보를 통해 Postcode 객체(우편 번호 및 기본주소) 생성
			RowMapper<Postcode> mapper = new BeanPropertyRowMapper<Postcode>(Postcode.class);
			eupmyeondong = eupmyeondong + "%";
			List<Postcode> list = null;
			list = this.template.query(POSTCODE_AND_DEFAULT_ADDRESS, mapper, eupmyeondong);
			return list;
		}

}