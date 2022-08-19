package happyChick.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.exception.PoulaillerException;
import happyChick.exception.PouleException;
import happyChick.model.Activite;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Saison;

@Service
public class PoulaillerService {
	@Autowired
	private IDAOPoulailler poulaillerRepo;

	@Autowired
	private IDAOPoule pouleRepo;
	
	@Autowired
	private PouleService pouleService;

	public Poulailler getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return poulaillerRepo.findById(id).orElseThrow(() -> {
			throw new PoulaillerException("id Poulailler inconnu");
		});
	}

	public List<Poulailler> getAllWithPoules() {
		return poulaillerRepo.findAllWithPoules();
	}
	
	public List<Poulailler> getAll() {
		// poulaillerRepo.findAll().forEach(System.out::println);
//		poulaillerRepo.findAll().forEach((p) -> {
//			System.out.println(p);
//		});
		return poulaillerRepo.findAll();
	}
	
	public Poulailler getByIdWithPoules(Integer id) {
		return poulaillerRepo.findByIdWithPoules(id).orElseThrow(() -> {
			throw new PoulaillerException("id Poulailler inconnu");
		});
	}

	public Poulailler create(Poulailler poulailler) {
		if (poulailler.getNom() == null || poulailler.getNom().isEmpty()) {
			throw new PoulaillerException("nom obligatoire");
		}
		return poulaillerRepo.save(poulailler);
	}

	public Poulailler update(Poulailler poulailler) {
		if (poulailler == null || pouleRepo.findById(poulailler.getId()) == null ) {
			throw new PoulaillerException("Update un poulailler qui n'existe pas en base");
		}
		return poulaillerRepo.save(poulailler);
	}

	public void delete(Poulailler poulailler) {
		pouleRepo.deleteByPoulailler(poulailler);
		poulaillerRepo.delete(poulailler);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
	
	public void step(Poulailler poulailler, Activite a, int portionNourriture, Map<Poule, Integer> poulesCouveuses, boolean agrandir, boolean securiser) {
		echangeNourriture(poulailler, portionNourriture);
		poulailler.setSaison(Saison.saisonSuivante(poulailler.getSaison()));
		if (poulailler.getSaison() == Saison.Printemps) {
			poulailler.setAnnee(poulailler.getAnnee() + 1);;
		}
		poulailler.setActiviteSaison(a);
		if (agrandir) {poulailler.agrandir();}
		if (securiser) {poulailler.augmenterSecurite();}
		int cpt = 0;
		int nbOeufs = 0;
		
		List<Poule> listePoulestmp = poulailler.getPoulesVivantes();
		Collections.shuffle(listePoulestmp);
		for (Poule p : listePoulestmp) {
			if (poulesCouveuses.containsKey(p)) {
				pouleService.step(cpt<poulailler.getNourriture(), poulesCouveuses.get(p), p);
			}
			else {
				pouleService.step(cpt < poulailler.getNourriture(), 0,p);
			}
			nbOeufs += pouleService.oeufsPondus(p);
			cpt++;
		}
		poulailler.setOeufs(poulailler.getOeufs()+nbOeufs);
		
		update(poulailler);
		
	}
	
	public void echangeNourriture(Poulailler poulailler, int portionNourriture) {
		poulailler.setOeufs(poulailler.getOeufs() - (portionNourriture*50));
		poulailler.setNourriture(poulailler.getNourriture() + portionNourriture);
	}
}
