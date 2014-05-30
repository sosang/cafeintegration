package logic;

import java.util.ArrayList;
import java.util.List;

public class UseCart {
	private List<CartVo> cartList = new ArrayList<CartVo>();
	
	public List<CartVo> getCartList(){
		return this.cartList;
	}
	
	public boolean isEmpty(){
		if(this.cartList==null||this.cartList.isEmpty()){
			return true;
		}
		return false;
	}
	
	public void push(CartVo pushedCart){
		boolean itemExistInCart = false;
		
		ItemVo pushedItem = pushedCart.getItemVo();
		int pushedItemNo = pushedItem.getItemNo().intValue();
		
		for(CartVo cartItemSet : this.cartList){
			ItemVo cartItem = cartItemSet.getItemVo();
			int cartItemNo = cartItem.getItemNo().intValue();
			
			if(cartItemNo == pushedItemNo){
				cartItemSet.addCartNumOfProduct(pushedCart.getCartNumOfProduct());
				itemExistInCart = true;
				break;
				
			}
			
			
			
		}
		if(!itemExistInCart){
			this.cartList.add(pushedCart);
		}
	}

	public void clearAll(){
		this.cartList = new ArrayList<CartVo>();
	}
}
