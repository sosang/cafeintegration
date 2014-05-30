package dao;

import java.util.List;

import javax.sql.DataSource;

import logic.ItemVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {
	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);

	}

	private static final String SELECT_ALL = "SELECT item_no, item_name, origin, grade, processing, roasting_date, roasting_level, item_info, photo, price, total_product, def_exchange, def_refund FROM item";

	@Override
	public List<ItemVo> findAll() {
		// TODO Auto-generated method stub
		RowMapper<ItemVo> mapper = new BeanPropertyRowMapper<ItemVo>(
				ItemVo.class);

		return this.template.query(ItemDaoImpl.SELECT_ALL, mapper);
	}

	private static final String SELECT_BY_PRIMARY_KEY = "SELECT item_no, item_name, origin, grade, processing, roasting_date, roasting_level, item_info, photo, price, total_product, def_exchange, def_refund FROM item WHERE item_no = ?";

	@Override
	public ItemVo findByPromaryKey(Integer itemNo) {
		// TODO Auto-generated method stub
		RowMapper<ItemVo> mapper = new BeanPropertyRowMapper<ItemVo>(
				ItemVo.class);

		return this.template.queryForObject(SELECT_BY_PRIMARY_KEY, mapper,
				itemNo);
	}

}
