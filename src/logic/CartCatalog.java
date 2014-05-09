package logic;

import java.util.List;

public interface CartCatalog {
//	void entryCart(CartVo cart);
//	
	List<CartVo> getCartList(String userEmail);
	
	void entryCart(CartVo cart);
	
//	List<CartVo> getCartList(String userEmail);
	
	void clearCart(String userEmail);
	
	void somedeleteCart(Integer itemNo);
	CartVo getCart(String userEmail, Integer itemNo);
}
