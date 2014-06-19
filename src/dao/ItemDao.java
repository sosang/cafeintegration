package dao;

import java.util.List;











import logic.ItemVo;
import logic.SaveFilePathTo;

public interface ItemDao {
	List<ItemVo> findAll();

	ItemVo findByPromaryKey(Integer itemNo);

	void regNewItem(ItemVo itemVo, String forDb);	// 새로운 아이템 등록

	int getNewItemNo();	// 새로운 아이템 번호 얻기

	void setItemImageFilePath(int newItemNo, String forDb);	// 상품 이미지 경로 저장

	void itemUpdate(ItemVo itemVo, Integer itemNo, String forDb);	// 상품 내용 수정 

	void updateFilePath(Integer itemNo, String forDb);	// 수정된 상품 이미지 파일 경로 갱신

	String getFilePathTo(Integer itemNo);	// 파일경로 얻기

	void deleteItem(Integer itemNo);	// 상품삭제
	
    List<SaveFilePathTo> findAll_photo();
}
