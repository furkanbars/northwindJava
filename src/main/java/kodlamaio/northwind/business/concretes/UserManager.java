package kodlamaio.northwind.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import lombok.var;

@Service
public class UserManager implements UserService{
	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao=userDao;
	}
	@Override
	public Result add(User user) {
	     this.userDao.save(user);
		return new SuccessResult("Kişi eklendi.");
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		var result=this.userDao.getByEmail(email);
		if (result!=null) {
			return new SuccessDataResult<User>(result,"Kişi bulundu.");
		}
		return new ErrorDataResult<User>("Kişi bulunamadı!");
	}
}
