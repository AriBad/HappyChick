package happyChick.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import happyChick.model.CauseMort;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Temperament;

public interface IDAOPoule extends JpaRepository<Poule, Integer> {

	void deleteByPoulailler(Poulailler poulailler);

	void deleteByTemperament(Temperament temperament);
	
	Optional <List<Poule>> findByTemperament(Temperament temperament);
	
	List<Poule> findPouleByTemperamentAndVie(Temperament temperament, CauseMort causeMort);
}

