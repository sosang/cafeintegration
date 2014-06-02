package dao;

import java.util.List;


import logic.ItemVo;

public interface ItemDao {
	List<ItemVo> findAll();

	ItemVo findByPromaryKey(Integer itemNo);
}
