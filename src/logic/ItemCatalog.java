package logic;

import java.util.List;

public interface ItemCatalog {
	List<ItemVo> getItemList();

	ItemVo getItemByItemNo(Integer itemNo);
}
