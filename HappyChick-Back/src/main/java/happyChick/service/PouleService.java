package happyChick.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import happyChick.dao.IDAOPoule;
import happyChick.exception.PouleException;
import happyChick.model.CauseMort;
import happyChick.model.Etat;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Temperament;
import happyChick.model.Temperament2;
import happyChick.tools.JsonNameParser;


public class PouleService {
	
	@Autowired
	private JsonNameParser jsonNameParser;
	@Autowired
	private IDAOPoule pouleRepo;

	public Poule getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findById(id).orElseThrow(() -> {
			throw new PouleException("id Poule inconnu");
		});
	}
	public List<Poule> getByTemperament(Temperament2 temperament) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findByTemperament(temperament).orElseThrow(() -> {
			throw new PouleException("Temperament de la poule inconnu");
		});
	}

	public List<Poule> getAll() {
		// poulaillerRepo.findAll().forEach(System.out::println);
//		poulaillerRepo.findAll().forEach((p) -> {
//			System.out.println(p);
//		});
		return pouleRepo.findAll();
	}
	
	public Poule create(Poule poule) {
		return pouleRepo.save(poule);
	}
	
	public Poule update(Poule poule) {
		if (poule == null || pouleRepo.findById(poule.getId()) == null ) {
			throw new PouleException("Update une poule qui n'existe pas en base");
		}
		return pouleRepo.save(poule);
	}

	public void delete(Poule poule) {
		pouleRepo.delete(poule);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
	
	public void step(boolean nourriture, int oeufsCouves, Poule poule) {
	

		if (poule.getEtat() == Etat.Couvaison) {
			oeufsEclos(poule.getOeufsCouves());
			poule.setOeufsCouves(0);
			poule.setEtat(Etat.Maternage);
		} else if (poule.getEtat() == Etat.Liberte && oeufsCouves !=0) {
			poule.setOeufsCouves(oeufsCouves);
			poule.setEtat(Etat.Couvaison);
			poule.setPonte(0);
		} else if (poule.getEtat() == Etat.Maternage) {
			poule.setEtat(Etat.Liberte);
		}


		if (nourriture) {
			manger(poule);
		} else {
			poule.setSaisonSansManger(poule.getSaisonSansManger()+ 1);
		}

		grandir(poule);
	}

	public void grandir(Poule poule) {
		poule.setAge(poule.getAge()+0.25);
		if (poule.getAge()==0.25) {
			passageAdulte(poule);
		}
		if (poule.isFemelle() && !poule.isPoussin()) {
			majVariables(poule);
		}
		mort(poule);
	}

	public void manger(Poule poule) {
		poule.getPoulailler().setNourriture(poule.getPoulailler().getNourriture()-1);
		poule.setSaisonSansManger(0);
		System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") a mangé.");
	}

	public void mort(Poule poule) {
		Random r = new Random();
		if ( r.nextDouble() < poule.getMaladie()) {
			poule.setCauseMort(CauseMort.Maladie);
			System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte de maladie.");
		} else if (r.nextDouble() < poule.getPredation()) {
			if (r.nextDouble() < (poule.getPoulailler().getNbPsychopathe()*0.05)){
				poule.setCauseMort(CauseMort.Predation);
				System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte à cause d'un prédateur.");
			}
		} else if (r.nextDouble() < 0.0025 * (poule.getAge() * poule.getAge()) && poule.getAge() > 10) {
			poule.setCauseMort(CauseMort.Age);
			System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte de Vieillesse.");
		} else if (r.nextDouble() < poule.getSaisonSansManger() * 0.334) {
			poule.setCauseMort(CauseMort.Faim);
		}
	}

	public void passageAdulte(Poule poule) {
		poule.setPoussin(false);
		if (poule.isFemelle()) {
			if (poule.getPoulailler().getSecurite()>5 && poule.getTemperament(Temperament2.pyromane).getPoules().size()<5) {
				Random r = new Random();
				int alea = r.nextInt(11); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
				if (alea==0 || alea==1 || alea==2) {
					poule.setTemperament(Temperament2.serieuse);
				} else if (alea==3 || alea==4 || alea==5) {
					poule.setTemperament(Temperament2.mamanPoule);
				} else if (alea==6 || alea==7 || alea==8) {
					poule.setTemperament(Temperament2.insouciante);
				} else if (alea==9) {
					poule.setTemperament(Temperament2.psychopathe);
				} else if (alea==10) {
					poule.setTemperament(Temperament2.pyromane);
				}
			} else {
				Random r = new Random();
				int alea = r.nextInt(10); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
				if (alea==0 || alea==1 || alea==2) {
					poule.setTemperament(Temperament2.serieuse);
				} else if (alea==3 || alea==4 || alea==5) {
					poule.setTemperament(Temperament2.mamanPoule);
				} else if (alea==6 || alea==7 || alea==8) {
					poule.setTemperament(Temperament2.insouciante);
				} else if (alea==9) {
					poule.setTemperament(Temperament2.pyromane);
				}
			}
			genererVariablesBase(poule);
		}
	}

	public int oeufsPondus(Poule poule) {
		int pondus=(int) (poule.getPonte());
		if (poule.getEtat() == Etat.Liberte) {
			return (int) (poule.getPonte());
		} else {return 0;}
	}

	public int oeufsEclos(Poule poule, int oeufsCouves) {
		int oeufsEclos;
		oeufsEclos=((int) (poule.getMaternage()*oeufsCouves));
		for (int i = 0; i < oeufsEclos; i++) {
			naissance(poule);
		}
		return oeufsEclos;
	}

	public void naissance(Poule poule) {
		Random r = new Random();
		Poule p;
		if (r.nextDouble()<0.1) {
			p = new Poule(jsonNameParser.genererNomCoq(true), false, poule.getPoulailler());
			
		} else {
			p = new Poule(jsonNameParser.genererNomPoule(true), true, poule.getPoulailler());
		}
		poule.getPoulailler().indiquerNaissance(p);
	}

}
