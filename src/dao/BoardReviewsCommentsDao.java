package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.BoardReviewsComments;
import logic.MemberVo;

public interface BoardReviewsCommentsDao {

	List<BoardReviewsComments> findAll(HttpServletRequest request,
			Integer pageNo, Integer bdNoRev) throws Throwable;	// 댓글 다 불러오기
	int getTotalCommentsCount(Integer bdNoRev);	// 댓글의 총 갯수
	void writeCommentsAtBdNoRev(BoardReviewsComments bdRevCom,
			MemberVo userKey, Integer bdNoRev, String userIp);	// 댓글 쓰기
	void deleteCommentsByBdNoQaComments(Integer bdNoRevComments);	// 댓글 지우기

}
