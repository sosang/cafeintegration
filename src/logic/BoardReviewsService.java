package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BoardReviewsService {

	List<BoardReviews> getBoardReviewsList(HttpServletRequest request,
			Integer pageNo) throws Throwable;	// 게시물 불러오기 20개씩
	BoardReviews getBoardReviewsByBdNoRev(Integer bdNoRev);	// 해당 게시물 읽기
	void countUpForBdNoRev(Integer bdNoRev);	// 조회수 올리기
	void boardRevWrite(BoardReviews boardRev, MemberVo userKey, String userIp);	// 후기쓰기
	void boardReviewsWriteUpdate(BoardReviews boardRev, String userIp);	// 게시물 수정
	void setBoardReviewsReplyByBdNoRev(BoardReviews boardRev, Integer bdNoRev,
			MemberVo userKey, String userIp);	// 후기 답글달기
	void deleteBoardReviewsArticle(Integer bdNoRev);	// 후기 삭제
	void RecommendUpByBdNoRev(Integer bdNoRev, MemberVo userKey, String userIp);	// 추천은 하는데 같은사람은 추천 못해
	int getRecentNo();	// 최근 게시물 번호
	SaveFilePathTo getFilePath(int bdNoRev);	// 파일경로 찾기
	void setFilePath(int newBdNoRev, String forDb);	// 경로쓰기

}
