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

import logic.BoardReviewsComments;
import logic.MemberVo;

@Repository
public class BoardReviewsCommentsDaoImpl implements BoardReviewsCommentsDao {

	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// 댓글의 총 갯수
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer("SELECT count(*) FROM board_reviews_comments where bd_no_rev = ?");
	@Override
	public int getTotalCommentsCount(Integer bdNoRev) {
		// TODO Auto-generated method stub
		// return this.template.queryForInt(GET_TOTAL_COUNT.toString());
		return this.template.queryForInt(GET_TOTAL_COUNT.toString(), bdNoRev);
	}
	
	// 댓글 불러오기
	private static final StringBuffer SELECT_ALL = new StringBuffer("SELECT * FROM board_reviews_comments WHERE bd_no_rev = ? ORDER BY bd_no_rev_comments");
	@Override
	public List<BoardReviewsComments> findAll(HttpServletRequest request,
			Integer pageNo, Integer bdNoRev) throws Throwable {
		// TODO Auto-generated method stub
		RowMapper<BoardReviewsComments> mapper = new BeanPropertyRowMapper<BoardReviewsComments>(BoardReviewsComments.class);
		List<BoardReviewsComments> commentsListRev = null;
		int listCount = 0;
		listCount = this.getTotalCommentsCount(bdNoRev);	// 해당 게시물의 총 댓글 수를 불러왕
		if(listCount > 0){
			commentsListRev = this.jdbcTemplate.query(BoardReviewsCommentsDaoImpl.SELECT_ALL.toString(), mapper, bdNoRev);	// 현재 페이지에 해당하는 글 목록
		}else{
			commentsListRev = Collections.emptyList();
		}
		request.setAttribute("commentsListRev", commentsListRev);
		return commentsListRev;
	}
	
	// 댓글 쓰기
	private static final StringBuffer WRITE_COMMENTS = new StringBuffer("INSERT INTO board_reviews_comments"
			+ "(bd_no_rev_comments, bd_no_rev, user_email, user_alias, bd_rev_comments_content, bd_rev_comments_date, bd_rev_comments_ip) "
			+ "VALUES(board_reviews_comments_seq.nextval,?,?,?,?,sysdate,?)");
	@Override
	public void writeCommentsAtBdNoRev(BoardReviewsComments bdRevCom,
			MemberVo userKey, Integer bdNoRev, String userIp) {
		// TODO Auto-generated method stub
		this.template.update(WRITE_COMMENTS.toString(), bdNoRev, userKey.getUserEmail(), userKey.getUserAlias(), bdRevCom.getBdRevCommentsContent(), userIp);
		commentsPointUp(userKey.getUserEmail());
	}
	
	// 댓글 삭제
	private static final StringBuffer DELETE_THE_COMMENTS = new StringBuffer("DELETE board_reviews_comments WHERE bd_no_rev_comments = ?");
	@Override
	public void deleteCommentsByBdNoQaComments(Integer bdNoRevComments) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(DELETE_THE_COMMENTS.toString(), bdNoRevComments);
		
	}
	
	// 글쓴이에게 포인트를!
	private static final StringBuffer GIVE_COMMENTS_POINT_TO_WRITER = new StringBuffer("UPDATE member SET user_num_of_comments = user_num_of_comments+1 WHERE user_email = ?");
	private void commentsPointUp(String userEmail) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(GIVE_COMMENTS_POINT_TO_WRITER.toString(), userEmail);
	}
	
	
}