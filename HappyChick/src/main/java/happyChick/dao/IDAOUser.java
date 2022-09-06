package happyChick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.User;

public interface IDAOUser extends JpaRepository<User,Integer>{

	public User findByPasswordAndLogin(String login,String password);
	
}
