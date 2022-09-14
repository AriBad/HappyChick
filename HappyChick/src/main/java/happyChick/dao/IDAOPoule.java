package happyChick.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import happyChick.model.CauseMort;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Temperament;

public interface IDAOPoule extends JpaRepository<Poule, Integer> {
	@Transactional
	void deleteByPoulailler(Poulailler poulailler);

	void deleteByTemperament(Temperament temperament);
	
	Optional <List<Poule>> findByTemperament(Temperament temperament);
	
Optional <List<Poule>> findByPoulaillerAndCauseMort(Poulailler poulailler, CauseMort causemort);
	
	@Query("SELECT p from Poule p where p.poulailler.id=:id and p.causeMort IS NULL")
	Optional <List<Poule>> findByVivante(Integer id);
}

