package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardQaService {
	List<BoardQa> getBoardQaList(HttpServletRequest request, Integer pageNo) throws Throwable;	// 해당페이지 게시물 리스트 받기
	BoardQa getBoardQaByBdNoQa(Integer bdNoQa);		// 선택 게시물 불러오기
	void countUpForBdNoQa(Integer bdNoQa);	// 조회수 올리기
	void boardQaWrite(BoardQa boardQa, MemberVo userKey, String userIp);	// 게시물 쓰기
	void boardQaWriteUpdate(BoardQa boardQa, String userIp);	// 게시물 수정
	void setBoardQaReplyByBdNoQa(BoardQa boardQa, Integer bdNoQa, MemberVo userKey, String userIp);	// 해당 게시물에 리플달긔
	void deleteBoardQaArticle(Integer bdNoQa);	// 게시물 삭제
	
}
