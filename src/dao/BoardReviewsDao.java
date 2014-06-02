package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logic.BoardReviews;
import logic.MemberVo;
import logic.SaveFilePathTo;

public interface BoardReviewsDao {

	List<BoardReviews> findAll(HttpServletRequest request, Integer pageNo) throws Throwable;	// 게시물 20개씩 불러오기
	Integer getTotalCount();	// 자유게시판의 총 게시물 수
	BoardReviews findByPrimaryKey(Integer bdNoRev); // 해당 게시물 읽기
	void countUp(Integer bdNoRev);	// 조회수 올리기
	void write(BoardReviews boardRev, MemberVo userKey, String userIp, String forDb);	// 후기 쓰기
	void update(BoardReviews boardRev, String userIp);	// 후기 수정
	void reply(BoardReviews boardRev, Integer bdNoRev, MemberVo userKey,
			String userIp);	// 후기 답글
	void delete(Integer bdNoRev);	// 후기 삭제
	void recommendUp(Integer bdNoRev, MemberVo userKey, String userIp); // 추천은 하는데 같은사람은 추천 못해
	Integer getRecentNo();	// 최근 글 쓴 번호
	SaveFilePathTo getAllFilePathByNewBdNoRev(int bdNoRev);	// 파일경로 찾기
	void setFilePathForNew(int newBdNoRev, String forDb);	// 파일경로 쓰기

}
