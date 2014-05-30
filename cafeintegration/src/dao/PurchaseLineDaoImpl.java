package dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import logic.PurchaseLineVo;

@Repository
public class PurchaseLineDaoImpl implements PurchaseLineDao {

	private static final String INSERT = "INSERT INTO PURCHASE_LINE(pur_line_no, pur_no, item_no, num_of_product) values(?,?,?,?)";

	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public void create(PurchaseLineVo purchaseLine) {
		// TODO Auto-generated method stub
		this.template.update(PurchaseLineDaoImpl.INSERT, purchaseLine.getPurchaseLineNo(),
				purchaseLine.getPurchase().getPurchaseNo(), purchaseLine
						.getItem().getItemNo(), purchaseLine.getNumOfProduct());
	}
}
