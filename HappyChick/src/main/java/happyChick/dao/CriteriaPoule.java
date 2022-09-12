package happyChick.dao;

import java.util.List;

import happyChick.model.CauseMort;
import happyChick.model.Poule;
import happyChick.model.Temperament;

public interface CriteriaPoule {
	List<Poule> findPouleByTemperamentAndVie(Temperament temperament, CauseMort causeMort) ;
}
