package happyChick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Temperament;
import happyChick.model.User;

public interface IDAOPoule extends JpaRepository<Poule, Integer> {

	void deleteByPoulailler(Poulailler poulailler);

	void deleteByTemperament(Temperament temperament);


}
