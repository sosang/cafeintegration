package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BoardReviewsCommentsDao;

@Service
@Transactional
public class BoardReviewsCommentsServiceImpl implements
		BoardReviewsCommentsService {
	
	@Autowired
	BoardReviewsCommentsDao boardReviewsCommentsDao;

	@Override
	public List<BoardReviewsComments> getBoardQaCommentsList(
			HttpServletRequest request, Integer pageNo, Integer bdNoRev)
			throws Throwable {
		// TODO Auto-generated method stub
		return this.boardReviewsCommentsDao.findAll(request, pageNo, bdNoRev);	// 모든 댓글 불러오기
	}

	@Override
	public void setCommentsAtBdNoRev(BoardReviewsComments bdRevCom,
			MemberVo userKey, Integer bdNoRev, String userIp) {
		// TODO Auto-generated method stub
		this.boardReviewsCommentsDao.writeCommentsAtBdNoRev(bdRevCom, userKey, bdNoRev, userIp);
	}

	@Override
	public void deleteBoardQaComment(Integer bdNoRevComments) {
		// TODO Auto-generated method stub
		this.boardReviewsCommentsDao.deleteCommentsByBdNoQaComments(bdNoRevComments);	// 댓글 지우기
	}

	@Override
	public List<BoardReviewsComments> getBoardRevCommentsList(
			HttpServletRequest request, Integer pageNo, Integer bdNoRev) throws Throwable {
		// TODO Auto-generated method stub
		return this.boardReviewsCommentsDao.findAll(request, pageNo, bdNoRev);	// 모든 댓글 불러오기
	}
	
	
	
	

}
