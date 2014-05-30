package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.BoardQa;
import logic.MemberVo;


public interface BoardQaDao {
	List<BoardQa> findAll(HttpServletRequest request, Integer pageNo) throws Throwable;	// 해당 페이지 게시물 리스트 불러오기
	BoardQa findByPrimaryKey(Integer bdNoQa);	// 선택된 게시물을 인덱스 넘버로 읽기
	void countUp(Integer bdNoQa);	//조회수 올리기
	void write(BoardQa boardQa, MemberVo userKey, String userIp);	// 게시물 쓰기
	void update(BoardQa boardQa, String userIp);	// 게시물 수정
	void reply(BoardQa boardQa, Integer bdNoQa, MemberVo userKey, String userIp);	// 해당 게시물 답글
	void delete(Integer bdNoQa);	// 게시물 삭제
}
