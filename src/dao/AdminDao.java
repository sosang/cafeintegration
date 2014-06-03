package dao;

import logic.AdminVo;

public interface AdminDao {
	AdminVo getAdminInfo(String adminEmail, String adminPasswd);
}
