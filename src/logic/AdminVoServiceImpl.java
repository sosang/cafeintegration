package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AdminDao;

@Service
@Transactional
public class AdminVoServiceImpl implements AdminVoService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public AdminVo getAdminInfo(String adminEmail, String adminPasswd) {
		// TODO Auto-generated method stub
		AdminVo adminVo = adminDao.getAdminInfo(adminEmail, adminPasswd);
		return adminVo;
	}

}
