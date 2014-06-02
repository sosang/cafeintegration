package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PostcodeDao;

@Service
public class PostcodeCatalog {

	@Autowired
	private PostcodeDao postcodeDao;

	public List<Postcode> getPostcodeByEupmyeondong(String eupmyeondong) {
		return this.postcodeDao.findByEupmyeondong(eupmyeondong);
	}

}