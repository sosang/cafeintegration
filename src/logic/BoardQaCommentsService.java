package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardQaCommentsService {

	List<BoardQaComments> getBoardQaCommentsList(HttpServletRequest request,
			Integer pageNo, Integer bdNoQa) throws Throwable;	// 해당 게시물의 댓글 불러오기
	int getTotalCommentsCount(Integer bdNoQa);	// 해당 게시물의 총 댓글 수 불러오기
	void setCommentsAtBdNoQa(BoardQaComments bdQaCom, MemberVo userKey, Integer bdNoQa, String userIp);	// 댓글 쓰기
	void deleteBoardQaComment(Integer bdNoQaComments);	// 댓글 삭제

}
