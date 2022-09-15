package happyChick.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAORecap;
import happyChick.exception.PouleException;
import happyChick.model.Activite;
import happyChick.model.CauseMort;
import happyChick.model.Etat;
import happyChick.model.Poule;
import happyChick.model.Temperament;
import happyChick.tools.JsonNameParser;

@Service
public class PouleService {

	@Autowired
	private JsonNameParser jsonNameParser;
	@Autowired
	private IDAOPoule pouleRepo;
	@Autowired
	private IDAOPoulailler poulaillerRepo;
	
	@Autowired
	private IDAORecap recapRepo;

	public Poule getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findById(id).orElseThrow(() -> {
			throw new PouleException("id Poule inconnu");
		});
	}
	public List<Poule> getByTemperament(Temperament temperament) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findByTemperament(temperament).orElseThrow(() -> {
			throw new PouleException("Temperament de la poule inconnu");
		});
	}
	
	public List<Poule> getByPoulaillerCauseMort(Integer id, String causemort) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findByPoulaillerAndCauseMort(poulaillerRepo.findById(id).get(),CauseMort.valueOf(causemort) ).orElseThrow(() -> {
			throw new PouleException("La cause le mort de la poule est inconnu");
		});
	}
	
	public List<Poule> getByPoulaillerVivante(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findByVivante(id).orElseThrow(() -> {
			throw new PouleException("Le poulailler est inconnu");
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
			oeufsEclos(poule, poule.getOeufsCouves());
			poule.setOeufsCouves(0);
			poule.setEtat(Etat.Maternage);
			poule.getPoulailler().getListeRecapLongs().add("La poule"+poule.getPrenom()+" est en maternage");
			System.out.println("La poule"+poule.getPrenom()+" est en maternage");
		} else if (poule.getEtat() == Etat.Liberte && oeufsCouves !=0) {
			poule.setOeufsCouves(oeufsCouves);
			poule.setEtat(Etat.Couvaison);
			poule.setPonte(0);
			poule.getPoulailler().getListeRecapLongs().add("La poule"+poule.getPrenom()+" est en couvaison");
			System.out.println("ICIIIIIIIIIII Liberte");
		} else if (poule.getEtat() == Etat.Maternage) {
			poule.setEtat(Etat.Liberte);
			poule.getPoulailler().getListeRecapLongs().add("La poule"+poule.getPrenom()+" est en liberté");
			System.out.println("ICIIIIIIIIIII Maternage");
		}


		if (nourriture) {
			manger(poule);
		} else {
			poule.setSaisonSansManger(poule.getSaisonSansManger()+ 1);
		}

		grandir(poule);
		update(poule);
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
		poule.getPoulailler().getListeRecapLongs().add("La poule "+poule.getPrenom()+"(id="+poule.getId()+") a mangé.");
		System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") a mangé.");
	}

	public void mort(Poule poule) {
		Random r = new Random();
		if ( r.nextDouble() < poule.getMaladie()) {
			poule.setCauseMort(CauseMort.Maladie);
			poule.getPoulailler().getListeRecapLongs().add("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte de maladie.");
			System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte de maladie.");
		} else if (r.nextDouble() < poule.getPredation()) {
			if (r.nextDouble() < (poule.getPoulailler().getNbPsychopathe()*0.05)){
				poule.setCauseMort(CauseMort.Predation);
				poule.getPoulailler().getListeRecapLongs().add("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte à cause d'un prédateur.");
				System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte à cause d'un prédateur.");
			}
		} else if (r.nextDouble() < 0.0025 * (poule.getAge() * poule.getAge()) && poule.getAge() > 10) {
			poule.setCauseMort(CauseMort.Age);
			poule.getPoulailler().getListeRecapLongs().add("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte de Vieillesse.");
			System.out.println("La poule "+poule.getPrenom()+"(id="+poule.getId()+") est morte de Vieillesse.");
		} else if (r.nextDouble() < poule.getSaisonSansManger() * 0.334) {
			poule.setCauseMort(CauseMort.Faim);
		}
	}

	public void passageAdulte(Poule poule) {
		poule.setPoussin(false);
		if (poule.isFemelle()) {
			if (poule.getPoulailler().getSecurite()>5 && getByTemperament(Temperament.pyromane).size()<5) {
				Random r = new Random();
				int alea = r.nextInt(11); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
				if (alea==0 || alea==1 || alea==2) {
					poule.setTemperament(Temperament.serieuse);
				} else if (alea==3 || alea==4 || alea==5) {
					poule.setTemperament(Temperament.mamanPoule);
				} else if (alea==6 || alea==7 || alea==8) {
					poule.setTemperament(Temperament.insouciante);
				} else if (alea==9) {
					poule.setTemperament(Temperament.psychopathe);
				} else if (alea==10) {
					poule.setTemperament(Temperament.pyromane);
				}
			} else {
				Random r = new Random();
				int alea = r.nextInt(10); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
				if (alea==0 || alea==1 || alea==2) {
					poule.setTemperament(Temperament.serieuse);
				} else if (alea==3 || alea==4 || alea==5) {
					poule.setTemperament(Temperament.mamanPoule);
				} else if (alea==6 || alea==7 || alea==8) {
					poule.setTemperament(Temperament.insouciante);
				} else if (alea==9) {
					poule.setTemperament(Temperament.psychopathe);
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
		System.out.println("personne n'est née !");
		for (int i = 0; i < oeufsEclos; i++) {
			naissance(poule);
		}
		return oeufsEclos;
	}

	public void naissance(Poule poule) {
		Random r = new Random();
		Poule p;
		if (r.nextDouble()<0.1) {
			p = create(new Poule(jsonNameParser.genererNomCoq(true), false, poule.getPoulailler()));
			poule.getPoulailler().getListeRecapLongs().add("Le coq "+p.getPrenom() + " est née !");
			System.out.println(p.getPrenom() + " est née !");
			poule.getPoulailler().indiquerNaissance(p);
		} else {
			p = create(new Poule(jsonNameParser.genererNomPoule(true), true, poule.getPoulailler()));
			poule.getPoulailler().getListeRecapLongs().add("La poule "+p.getPrenom() + " est née !");
			System.out.println(p.getPrenom() + " est née !");
			poule.getPoulailler().indiquerNaissance(p);
		}
		
	}


	private void majVariables(Poule poule) {
		majBonheur(poule);
		majMaternage(poule);
		majPredation(poule);
		majMaladie(poule);
		majPonte(poule);
		if (poule.getTemperament()==Temperament.pyromane) {
			toutBruler(poule);
		} else if (poule.getTemperament()==Temperament.psychopathe) {
			tuerPoule(poule);
		}

	}

	private void genererVariablesBase(Poule poule) {
		this.majMaternage(poule);
		this.majPonte(poule);
		poule.setBonheur(50);

	}


	private void majPonte(Poule poule) {
		if (poule.getTemperament()==Temperament.mamanPoule) {
			Random r = new Random();
			int alea = r.nextInt(5) + 10;
			poule.setPonte((poule.getBonheur()/100)*(3/(1+poule.getAge()))*alea);
		} else {
			if (poule.getEtat()==Etat.Liberte) {
				Random r = new Random();
				int alea = r.nextInt(5) + 5;
				poule.setPonte((poule.getBonheur()/100)*(3/(1+poule.getAge()))*alea);
			} else {
				poule.setPonte(0);
			}
		}

	}
	private void majMaladie(Poule poule) {
		if (poule.getTemperament()==Temperament.mamanPoule) {
			if (poule.getEtat() == Etat.Couvaison ){
				poule.setMaladie((0.5*(poule.getPoulailler().getNbPoulesVivantes()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100)))*5);
			}
			else {
				poule.setMaladie((0.5*(poule.getPoulailler().getNbPoulesVivantes()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100))));
			}
			
		} else {
			if (poule.getEtat() == Etat.Couvaison ){
				poule.setMaladie((0.5*(poule.getPoulailler().getNbPoulesVivantes()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100)))*5);
			}
			else {
				poule.setMaladie((0.5*(poule.getPoulailler().getNbPoulesVivantes()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100))));
			}
		}	
	}
	private void majPredation(Poule poule) {
		if (poule.getTemperament()==Temperament.psychopathe) {
			poule.setPredation( 0.05 * (1/poule.getPoulailler().getSecurite()) );
		} else if (poule.getTemperament()==Temperament.insouciante) {
			poule.setPredation( 0.75 * (1/poule.getPoulailler().getSecurite()) );
		} else if (poule.getTemperament()==Temperament.serieuse) {
			poule.setPredation( 0.3 * (1/poule.getPoulailler().getSecurite()) );
		} else {
			poule.setPredation( 0.5 * (1/poule.getPoulailler().getSecurite()) );
		}

	}
	private void majMaternage(Poule poule) {
		if (poule.getTemperament()==Temperament.mamanPoule) {
			poule.setMaternage(0.8);
		} else if (poule.getTemperament()==Temperament.pyromane) {
			poule.setMaternage(0.7);
		} else {
			poule.setMaternage(0.6);
		}

	}
	private void majBonheur(Poule poule) {
		if (poule.getPoulailler().getActiviteSaison()==Activite.Escrime)  {
			if (poule.getTemperament()==Temperament.mamanPoule) {
				poule.setBonheur(poule.getBonheur()+20 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()-poule.getPoulailler().getSecurite()*5+poule.getPoulailler().getNbPsychopathe()*0.1);
			} else {
				poule.setBonheur(poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()+poule.getPoulailler().getNbPsychopathe()*0.1);
			}
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.Tricot) {
			if (poule.getTemperament()==Temperament.serieuse) {
				poule.setBonheur(poule.getBonheur()+20 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()-poule.getPoulailler().getSecurite()*5+poule.getPoulailler().getNbPsychopathe()*0.1);
			} else {
				poule.setBonheur(poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()+poule.getPoulailler().getNbPsychopathe()*0.1);
			}
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.Danse) {
			if (poule.getTemperament()==Temperament.insouciante) {
				poule.setBonheur(poule.getBonheur()+20 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()-poule.getPoulailler().getSecurite()*5+poule.getPoulailler().getNbPsychopathe()*0.1);
			} else {
				poule.setBonheur(poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()+poule.getPoulailler().getNbPsychopathe()*0.1);
			}
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.SortieGenerale) {
			poule.setBonheur(poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()+poule.getPoulailler().getNbPsychopathe()*0.1);
		}
		if (poule.getBonheur()>100) {poule.setBonheur(100);}

	}

	private void toutBruler(Poule poule) {
		int nbOeufs=poule.getPoulailler().getOeufs();
		boolean premier =true;
		for (int i = 2; i < nbOeufs; i++) {
			if (nbOeufs % i == 0)
				premier=false;
		}
		if (premier==true) {
			if (poule.getPoulailler().getSecurite()>1) {
				poule.getPoulailler().setSecurite(poule.getPoulailler().getSecurite()-1);
				System.out.println("!!!!!   LE POULAILLER A TROP CRAMER !!!!!!!!");
			}
		}

	}
	private void tuerPoule(Poule poule) {
		Random r1 = new Random();
		if ( r1.nextDouble() > (poule.getBonheur()/100)) {
			Random r2 = new Random();
			int alea = r2.nextInt(poule.getPoulailler().getNbPoulesVivantes());

			poule.getPoulailler().getPoulesVivantes().get(alea).setCauseMort(CauseMort.Meurtre);
			poule.getPoulailler().getListeRecapLongs().add("La poule psychopathe à tuer la poule "+poule.getPoulailler().getPoulesVivantes().get(alea));
			System.out.println("La poule psychopathe à tuer la poule "+poule.getPoulailler().getPoulesVivantes().get(alea));
		}

	}
}
