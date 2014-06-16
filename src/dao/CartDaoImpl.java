package dao;

import java.util.List;

import javax.sql.DataSource;

import logic.CartVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements CartDao {

	private static final String SELECT_CART_BY_USERID = "select a.user_email, a.item_no ,b.item_name, b.photo ,b.price , sum(a.cart_num_of_product) cart_num_of_product, sum(a.cart_sub_total) cart_sub_total from cart a, item b where a.user_email=? and a.item_no=b.item_no group by a.user_email,a.item_no,b.item_name,b.photo,b.price ";
	private static final String SELECT_CART_BY_USERID_ITEM_NO = "select a.user_email, a.item_no ,b.item_name, b.photo ,b.price , sum(a.cart_num_of_product) cart_num_of_product, sum(a.cart_sub_total) cart_sub_total from cart a, item b where a.user_email=? and a.item_no=? and a.item_no=b.item_no group by a.user_email,a.item_no,b.item_name,b.photo,b.price ";
	private static final String INSERT = "INSERT INTO cart(cart_num,user_email,item_no,cart_num_of_product,cart_sub_total)values(cart_seq.nextval,?,?,?,?)";
	private static final String DELETE = "DELETE FROM cart where user_email=?";
	private static final String DELETE_MYPAGE = "DELETE from cart where item_no=?";
	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public List<CartVo> findAll(String userEmail) {
		// TODO Auto-generated method stub
		RowMapper<CartVo> mapper = new BeanPropertyRowMapper<CartVo>(
				CartVo.class);
		return this.template.query(SELECT_CART_BY_USERID, mapper, userEmail);
	}

	@Override
	public CartVo findcart(String userEmail, Integer itemNo) {
		RowMapper<CartVo> mapper = new BeanPropertyRowMapper<CartVo>(
				CartVo.class);

		return this.template.queryForObject(SELECT_CART_BY_USERID_ITEM_NO,
				mapper, userEmail, itemNo);
	}

	@Override
	public void create(CartVo cart) {
		this.template.update(CartDaoImpl.INSERT, cart.getUserEmail(),
				cart.getItemNo(), cart.getCartNumOfProduct(),
				cart.getCartSubTotal());
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(String userEmail) {
		// TODO Auto-generated method stub
		this.template.update(DELETE, userEmail);
	}

	@Override
	public void mypagedelete(Integer itemNo) {
		// TODO Auto-generated method stub
		this.template.update(DELETE_MYPAGE, itemNo);

	}

}
