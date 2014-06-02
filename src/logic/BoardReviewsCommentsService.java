package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardReviewsCommentsService {

	List<BoardReviewsComments> getBoardQaCommentsList(
			HttpServletRequest request, Integer pageNo, Integer bdNoRev) throws Throwable;	// 모든 댓글 불러오기
	void setCommentsAtBdNoRev(BoardReviewsComments bdRevCom, MemberVo userKey,
			Integer bdNoRev, String userIp);	// 댓글 쓰기
	void deleteBoardQaComment(Integer bdNoRevComments);	// 댓글 지우기
	List<BoardReviewsComments> getBoardRevCommentsList(
			HttpServletRequest request, Integer pageNo, Integer bdNoRev) throws Throwable;

}
