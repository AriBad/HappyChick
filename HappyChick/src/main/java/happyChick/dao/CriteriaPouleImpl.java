package happyChick.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import happyChick.model.CauseMort;
import happyChick.model.Poule;
import happyChick.model.Temperament;

public class CriteriaPouleImpl implements CriteriaPoule {
	
	public  List<Poule> findPouleByTemperamentAndVie(Temperament temperament, CauseMort causeMort) {
	    	
		    EntityManager em = null;
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Poule> cq = cb.createQuery(Poule.class);

	        Root<Poule> poule = cq.from(Poule.class);
	        List<Predicate> predicates = new ArrayList<>();
	        if (temperament != null) {
	            predicates.add(cb.equal(poule.get("temperament"), temperament));
	        }
	        if (causeMort != null) {
	            predicates.add(cb.equal(poule.get("causeMort"), causeMort));
	        }
	        cq.where(predicates.toArray(new Predicate[0]));

	        return em.createQuery(cq).getResultList();
	    }

}
