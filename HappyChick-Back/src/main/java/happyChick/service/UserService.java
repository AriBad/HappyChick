package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAOUser;
import happyChick.exception.UserException;
import happyChick.model.User;


public class UserService {
	
	@Autowired
	private IDAOPoulailler poulaillerRepo;
	
	@Autowired
	private IDAOUser userRepo;

	public User getById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> {
			throw new UserException("id User inconnu");
		});
	}

	public List<User> getAll() {

		return userRepo.findAll();
	}
	
	public User create(User user) {
		if (user.getLogin() == null || user.getLogin().isEmpty()) {
			throw new UserException("Login obligatoire");
		}
		return userRepo.save(user);
	}
	
	public User update(User user) {
		if (user.getLogin() == null || user.getLogin().isEmpty()) {
			throw new UserException("Login obligatoire");
		}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new UserException("Password obligatoire");
		}
		User userEnBase = getById(user.getId());
		userEnBase.setLogin(user.getLogin());
		userEnBase.setPassword(user.getPassword());
		return userRepo.save(userEnBase);
	}

	public void delete(User user) {
		poulaillerRepo.deleteByUser(user);
		userRepo.delete(user);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
}
