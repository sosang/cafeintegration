package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.BoardFaq;

public interface BoardFaqDao {
	List<BoardFaq> findAll(HttpServletRequest request, Integer pageNo) throws Throwable;	
}
