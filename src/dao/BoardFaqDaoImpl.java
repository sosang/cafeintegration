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

import logic.BoardFaq;

@Repository
public class BoardFaqDaoImpl implements BoardFaqDao {
	
	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	// 총 게시물 수 
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer("SELECT count(*) FROM board_faq");
	private Integer getTotalCount() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_TOTAL_COUNT.toString());
	}
	
	// 게시물 불러오기 해당페이지
	private static final StringBuffer SELECT_ALL = new StringBuffer("SELECT * FROM (SELECT rownum rnum, bd_no_faq, title_faq, content_faq from (select * from board_faq order by bd_no_faq desc)) where rnum>=? and rnum<=?");

	@Override
	public List<BoardFaq> findAll(HttpServletRequest request, Integer pageNo) throws Throwable{
		// TODO Auto-generated method stub
		RowMapper<BoardFaq> mapper = new BeanPropertyRowMapper<BoardFaq>(BoardFaq.class);

		int limit = 10;
		Integer startrow = (pageNo-1)*limit+1;
		Integer endrow = startrow+limit-1;
		int listCount = 0;
		if(request.getParameter("pageNo")!=null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		List<BoardFaq> faqList = null;
		listCount = this.getTotalCount();
		if(listCount > 0){
			faqList = this.jdbcTemplate.query(BoardFaqDaoImpl.SELECT_ALL.toString(), mapper, startrow, endrow);	// 현재 페이지에 해당하는 글 목록
		}else{
			faqList = Collections.emptyList();
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
		request.setAttribute("faqList", faqList);

		return faqList;
	}

}
