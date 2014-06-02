package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardFaqService {

	List<BoardFaq> getBoardFaqList(HttpServletRequest request, Integer pageNo) throws Throwable;	// FAQ불러오기

}
