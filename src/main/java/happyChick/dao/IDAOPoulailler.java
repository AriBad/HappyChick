package happyChick.dao;

import happyChick.model.Poulailler;

public interface IDAOPoulailler extends IDAO<Poulailler, Integer> {
	public Poulailler findByIdWithPoules(Integer id);
}
