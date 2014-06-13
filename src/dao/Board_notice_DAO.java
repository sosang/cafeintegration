package dao;

import java.util.List;




import javax.servlet.http.HttpServletRequest;

import logic.BoardNotice;

public interface Board_notice_DAO {

	Integer getTotalCount();
	List<BoardNotice> findAll(HttpServletRequest request, Integer pageNo) throws Throwable;	// 해당 페이지 게시물 리스트 불러오기
	BoardNotice findByPrimaryKey(Integer bdNoNtc);
	void countUp(Integer bdNoNtc);
	Integer getLastBdNoNtc();
	void Write(BoardNotice boardNotice);	// 공지사항 쓰기
	void Delete(Integer bdNoNtc);	// 공지 삭제
	
	

}
