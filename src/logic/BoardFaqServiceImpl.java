package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BoardFaqDao;

@Service
@Transactional
public class BoardFaqServiceImpl implements BoardFaqService {
	
	@Autowired
	BoardFaqDao boardFaqDao;

	@Override
	public List<BoardFaq> getBoardFaqList(HttpServletRequest request,
			Integer pageNo) throws Throwable {
		// TODO Auto-generated method stub
		return this.boardFaqDao.findAll(request, pageNo);
	}

	@Override
	public void boardFaqWrite(BoardFaq boardFaq) {
		// TODO Auto-generated method stub
		this.boardFaqDao.writeFaq(boardFaq);
	}

	@Override
	public void deleteBoardFaqArticle(Integer bdNoFaq) {
		// TODO Auto-generated method stub
		this.boardFaqDao.DeleteFaq(bdNoFaq);
	}

	@Override
	public BoardFaq getBoardFaqByBdNoFaq(Integer bdNoFaq) {
		// TODO Auto-generated method stub
		return this.boardFaqDao.getFaqByBdNoFaq(bdNoFaq);
	}

	@Override
	public void boardFaqUpdate(BoardFaq boardFaq) {
		// TODO Auto-generated method stub
		this.boardFaqDao.Update(boardFaq);
	}

	
	
}
