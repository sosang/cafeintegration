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

import logic.BoardQaComments;
import logic.MemberVo;

@Repository
public class BoardQaCommentsDaoImpl implements BoardQaCommentsDao {
	
	private SimpleJdbcTemplate template;
	private JdbcTemplate jdbcTemplate;
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.template = new SimpleJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
//		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	// 여기서만 쓰는거라 오버라이드 안함
	// 댓글의 총 갯수를 불러오는 맷소드 리스트의 예외처리를 위한것인듯? 잘몰라 ㅋ
	private static final StringBuffer GET_TOTAL_COUNT = new StringBuffer("SELECT count(*) FROM board_qa_comments where bd_no_qa = ?");
	
	@Override
	public int getTotalCommentsCount(Integer bdNoQa) {
		// TODO Auto-generated method stub
		// return this.template.queryForInt(GET_TOTAL_COUNT.toString());
		return this.template.queryForInt(GET_TOTAL_COUNT.toString(), bdNoQa);
	}
	
	// 해당 게시물의 댓글 전체를 불러온당~
	private static final StringBuffer SELECT_ALL = new StringBuffer("SELECT * FROM board_qa_comments WHERE bd_no_qa = ? ORDER BY bd_no_qa_comments");
	@Override
	public List<BoardQaComments> findAll(HttpServletRequest request, Integer pageNo, Integer bdNoQa)
			throws Throwable {
		// TODO Auto-generated method stub
		RowMapper<BoardQaComments> mapper = new BeanPropertyRowMapper<BoardQaComments>(BoardQaComments.class);
		List<BoardQaComments> commentsListQa = null;
		int listCount = 0;
		listCount = this.getTotalCommentsCount(bdNoQa);	// 해당 게시물의 총 댓글 수를 불러왕
		if(listCount > 0){
			commentsListQa = this.jdbcTemplate.query(BoardQaCommentsDaoImpl.SELECT_ALL.toString(), mapper, bdNoQa);	// 현재 페이지에 해당하는 글 목록
		}else{
			commentsListQa = Collections.emptyList();
		}
		request.setAttribute("commentsListQa", commentsListQa);
		return commentsListQa;
	}
	
	// 댓글 쓰기
	private static final StringBuffer WRITE_COMMENTS = new StringBuffer("INSERT INTO board_qa_comments"
			+ "(bd_no_qa_comments, bd_no_qa, user_email, user_alias, bd_qa_comments_content, bd_qa_comments_date, bd_qa_comments_ip) "
			+ "VALUES(board_qa_comments_seq.nextval,?,?,?,?,sysdate,?)");
	@Override
	public void writeCommentsAtBdNoQa(BoardQaComments bdQaCom, MemberVo userKey, Integer bdNoQa, String userIp) {
		// TODO Auto-generated method stub
//		System.out.println(bdNoQa+"/"+userKey.getUserEmail()+"/"+userKey.getUserAlias()+"/"+ bdQaCom.getBdQaCommentsContent()+"/"+ userIp);
		this.template.update(WRITE_COMMENTS.toString(), bdNoQa, userKey.getUserEmail(), userKey.getUserAlias(), bdQaCom.getBdQaCommentsContent(), userIp);
		commentsPointUp(userKey.getUserEmail());
	}
	
	
	// 댓글 삭제
	private static final StringBuffer DELETE_THE_COMMENTS = new StringBuffer("DELETE board_qa_comments WHERE bd_no_qa_comments = ?");
	@Override
	public void deleteCommentsByBdNoQaComments(Integer bdNoQaComments) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(DELETE_THE_COMMENTS.toString(), bdNoQaComments);
	}
	
	// 글쓴이에게 포인트를!
	private static final StringBuffer GIVE_COMMENTS_POINT_TO_WRITER = new StringBuffer("UPDATE member SET user_num_of_comments = user_num_of_comments+1 WHERE user_email = ?");
	private void commentsPointUp(String userEmail) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(GIVE_COMMENTS_POINT_TO_WRITER.toString(), userEmail);
	}
	
	
	

}
