package dao;

import java.util.List;




import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import logic.CartVo;
@Repository
public class CartDaoImpl implements CartDao {
	
	private static final String SELECT_CART_BY_USERID="SELECT cart_num,user_email_item_no,cart_num_of_product,cart_sub_total from cart where user_email = ?";
	private static final String INSERT="INSERT INTO cart(cart_num,user_email,item_no,cart_num_of_product,cart_sub_total)values(cart_seq.nextval,?,?,?,?)";
	private static final String Delete="DELETE FROM cart where user_email=?";
	private SimpleJdbcTemplate template;
	
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
	}
	@Override
	public List<CartVo> findAll(String userEmail) {
		// TODO Auto-generated method stub
		RowMapper<CartVo> mapper = new BeanPropertyRowMapper<CartVo>(CartVo.class);
		return this.template.query(SELECT_CART_BY_USERID, mapper, userEmail);
	}

	@Override
	public void create(CartVo cart) {
		this.template.update(CartDaoImpl.INSERT, cart.getUserEmail(),cart.getItemVo().getItemNo(),cart.getCartNumOfProduct(),cart.getCartSubTotal());
		
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(String userEmail) {
		// TODO Auto-generated method stub
		this.template.update(Delete, userEmail);
	}


}
