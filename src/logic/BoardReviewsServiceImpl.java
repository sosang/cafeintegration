package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BoardReviewsDao;

@Service
@Transactional
public class BoardReviewsServiceImpl implements BoardReviewsService {

	@Autowired
	BoardReviewsDao boardReviewsDao;

	// 게시물 20개씩 불러오기
	@Override
	public List<BoardReviews> getBoardReviewsList(HttpServletRequest request,
			Integer pageNo) throws Throwable {
		return this.boardReviewsDao.findAll(request, pageNo);
	}

	// 해당 게시물 읽기
	@Override
	public BoardReviews getBoardReviewsByBdNoRev(Integer bdNoRev) {
		return this.boardReviewsDao.findByPrimaryKey(bdNoRev);
	}

	// 조회수 올리기
	@Override
	public void countUpForBdNoRev(Integer bdNoRev) {
		this.boardReviewsDao.countUp(bdNoRev);
	}

	// 후기 쓰기
	@Override
	public void boardRevWrite(BoardReviews boardRev, MemberVo userKey,
			String userIp) {
		this.boardReviewsDao.write(boardRev, userKey, userIp);
	}

	// 게시물 수정
	@Override
	public void boardReviewsWriteUpdate(BoardReviews boardRev, String userIp) {
		// TODO Auto-generated method stub
		this.boardReviewsDao.update(boardRev, userIp);
	}

	// 후기 답글
	@Override
	public void setBoardReviewsReplyByBdNoRev(BoardReviews boardRev,
			Integer bdNoRev, MemberVo userKey, String userIp) {
		// TODO Auto-generated method stub
		this.boardReviewsDao.reply(boardRev, bdNoRev, userKey, userIp);
	}
	// 후기 삭제
	@Override
	public void deleteBoardReviewsArticle(Integer bdNoRev) {
		// TODO Auto-generated method stub
		this.boardReviewsDao.delete(bdNoRev);	// 해당 게시물 삭제
	}

	// 추천은 하는데 같은사람은 추천 못해
	@Override
	public void RecommendUpByBdNoRev(Integer bdNoRev, MemberVo userKey,
			String userIp) {
		// TODO Auto-generated method stub
		this.boardReviewsDao.recommendUp(bdNoRev, userKey, userIp);
	}

	@Override
	public int getRecentNo() {
		// TODO Auto-generated method stub
		return this.boardReviewsDao.getRecentNo();
	}

	@Override
	public SaveFilePathTo getFilePath(int bdNoRev) {
		// TODO Auto-generated method stub
		return this.boardReviewsDao.getAllFilePathByNewBdNoRev(bdNoRev);
	}

	@Override
	public void setFilePath(int newBdNoRev, String forDb) {
		// TODO Auto-generated method stub
		this.boardReviewsDao.setFilePathForNew(newBdNoRev, forDb);
	}



}