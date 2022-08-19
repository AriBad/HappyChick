package happyChick.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Temperament;
import happyChick.model.Temperament2;

public interface IDAOPoule extends JpaRepository<Poule, Integer> {

	void deleteByPoulailler(Poulailler poulailler);

	void deleteByTemperament(Temperament temperament);
	
	List<Poule> findByTemperament(Temperament2 temperament);

}
