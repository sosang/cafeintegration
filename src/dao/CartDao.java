package dao;

import java.util.List;


import logic.CartVo;

public interface CartDao {
	List<CartVo> findAll(String userEmail);
	
	CartVo findcart(String userEmail, Integer itemNo);

	//
	// void create(CartVo cart);

	// List<CartItem> findAll(String userEmail);

	void create(CartVo cart);

	void delete(String userEmail);

	void mypagedelete(Integer itemNo);

}
