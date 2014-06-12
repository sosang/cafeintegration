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

	// 새로운 아이템 등록 
	@Override
	public void setNewItem(ItemVo itemVo, String forDb) {
		// TODO Auto-generated method stub
		this.itemDao.regNewItem(itemVo, forDb);
	}

	//	새로운 아이템 번호 얻기
	@Override
	public int getNewItemNo() {
		// TODO Auto-generated method stub
		return this.itemDao.getNewItemNo();
	}

	// 상품 이미지 경로 저장하기 
	@Override
	public void setFilePath(int newItemNo, String forDb) {
		// TODO Auto-generated method stub
		this.itemDao.setItemImageFilePath(newItemNo, forDb);
	}

	// 상품 내용 수정

	@Override
	public void itemUpdate(ItemVo itemVo, Integer itemNo) {
		// TODO Auto-generated method stub
		this.itemDao.itemUpdate(itemVo, itemNo);
	}

	// 수정된 상품의 이미지파일 경로 수정
	@Override
	public void updateFilePath(Integer itemNo, String forDb) {
		// TODO Auto-generated method stub
		this.itemDao.updateFilePath(itemNo, forDb);
	}

	// 파일 경로얻기
	@Override
	public String getFilePathTo(Integer itemNo) {
		// TODO Auto-generated method stub
		return this.itemDao.getFilePathTo(itemNo);
	}

	// 상품삭제
	@Override
	public void delete(Integer itemNo) {
		// TODO Auto-generated method stub
		this.itemDao.deleteItem(itemNo);
	}

	@Override
	public List<SaveFilePathTo> findAll_photo() {
		// TODO Auto-generated method stub
		return this.itemDao.findAll_photo();
	}
}