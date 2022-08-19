package happyChick.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Temperament;

public interface IDAOPoule extends JpaRepository<Poule, Integer> {

	void deleteByPoulailler(Poulailler poulailler);

	void deleteByTemperament(Temperament temperament);
	
	Optional <List<Poule>> findByTemperament(Temperament temperament);

}
