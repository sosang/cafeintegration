package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartDao;

@Service
public class CartCatalogImpl implements CartCatalog {

	@Autowired
	private CartDao cartDao;

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	@Override
	public void entryCart(CartVo cart) {
		// TODO Auto-generated method stub
		this.cartDao.create(cart);
	}

	@Override
	public List<CartVo> getCartList(String userEmail) {
		// TODO Auto-generated method stub
		return this.cartDao.findAll(userEmail);
	}

	@Override
	public void clearCart(String userEmail) {
		// TODO Auto-generated method stub
		this.cartDao.delete(userEmail);
	}

	@Override
	public void somedeleteCart(Integer itemNo) {
		// TODO Auto-generated method stub
		this.cartDao.mypagedelete(itemNo);

	}

	@Override
	public CartVo getCart(String userEmail, Integer itemNo) {

		return this.cartDao.findcart(userEmail, itemNo);
	}

}