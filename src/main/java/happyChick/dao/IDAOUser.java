package happyChick.dao;

import happyChick.model.User;

public interface IDAOUser extends IDAO<User,Integer>{

	public User seConnecter(String login,String password);
	
}
