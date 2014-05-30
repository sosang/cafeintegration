package logic;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ItemDao;

@Service
public class ItemCatalogImpl implements ItemCatalog {
	
	@Autowired
	private ItemDao itemDao;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public List<ItemVo> getItemList() {
		// TODO Auto-generated method stub
		return this.itemDao.findAll();
	}

	@Override
	public ItemVo getItemByItemNo(Integer itemNo) {
		// TODO Auto-generated method stub
		return this.itemDao.findByPromaryKey(itemNo);
	}

}
