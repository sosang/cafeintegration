package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardFaqService {

	List<BoardFaq> getBoardFaqList(HttpServletRequest request, Integer pageNo) throws Throwable;	// FAQ불러오기

	void boardFaqWrite(BoardFaq boardFaq);	// FAQ 쓰기

	void deleteBoardFaqArticle(Integer bdNoFaq);	// FAQ 삭제

	BoardFaq getBoardFaqByBdNoFaq(Integer bdNoFaq);	// 해당 FAQ 읽어오기

	void boardFaqUpdate(BoardFaq boardFaq);	// FAQ 수정

}
