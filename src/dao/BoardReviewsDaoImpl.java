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

import logic.BoardReviews;
import logic.MemberVo;
import logic.SaveFilePathTo;

@Repository
public class BoardReviewsDaoImpl implements BoardReviewsDao {
	
	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	// 후기게시판의 최근 게시물 번호
	private static final StringBuffer GET_RECENT_NO = new StringBuffer("SELECT max(bd_no_rev) FROM board_reviews");
	@Override
	public Integer getRecentNo() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_RECENT_NO.toString());
	}

	// 후기게시판의 총 게시물 수
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer("SELECT count(*) FROM board_reviews");
	@Override
	public Integer getTotalCount() {
		// TODO Auto-generated method stub
		return this.template.queryForInt(GET_TOTAL_COUNT.toString());
	}
	
	// 게시물 20개씩 불러오기
	private static final StringBuffer SELECT_ALL = new StringBuffer("SELECT * FROM (SELECT rownum rnum, bd_no_rev, user_email, user_alias, title_rev, content_rev, count_rev, recommend_rev, ref_rev, re_step, re_level, date_rev, user_ip from (SELECT * FROM board_reviews ORDER BY ref_rev desc, re_step asc)) where rnum>=? and rnum<=?");
	@Override
	public List<BoardReviews> findAll(HttpServletRequest request, Integer pageNo) throws Throwable {
		// TODO Auto-generated method stub
		RowMapper<BoardReviews> mapper = new BeanPropertyRowMapper<BoardReviews>(BoardReviews.class);

		int limit = 20;
		Integer startrow = (pageNo-1)*limit+1;
		Integer endrow = startrow+limit-1;
		int listCount = 0;
		if(request.getParameter("pageNo")!=null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		List<BoardReviews> articleListRev = null;
		listCount = this.getTotalCount();
		if(listCount > 0){
			articleListRev = this.jdbcTemplate.query(BoardReviewsDaoImpl.SELECT_ALL.toString(), mapper, startrow, endrow);	// 현재 페이지에 해당하는 글 목록
		}else{
			articleListRev = Collections.emptyList();
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
		request.setAttribute("maxPageRev", new Integer(maxPage));
		request.setAttribute("startPageRev", new Integer(startPage));
		request.setAttribute("endPageRev", new Integer(endPage));
		request.setAttribute("listCountRev", new Integer(listCount));
		request.setAttribute("articleListRev", articleListRev);

		return articleListRev;
	}
	
	// 해당 게시물 읽기
	private static final StringBuffer SELECT_BY_PRIMARY_KEY = new StringBuffer("SELECT * FROM board_reviews WHERE bd_no_rev = ?");
	@Override
	public BoardReviews findByPrimaryKey(Integer bdNoRev) {
		// TODO Auto-generated method stub
		RowMapper<BoardReviews> mapper = new BeanPropertyRowMapper<BoardReviews>(BoardReviews.class);
		BoardReviews boardRev = this.template.queryForObject(SELECT_BY_PRIMARY_KEY.toString(), mapper, bdNoRev);
		return boardRev;
	}
	
	
	// 조회시 조회수 증가
	private static final StringBuffer COUNT_NOTICE_UP = new StringBuffer("UPDATE board_reviews SET count_rev=count_rev+1 WHERE bd_no_rev=?");
	@Override
	public void countUp(Integer bdNoRev) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(COUNT_NOTICE_UP.toString(), bdNoRev);
	}
	
	
	// 후기 쓰기
	private static final StringBuffer BOARD_REV_WRITE = new StringBuffer("INSERT INTO board_reviews(bd_no_rev, user_email, user_alias, title_rev, content_rev, ref_rev, user_ip) values(board_reviews_seq.nextval,?,?,?,?,board_reviews_seq.currval,?)");
	@Override
	public void write(BoardReviews boardRev, MemberVo userKey, String userIp, String forDb) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(BoardReviewsDaoImpl.BOARD_REV_WRITE.toString(), userKey.getUserEmail(), userKey.getUserAlias(), boardRev.getTitleRev(), boardRev.getContentRev(), userIp);
		articlePointUp(userKey.getUserEmail());
	}

	// 글쓴이에게 포인트를!
	private static final StringBuffer GIVE_ARTICLE_POINT_TO_WRITER = new StringBuffer("UPDATE member SET user_num_of_article = user_num_of_article+1 WHERE user_email = ?");
	private void articlePointUp(String userEmail) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(GIVE_ARTICLE_POINT_TO_WRITER.toString(), userEmail);
	}
	
	// 후기 수정
	private static final StringBuffer UPDATE_TO_THE_ARTICLE = new StringBuffer("UPDATE board_reviews set title_rev=?, content_rev=?, user_ip=? WHERE bd_no_rev = ?");
	@Override
	public void update(BoardReviews boardRev, String userIp) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(BoardReviewsDaoImpl.UPDATE_TO_THE_ARTICLE.toString(), boardRev.getTitleRev(), boardRev.getContentRev(), userIp, boardRev.getBdNoRev());
	}
	
	// 해당 게시물의 re_step 이 0일때는 무조건 1로
	// 해당 게시물의 re_step 이 1이상일 때는 무조건 +1해줌
	// 답글로 들어오면 level은 무조건 +1해줌
	// 해당 게시물의 답글달기
	private static final StringBuffer REPLY_FOR_THE_ARTICLE = new StringBuffer("INSERT INTO board_reviews(bd_no_rev, user_email, user_alias, title_rev, content_rev, ref_rev, re_step, re_level, user_ip) values(board_reviews_seq.nextval,?,?,?,?,?,?,?,?)");
	@Override
	public void reply(BoardReviews boardRev, Integer bdNoRev, MemberVo userKey, String userIp) {
		// TODO Auto-generated method stub
		int newRefQa = 0;
		int newReStepQa = 0;
		int newReLevelQa = 0;
		
		BoardReviews boardRevBefore = findByPrimaryKey(bdNoRev);
		if(boardRevBefore.getReStep()>0){
			newRefQa = (int)boardRevBefore.getRefRev();
			newReStepQa = (int)boardRevBefore.getReStep()+1;
			newReLevelQa = (int)boardRevBefore.getReLevel()+1;
		}else{
			newRefQa = bdNoRev;
			newReStepQa = 1;
			newReLevelQa = (int)boardRevBefore.getReLevel()+1; 
		}
		this.jdbcTemplate.update(BoardReviewsDaoImpl.REPLY_FOR_THE_ARTICLE.toString(), userKey.getUserEmail(), userKey.getUserAlias(), boardRev.getTitleRev(), boardRev.getContentRev(), newRefQa, newReStepQa, newReLevelQa, userIp);
	}
	
	// 해당 게시물 삭제
	private static final StringBuffer DELETE_THE_ARTICLE = new StringBuffer("DELETE board_reviews WHERE bd_no_rev = ?");
	@Override
	public void delete(Integer bdNoRev) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(BoardReviewsDaoImpl.DELETE_THE_ARTICLE.toString(), bdNoRev);
	}
	
	// 추천은 하는데 같은사람은 추천 못해
	private static final StringBuffer RECOMMEND_UP = new StringBuffer("INSERT INTO recommend_recorder(rec_rec_no, bd_no_rev, user_email, user_alias, user_ip) values(recommend_recorder_seq.nextval, ?, ?, ?, ?)");
//	@Override
	public void recommendUp(Integer bdNoRev, MemberVo userKey, String userIp) {
		// TODO Auto-generated method stub
		if(recommendedCheck(bdNoRev, userKey.getUserEmail())==0){
			// 추천수 올림
			this.recommendUpByBdNoRev(bdNoRev);
			this.jdbcTemplate.update(BoardReviewsDaoImpl.RECOMMEND_UP.toString(), bdNoRev, userKey.getUserEmail(), userKey.getUserAlias(), userIp);
		}else{
		}
	}
	
	// 추천한 적이 있는지 검사
	// 추천한적 없으면 0 있으면 1 나옴
	private static final StringBuffer RECOMMENDED_CHECK = new StringBuffer("SELECT * FROM recommend_recorder WHERE bd_no_rev = ? AND user_email = ?");
	private int recommendedCheck(Integer bdNoRev, String userEmail) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update(BoardReviewsDaoImpl.RECOMMENDED_CHECK.toString(), bdNoRev, userEmail);
	}
	
	// 추천을 올려
	private static final StringBuffer RECOMMENDED_UP_BY_BDNOREV = new StringBuffer("UPDATE board_reviews set recommend_rev=recommend_rev+1 WHERE bd_no_rev = ?");
	private void recommendUpByBdNoRev(int bdNoRev){
		this.jdbcTemplate.update(BoardReviewsDaoImpl.RECOMMENDED_UP_BY_BDNOREV.toString(),bdNoRev); 
	}
	
	// 파일경로 찾기
	private static final StringBuffer GAFPBNBNR = new StringBuffer("SELECT * FROM save_file_path WHERE bd_no_rev = ?");
	@Override
	public SaveFilePathTo getAllFilePathByNewBdNoRev(int bdNoRev) {
		// TODO Auto-generated method stub
		RowMapper<SaveFilePathTo> mapper = new BeanPropertyRowMapper<SaveFilePathTo>(SaveFilePathTo.class);
		SaveFilePathTo sfpt = this.template.queryForObject(GAFPBNBNR.toString(), mapper, bdNoRev);
		return sfpt;
	}
	
	// 파일경로 쓰기
	private static final StringBuffer SFPFN = new StringBuffer("INSERT INTO save_file_path(save_file_path_no, bd_no_rev, file_path) VALUES(save_file_path_seq.nextval,?,?)");
	@Override
	public void setFilePathForNew(int newBdNoRev, String forDb) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(SFPFN.toString(),newBdNoRev,forDb);
	}
	
	
	
}
