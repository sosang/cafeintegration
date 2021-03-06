package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


public interface BoardNoticeService {

	Integer getTotalCount();
	List<BoardNotice> getBoardNoticeList(HttpServletRequest request, Integer pageNo) throws Throwable;	// 해당페이지 게시물 리스트 받기
	BoardNotice getBoardNoticeByBdNoNtc(Integer bdNoNtc);		// 선택된 게시물 가져오기
	void countUp(Integer bdNoNtc);								// 조회수 올리기
	Integer getLastBdNoNtc();
}
