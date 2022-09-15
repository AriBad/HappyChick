package happyChick.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAORecap;
import happyChick.exception.PoulaillerException;
import happyChick.exception.PouleException;
import happyChick.model.Activite;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Recap;
import happyChick.model.Saison;


@Service
public class PoulaillerService {
	@Autowired
	private IDAOPoulailler poulaillerRepo;

	@Autowired
	private IDAOPoule pouleRepo;
	
	@Autowired
	private IDAORecap recapRepo;
	
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
	
	public List<Poulailler> getAllByUserWithPoules(Integer id) {
		return poulaillerRepo.findAllByUserWithPoules(id);
	}
	
	public List<Poulailler> getAllByUser(Integer id) {
		return poulaillerRepo.findAllByUtilisateur(id);
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
		//Initialise les données au début du jeu avec un nouveau poulailler
		poulailler.setSecurite(1);
		poulailler.setTaille(100);
		poulailler.setNourriture(50);
		poulailler.setOeufs(5);
		poulailler.setAnnee(0);
		poulailler.setSaison(Saison.Printemps);
		poulailler.setCptSaisons(0);
		poulailler=poulaillerRepo.save(poulailler);
		Poule p1 = pouleService.create(new Poule ("Marlene",true,poulailler));
        Poule p2 = pouleService.create(new Poule ("Clara",true,poulailler));
        Poule p3 = pouleService.create(new Poule ("Jane",true,poulailler));
        Poule p4 = pouleService.create(new Poule ("Luna",true,poulailler));
        Poule p5 = pouleService.create(new Poule ("Marc",false,poulailler));        
        //Collections.addAll(poulailler.getListePoules(),p1,p2,p3,p4,p5);
        poulailler.getListeRecapLongs().add("Bienvenue dans votre poulailler dont vous allez être poule en chef. Vous avez découvert dans un fossé 5 oeufs qui viennent d'éclore, et qui ont donné naissance à 5 magnifiques poussins. Vous avez également reçu un un don anonyme de 10 portions de nourriture pour lancer votre activité.\n");
        
		String recapLongString = String.join(" ", poulailler.getListeRecapLongs());
		
		Recap recap = new Recap(poulailler,poulailler.getSaison(),poulailler.getAnnee(),"",recapLongString);
		recapRepo.save(recap);
        
		return poulaillerRepo.findById(poulailler.getId()).get();
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
			poulailler.setAnnee(poulailler.getAnnee() + 1);

		}
		poulailler.setActiviteSaison(a);
		if (agrandir) {poulailler.agrandir();}
		if (securiser) {poulailler.augmenterSecurite();}
		int cpt = 0;
		int nbOeufs = 0;
		
		List<Poule> listePoulestmp = poulailler.getPoulesVivantes();
		Collections.shuffle(listePoulestmp);
		
		System.out.println(poulesCouveuses);
		for (Poule p : listePoulestmp) {
			if (poulesCouveuses.containsKey(p)) {
				System.out.println("ICIIIIIIIIIII");
				pouleService.step(cpt < poulailler.getNourriture(), poulesCouveuses.get(p), p);
			}
			else {
				pouleService.step(cpt < poulailler.getNourriture(), 0,p);
			}
			nbOeufs += pouleService.oeufsPondus(p);
			cpt++;
		}
		poulailler.setOeufs(poulailler.getOeufs()+nbOeufs);
		poulailler.setCptSaisons(poulailler.getCptSaisons() + 1);
		
		String recapLongString = String.join(" ", poulailler.getListeRecapLongs());
		
		Recap recap = new Recap(poulailler,poulailler.getSaison(),poulailler.getAnnee(),"",recapLongString);
		recapRepo.save(recap);
		update(poulailler);
		
	}
	
	public void echangeNourriture(Poulailler poulailler, int portionNourriture) {
		poulailler.setOeufs(poulailler.getOeufs() - (portionNourriture*8));
		poulailler.setNourriture(poulailler.getNourriture() + portionNourriture);
	}
}
