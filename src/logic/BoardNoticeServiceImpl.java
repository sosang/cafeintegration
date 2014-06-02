package logic;

import java.util.List;





import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Board_notice_DAO;

@Service
@Transactional
public class BoardNoticeServiceImpl implements BoardNoticeService {
	
	@Autowired
	private Board_notice_DAO boardNoticeDao;
	
	@Override
	public List<BoardNotice> getBoardNoticeList(HttpServletRequest request, Integer pageNo) throws Throwable {
		// TODO Auto-generated method stub
		return this.boardNoticeDao.findAll(request, pageNo);	// 게시물 싹다 불러오기
	}

	@Override
	public Integer getTotalCount() {
		// TODO Auto-generated method stub
		return this.boardNoticeDao.getTotalCount();	// 총 게시물 수
	}

	@Override
	public BoardNotice getBoardNoticeByBdNoNtc(Integer bdNoNtc) {
		// TODO Auto-generated method stub
		return this.boardNoticeDao.findByPrimaryKey(bdNoNtc);//클릭한 게시물 DB에서 읽기
	}

	@Override
	public void countUp(Integer bdNoNtc) {
		// TODO Auto-generated method stub
		this.boardNoticeDao.countUp(bdNoNtc);//조회수 올리기
		
	}

	@Override
	public Integer getLastBdNoNtc() {
		// TODO Auto-generated method stub
		return this.boardNoticeDao.getLastBdNoNtc();	// 게시물 마지막 번호 왜있는지 모름 ㅋ(똥)
	}



}
