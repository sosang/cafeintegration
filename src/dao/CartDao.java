package dao;

import java.util.List;


import logic.CartVo;

public interface CartDao {
	List<CartVo> findAll(String userEmail);

	//
	// void create(CartVo cart);

	// List<CartItem> findAll(String userEmail);

	void create(CartVo cart);

	void delete(String userEmail);

	void mypagedelete(Integer itemNo);

}
