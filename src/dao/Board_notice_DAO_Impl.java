package dao;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import logic.BoardNotice;

@Repository
public class Board_notice_DAO_Impl implements Board_notice_DAO {

	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer("select count(*) from BOARD_NOTICE");
	
	@Override
	public Integer getTotalCount() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_TOTAL_COUNT.toString());
	}
	
//	private static final StringBuffer SELECT_ALL1 = new StringBuffer("SELECT * FROM BOARD_NOTICE ORDER BY bd_no_ntc DESC"); 
	private static final StringBuffer SELECT_ALL = new StringBuffer("select * from (select rownum rnum, bd_no_ntc, title_ntc, content_ntc, date_ntc, count_ntc from (select * from BOARD_NOTICE order by bd_no_ntc desc)) where rnum>=? and rnum<=?");

	@Override
	public List<BoardNotice> findAll(HttpServletRequest request, Integer pageNo) throws Throwable{
		// TODO Auto-generated method stub
		RowMapper<BoardNotice> mapper = new BeanPropertyRowMapper<BoardNotice>(BoardNotice.class);

		int limit = 10;
		Integer startrow = (pageNo-1)*limit+1;
		Integer endrow = startrow+limit-1;
		int listCount = 0;
		if(request.getParameter("pageNo")!=null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		List<BoardNotice> articleList = null;
		listCount = this.getTotalCount();
		if(listCount > 0){
			articleList = this.jdbcTemplate.query(Board_notice_DAO_Impl.SELECT_ALL.toString(), mapper, startrow, endrow);	// 현재 페이지에 해당하는 글 목록
		}else{
			articleList = Collections.emptyList();
		}
		int maxPage = (int)((double)listCount/limit+0.95);	// 올림처리

		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등)
		int startPage = (((int)((double)pageNo/10+0.9))-1)*10+1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10,20,30 등)
		int endPage = maxPage;
		if(endPage>startPage+10-1){
			endPage = startPage+10-1;
		}

		// 공지사항 패이지에 보여줄 속성들 셋팅
		request.setAttribute("pageNo", new Integer(pageNo));
		request.setAttribute("maxPage", new Integer(maxPage));
		request.setAttribute("startPage", new Integer(startPage));
		request.setAttribute("endPage", new Integer(endPage));
		request.setAttribute("listCount", new Integer(listCount));
		request.setAttribute("articleList", articleList);

		return articleList;
	}


	// pk로 조회
	private static final StringBuffer SELECT_BY_PRIMARY_KEY = new StringBuffer("select * from board_notice where bd_no_ntc = ?");
	
	@Override
	public BoardNotice findByPrimaryKey(Integer bdNoNtc) {
		// TODO Auto-generated method stub
		countUp(bdNoNtc);
		RowMapper<BoardNotice> mapper = new BeanPropertyRowMapper<BoardNotice>(BoardNotice.class);
		return this.template.queryForObject(SELECT_BY_PRIMARY_KEY.toString(), mapper, bdNoNtc);
	}
	
	// 조회시 조회수 증가
	private static final StringBuffer COUNT_NOTICE_UP = new StringBuffer("update board_notice set count_ntc=count_ntc+1 where bd_no_ntc=?");
	@Override
	public void countUp(Integer bdNoNtc) {
		// TODO Auto-generated method stub
		Object[] obj = { bdNoNtc };
		this.jdbcTemplate.update(COUNT_NOTICE_UP.toString(), obj);
	}
	
	// 최신 게시물 인덱스 번호 받기
	private static final StringBuffer SELECT_LAST_IDX_NO = new StringBuffer("select max(bd_no_ntc) from board_notice");
	@Override
	public Integer getLastBdNoNtc() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(SELECT_LAST_IDX_NO.toString());
	}
	
	// 공지사항 쓰기
	private static final StringBuffer WRITE_NOTICE = new StringBuffer("INSERT INTO board_notice values(board_notice_seq.nextval, ?,?,sysdate,0)");
	
	@Override
	public void Write(BoardNotice boardNotice) {
		// TODO Auto-generated method stub
		this.template.update(Board_notice_DAO_Impl.WRITE_NOTICE.toString(), boardNotice.getTitleNtc(), boardNotice.getContentNtc());
	}
	
	//공지사항 삭제
	private static final StringBuffer DELETE_NOTICE = new StringBuffer("DELETE board_notice where bd_no_ntc = ?");
	
	@Override
	public void Delete(Integer bdNoNtc) {
		// TODO Auto-generated method stub
		this.template.update(Board_notice_DAO_Impl.DELETE_NOTICE.toString(), bdNoNtc);
	}
	

}
