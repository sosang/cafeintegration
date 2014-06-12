package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;

@Service
public class MemberCatalogImpl implements MemberCatalog {

	@Autowired
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public MemberVo getMemberByUserEmailAndUserPasswd(String userEmail,
			String userPasswd) {
		// TODO Auto-generated method stub
		return this.memberDao.findByUserEmailAndUserPasswd(userEmail, userPasswd);
	}

	@Override
	public void entryMember(MemberVo member) {
		this.memberDao.create(member);

	}

	// userEmail 중복체크
	@Override
	public int getCheckedUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return this.memberDao.checkUserEmail(userEmail);
	}
	
	// userAlias 중복체크
		@Override
		public int getCheckedUserAlias(String userAlias) {
			// TODO Auto-generated method stub
			return this.memberDao.checkUserAlias(userAlias);
		}

	@Override
	public List<MemberVo> findAllMember(HttpServletRequest request,
			Integer pageNo) {
		// TODO Auto-generated method stub
		return this.memberDao.findAllMemberList(request, pageNo);
	}





}