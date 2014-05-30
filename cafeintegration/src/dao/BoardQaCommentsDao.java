package dao;

import java.util.List;







import javax.servlet.http.HttpServletRequest;

import logic.BoardQaComments;
import logic.MemberVo;

public interface BoardQaCommentsDao {

	List<BoardQaComments> findAll(HttpServletRequest request, Integer pageNo, Integer bdNoQa) throws Throwable;	// 해당 페이지 댓글 리스트 불러오기
	int getTotalCommentsCount(Integer bdNoQa);	// 총 댓글 수 불러오기
	void writeCommentsAtBdNoQa(BoardQaComments bdQaCom, MemberVo userKey, Integer bdNoQa, String userIp);	// 댓글 쓰기
	void deleteCommentsByBdNoQaComments(Integer bdNoQaComments);	// 댓글 삭제
}
