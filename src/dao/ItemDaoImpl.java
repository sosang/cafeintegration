package dao;

import java.util.List;

import javax.sql.DataSource;

import logic.ItemVo;
import logic.SaveFilePathTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {
	private SimpleJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new SimpleJdbcTemplate(dataSource);

	}

	private static final String SELECT_ALL = "SELECT item_no, item_name, origin, grade, processing, roasting_date, roasting_level, item_info, photo,price, total_product, def_exchange, def_refund FROM item";

	@Override
	public List<ItemVo> findAll() {
		// TODO Auto-generated method stub
		RowMapper<ItemVo> mapper = new BeanPropertyRowMapper<ItemVo>(
				ItemVo.class);

		return this.template.query(ItemDaoImpl.SELECT_ALL, mapper);
	}

	private static final String SELECT_BY_PRIMARY_KEY = "SELECT item_no, item_name, origin, grade, processing, roasting_date, roasting_level, item_info, photo, price, total_product, def_exchange, def_refund FROM item WHERE item_no = ?";

	@Override
	public ItemVo findByPromaryKey(Integer itemNo) {
		// TODO Auto-generated method stub
		RowMapper<ItemVo> mapper = new BeanPropertyRowMapper<ItemVo>(
				ItemVo.class);

		return this.template.queryForObject(SELECT_BY_PRIMARY_KEY, mapper,
				itemNo);
	}
	
	
	// 새로운 아이템 등록
	private static final StringBuffer REG_NEW_ITEM = new StringBuffer("INSERT INTO item(item_no, item_name, origin, grade, processing, roasting_date, roasting_level, item_info, photo, price, total_product, def_exchange, def_refund) VALUES(item_seq.nextval,?,?,?,?,sysdate,?,?,?,?,?,?,?)");
	@Override
	public void regNewItem(ItemVo itemVo, String forDb) {
		// TODO Auto-generated method stub
		this.template.update(ItemDaoImpl.REG_NEW_ITEM.toString(), itemVo.getItemName(), itemVo.getOrigin(), itemVo.getGrade(), itemVo.getProcessing(), itemVo.getRoastingLevel(), itemVo.getItemInfo(),forDb, itemVo.getPrice(), itemVo.getTotalProduct(), 0,0);
		
	}
	
	// 후기게시판의 최근 게시물 번호
	private static final StringBuffer GET_RECENT_NO = new StringBuffer("SELECT max(item_no) FROM item");
	@Override
	public int getNewItemNo() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_RECENT_NO.toString());
	}
	
	
	// 파일경로 쓰기
	private static final StringBuffer SFPFN = new StringBuffer("INSERT INTO save_file_path(save_file_path_no, item_no, file_path) VALUES(save_file_path_seq.nextval,?,?)");
	@Override
	public void setItemImageFilePath(int newItemNo, String forDb) {
		// TODO Auto-generated method stub
		this.template.update(SFPFN.toString(),newItemNo,forDb);

	}
	
	// 상품 내용 수정
	private static final StringBuffer UPDATE_THE_ITEM = new StringBuffer("UPDATE item set item_name=?, origin=?, grade=?, processing=?, roasting_level=?, item_info=?, photo=?, price=?, total_product=? WHERE item_no = ?");
	@Override
	public void itemUpdate(ItemVo itemVo, Integer itemNo, String forDb) {
		// TODO Auto-generated method stub
		this.template.update(ItemDaoImpl.UPDATE_THE_ITEM.toString(), itemVo.getItemName(), itemVo.getOrigin(), itemVo.getGrade(), itemVo.getProcessing(), itemVo.getRoastingLevel(), itemVo.getItemInfo(), forDb, itemVo.getPrice(), itemVo.getTotalProduct(), itemNo);
	}
	
	// 수정된 상품 이미지 파일 경로 갱신
	private static final StringBuffer UPDATE_THE_FILE_PATH = new StringBuffer("UPDATE save_file_path set file_path=? WHERE item_no = ?");
	@Override
	public void updateFilePath(Integer itemNo, String forDb) {
		// TODO Auto-generated method stub
		this.template.update(ItemDaoImpl.UPDATE_THE_FILE_PATH.toString(), forDb, itemNo);
	}
	
	// 파일경로 얻기
	private static final StringBuffer LOOK_FOR_THE_FILE_PATH = new StringBuffer("SELECT file_path from save_file_path WHERE item_no = ?");
	@Override
	public String getFilePathTo(Integer itemNo) {
		// TODO Auto-generated method stub
		RowMapper<SaveFilePathTo> mapper = new BeanPropertyRowMapper<SaveFilePathTo>(
				SaveFilePathTo.class);
		SaveFilePathTo find = this.template.queryForObject(ItemDaoImpl.LOOK_FOR_THE_FILE_PATH.toString(), mapper ,itemNo);
		return find.getFilePath();
	}
	
	// 상품삭제
	private static final StringBuffer DELETE_THE_ITEM = new StringBuffer("DELETE item WHERE item_no = ?");
	private static final StringBuffer DELETE_THE_FILE_PATH = new StringBuffer("DELETE save_file_path WHERE item_no = ?");
	@Override
	public void deleteItem(Integer itemNo) {
		// TODO Auto-generated method stub
		this.template.update(ItemDaoImpl.DELETE_THE_ITEM.toString(), itemNo);
		this.template.update(ItemDaoImpl.DELETE_THE_FILE_PATH.toString(), itemNo);
	}
	
	private static final StringBuffer LOOK_FOR_THE_FILE_PATH_ALL = new StringBuffer("SELECT file_path from save_file_path");
	public List<SaveFilePathTo> findAll_photo() {
		// TODO Auto-generated method stub
		RowMapper<SaveFilePathTo> mapper = new BeanPropertyRowMapper<SaveFilePathTo>(
				SaveFilePathTo.class);

		return this.template.query(ItemDaoImpl.LOOK_FOR_THE_FILE_PATH_ALL.toString(), mapper);
	}
	
}
