package dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import logic.PurchaseListVo;
import logic.PurchaseVo;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

	private static final String SELECT_MAX_PURCHASENO = "SELECT MAX(pur_no)AS pur_no from purchase";

	private static final String INSERT = "INSERT into purchase (pur_no,user_email,receiver,rec_phone,rec_addr,rec_postcode,remarks) values (?,?,?,?,?,?,?)";
	// private static final String INSERT =
	// "INSERT INTO PURCHASE (pur_no,user_email) values (?,?)";

	private static final String SELECT_FROM_USER_EMAIL = "select b.num_of_product, a.time_of_purchase, c.item_name, c.price from PURCHASE a , PURCHASE_LINE b , item c where a.user_email=? and b.item_no=c.item_no and a.pur_no=b.pur_no order by a.pur_no, b.pur_line_no";

	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public void create(PurchaseVo purchaseVo) {
		// TODO Auto-generated method stub
		this.template.update(INSERT, purchaseVo.getPurchaseNo(),
				purchaseVo.getUserEmail(), purchaseVo.getReceiver(),
				purchaseVo.getRecphone(), purchaseVo.getRecaddr(),
				purchaseVo.getRecpostcode(), purchaseVo.getRemarks());

	}

	// @Override
	// public void create(PurchaseVo purchaseVo) {
	// // TODO Auto-generated method stub
	// this.template.update(PurchaseDaoImpl.INSERT, purchaseVo.getPurchaseNo(),
	// purchaseVo.getUserEmail());
	//
	// }

	@Override
	public Integer findMaxPurchaseNo() {
		return this.template.queryForInt(PurchaseDaoImpl.SELECT_MAX_PURCHASENO);
		// TODO Auto-generated method stub

	}

	@Override
	public List<PurchaseListVo> findList(String userEmail) {
		// TODO Auto-generated method stub

		RowMapper<PurchaseListVo> mapper = new BeanPropertyRowMapper<PurchaseListVo>(
				PurchaseListVo.class);
		return this.template.query(SELECT_FROM_USER_EMAIL, mapper, userEmail);
	}

}
