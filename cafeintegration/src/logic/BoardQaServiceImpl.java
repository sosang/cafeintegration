package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BoardQaDao;

@Service
@Transactional
public class BoardQaServiceImpl implements BoardQaService {
	
	@Autowired
	private BoardQaDao boardQaDao;

	@Override
	public List<BoardQa> getBoardQaList(HttpServletRequest request,
			Integer pageNo) throws Throwable {
		// TODO Auto-generated method stub
		return this.boardQaDao.findAll(request, pageNo);
	}

	@Override
	public BoardQa getBoardQaByBdNoQa(Integer bdNoQa) {
		// TODO Auto-generated method stub
		return this.boardQaDao.findByPrimaryKey(bdNoQa);//클릭한 게시물 DB에서 읽기
	}
	
	
	@Override
	public void countUpForBdNoQa(Integer bdNoQa) {
		// TODO Auto-generated method stub
		this.boardQaDao.countUp(bdNoQa);
	}

	@Override
	public void boardQaWrite(BoardQa boardQa, MemberVo userKey, String userIp) {
		// TODO Auto-generated method stub
		this.boardQaDao.write(boardQa, userKey, userIp); //게시물 쓰기
	}

	@Override
	public void boardQaWriteUpdate(BoardQa boardQa, String userIp) {
		// TODO Auto-generated method stub
		this.boardQaDao.update(boardQa, userIp); // 게시물 수정
	}

	@Override
	public void setBoardQaReplyByBdNoQa(BoardQa boardQa, Integer bdNoQa, MemberVo userKey,
			String userIp) {
		// TODO Auto-generated method stub
		this.boardQaDao.reply(boardQa, bdNoQa, userKey, userIp); // 해당 게시물 답글
	}

	@Override
	public void deleteBoardQaArticle(Integer bdNoQa) {
		// TODO Auto-generated method stub
		this.boardQaDao.delete(bdNoQa);	// 해당 게시물 삭제
	}
	
	

}
