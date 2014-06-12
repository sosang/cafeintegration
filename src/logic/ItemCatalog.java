package logic;

import java.util.List;

public interface ItemCatalog {
	List<ItemVo> getItemList();

	ItemVo getItemByItemNo(Integer itemNo);

	void setNewItem(ItemVo itemVo, String forDb); // 새로운 아이템 등록

	int getNewItemNo(); // 새로운 아이템 번호 얻기

	void setFilePath(int newItemNo, String forDb); // 아이템 이미지 경로 저장

	void itemUpdate(ItemVo itemVo, Integer itemNo); // 상품내용 수정

	void updateFilePath(Integer itemNo, String forDb); // 수정된 상품 내용의 이미지파일 경로 갱신

	String getFilePathTo(Integer itemNo); // 상품 세부를 위한 파일경로

	void delete(Integer itemNo); // 상품 삭제

	List<SaveFilePathTo> findAll_photo();

}