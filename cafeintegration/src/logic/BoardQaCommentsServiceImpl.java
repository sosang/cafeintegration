package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BoardQaCommentsDao;

@Service
@Transactional
public class BoardQaCommentsServiceImpl implements BoardQaCommentsService {
	
	@Autowired
	BoardQaCommentsDao boardQaCommentsDao;

	@Override
	public List<BoardQaComments> getBoardQaCommentsList(
			HttpServletRequest request, Integer pageNo, Integer bdNoQa) throws Throwable {
		// TODO Auto-generated method stub
		return this.boardQaCommentsDao.findAll(request, pageNo, bdNoQa);	// 모든 댓글 불러오기
	}

	@Override
	public int getTotalCommentsCount(Integer bdNoQa) {
		// TODO Auto-generated method stub
		return this.boardQaCommentsDao.getTotalCommentsCount(bdNoQa);	// 댓글의 갯수 불러오기
	}

	@Override
	public void setCommentsAtBdNoQa(BoardQaComments bdQaCom, MemberVo userKey, Integer bdNoQa, String userIp) {
		// TODO Auto-generated method stub
		this.boardQaCommentsDao.writeCommentsAtBdNoQa(bdQaCom, userKey, bdNoQa, userIp);	// 댓글 쓰기
	}

	@Override
	public void deleteBoardQaComment(Integer bdNoQaComments) {
		// TODO Auto-generated method stub
		this.boardQaCommentsDao.deleteCommentsByBdNoQaComments(bdNoQaComments);
	}
	
	

}
