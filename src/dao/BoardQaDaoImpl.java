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
import org.springframework.transaction.annotation.Transactional;

import logic.BoardQa;
import logic.MemberVo;

@Repository
@Transactional
public class BoardQaDaoImpl implements BoardQaDao {
	
	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
	}

	
	// 페이지 번호에 따른 게시물 불러오기
	private static final StringBuffer SELECT_ALL = new StringBuffer("SELECT * FROM (SELECT rownum rnum, bd_no_qa, user_email, user_alias, title_qa, content_qa, count_qa, ref_qa, re_step, re_level, date_qa, user_ip from (SELECT * FROM BOARD_QA ORDER BY ref_qa desc, re_step asc)) where rnum>=? and rnum<=?");
//																																																							select * from board_qa order by ref_qa desc, re_level asc
	@Override
	public List<BoardQa> findAll(HttpServletRequest request, Integer pageNo)
			throws Throwable {
		// TODO Auto-generated method stub
		RowMapper<BoardQa> mapper = new BeanPropertyRowMapper<BoardQa>(BoardQa.class);

		int limit = 20;	// 페이지당 게시물 수
		Integer startrow = (pageNo-1)*limit+1;
		Integer endrow = startrow+limit-1;
		int listCount = 0;
		if(request.getParameter("pageNo")!=null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		List<BoardQa> articleListQa = null;
		listCount = this.getTotalCount();
		if(listCount > 0){
			articleListQa = this.jdbcTemplate.query(BoardQaDaoImpl.SELECT_ALL.toString(), mapper, startrow, endrow);	// 현재 페이지에 해당하는 글 목록
		}else{
			articleListQa = Collections.emptyList();
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
		request.setAttribute("maxPageQa", new Integer(maxPage));
		request.setAttribute("startPageQa", new Integer(startPage));
		request.setAttribute("endPageQa", new Integer(endPage));
		request.setAttribute("listCountQa", new Integer(listCount));
		request.setAttribute("articleListQa", articleListQa);

		return articleListQa;
	}
	
	// 질답게시판의 게시물 수 불러오기
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer("SELECT count(*) FROM board_qa");
	
	private Integer getTotalCount() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_TOTAL_COUNT.toString());
	}

	// pk로 조회
	private static final StringBuffer SELECT_BY_PRIMARY_KEY = new StringBuffer("SELECT * FROM BOARD_QA WHERE bd_no_qa = ?");
	
	@Override
	public BoardQa findByPrimaryKey(Integer bdNoQa) {
		// TODO Auto-generated method stub
//		countUp(bdNoQa); // 조회수 증가
		RowMapper<BoardQa> mapper = new BeanPropertyRowMapper<BoardQa>(BoardQa.class);
		return this.template.queryForObject(SELECT_BY_PRIMARY_KEY.toString(), mapper, bdNoQa);
	}

	// 조회시 조회수 증가
	private static final StringBuffer COUNT_NOTICE_UP = new StringBuffer("UPDATE BOARD_QA SET count_qa=count_qa+1 WHERE bd_no_qa=?");
	@Override
	public void countUp(Integer bdNoQa) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(COUNT_NOTICE_UP.toString(), bdNoQa);
	}


	// 글쓰기!
	private static final StringBuffer BOARD_QA_WRITE = new StringBuffer("INSERT INTO BOARD_QA(bd_no_qa, user_email, user_alias, title_qa, content_qa, ref_qa, user_ip) values(board_qa_seq.nextval,?,?,?,?,board_qa_seq.currval,?)");
	@Override
	public void write(BoardQa boardQa, MemberVo userKey, String userIp) {
		// TODO Auto-generated method stub
		this.template.update(BoardQaDaoImpl.BOARD_QA_WRITE.toString(), userKey.getUserEmail(), userKey.getUserAlias(), boardQa.getTitleQa(), boardQa.getContentQa(), userIp);
		articlePointUp(userKey.getUserEmail());
	}
	
	// 글쓴이에게 포인트를!
	private static final StringBuffer GIVE_ARTICLE_POINT_TO_WRITER = new StringBuffer("UPDATE MEMBER SET user_num_of_article = user_num_of_article+1 WHERE user_email = ?");
	private void articlePointUp(String userEmail) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(GIVE_ARTICLE_POINT_TO_WRITER.toString(), userEmail);
	}
	
	// 게시물 수정
//	private static final StringBuffer UPDATE_THE_ARTICLE = new StringBuffer("UPDATE board_qa set title_qa=:titleQa, content_qa=:contentQa, user_ip=:userIp WHERE bd_no_qa = :bdNoQa");
//	@Override
//	public void update(BoardQa boardQa, String userIp) {
//		// TODO Auto-generated method stub
//		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(boardQa);
//		this.namedParameterJdbcTemplate.(BoardQaDaoImpl.UPDATE_THE_ARTICLE.toString(), parameterSource);
//		
//	}
	// 게시물 수정
	private static final StringBuffer UPDATE_TO_THE_ARTICLE = new StringBuffer("UPDATE board_qa set title_qa=?, content_qa=?, user_ip=? WHERE bd_no_qa = ?");
	@Override
	public void update(BoardQa boardQa, String userIp) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(BoardQaDaoImpl.UPDATE_TO_THE_ARTICLE.toString(), boardQa.getTitleQa(), boardQa.getContentQa(), userIp, boardQa.getBdNoQa());
		
	}
	
	// 해당 게시물의 re_step 이 0일때는 무조건 1로
	// 해당 게시물의 re_step 이 1이상일 때는 무조건 +1해줌
	// 답글로 들어오면 level은 무조건 +1해줌
//	private static final StringBuffer BEFORE_TO_REPLY = new StringBuffer("SELECT * FROM board_qa WHERE bd_no_qa = ?");
	// 해당 게시물의 답글달기
	private static final StringBuffer REPLY_FOR_THE_ARTICLE = new StringBuffer("INSERT INTO board_qa(bd_no_qa, user_email, user_alias, title_qa, content_qa, ref_qa, re_step, re_level, user_ip) values(board_qa_seq.nextval,?,?,?,?,?,?,?,?)");
	@Override
	public void reply(BoardQa boardQa, Integer bdNoQa, MemberVo userKey, String userIp) {
		// TODO Auto-generated method stub
//		System.out.println(boardQa.getUserAlias()+"/"+bdNoQa+"/"+userKey.getUserEmail()+"/"+userIp);
//		RowMapper<BoardQa> mapper = new BeanPropertyRowMapper<BoardQa>(BoardQa.class);
//		try{
//			RowMapper<BoardQa> mapper = new BeanPropertyRowMapper<BoardQa>(BoardQa.class);
//			boardQa = this.template.queryForObject(SELECT_BY_PRIMARY_KEY.toString(), mapper, bdNoQa);
//			System.out.println("after : "+boardQa.getTitleQa());
//		}catch (EmptyResultDataAccessException e){
//			e.printStackTrace();
//		}
		int newRefQa = 0;
		int newReStepQa = 0;
		int newReLevelQa = 0;
		
		BoardQa boardQaBefore = findByPrimaryKey(bdNoQa);
//		System.out.println(boardQaBefore.getTitleQa());
//		System.out.println(boardQaBefore.getReLevel());
		if(boardQaBefore.getReStep()>0){
			newRefQa = (int)boardQaBefore.getRefQa();
			newReStepQa = (int)boardQaBefore.getReStep()+1;
			newReLevelQa = (int)boardQaBefore.getReLevel()+1;
		}else{
			newRefQa = bdNoQa;
			newReStepQa = 1;
			newReLevelQa = (int)boardQaBefore.getReLevel()+1; 
		}
//		System.out.println("finish : "+boardQa.getTitleQa()+"/ end : "+newRefQa);
		this.jdbcTemplate.update(BoardQaDaoImpl.REPLY_FOR_THE_ARTICLE.toString(), userKey.getUserEmail(), userKey.getUserAlias(), boardQa.getTitleQa(), boardQa.getContentQa(), newRefQa, newReStepQa, newReLevelQa, userIp);
	}
	
	// 해당 게시물 삭제
	private static final StringBuffer DELETE_THE_ARTICLE = new StringBuffer("DELETE board_qa WHERE bd_no_qa = ?");
	@Override
	public void delete(Integer bdNoQa) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(BoardQaDaoImpl.DELETE_THE_ARTICLE.toString(), bdNoQa);
		System.out.println("완료");
	}
	
	
	
}
