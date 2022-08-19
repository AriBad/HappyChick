package happyChick.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import happyChick.dao.IDAOPoule;
import happyChick.exception.PouleException;
import happyChick.model.CauseMort;
import happyChick.model.Etat;
import happyChick.model.Poule;


public class PouleService {
	
	@Autowired
	private IDAOPoule pouleRepo;

	public Poule getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findById(id).orElseThrow(() -> {
			throw new PouleException("id Poulailler inconnu");
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
			manger();
		} else {
			poule.setSaisonSansManger(poule.getSaisonSansManger()+ 1);
		}

		grandir(poule);
	}

	public void grandir(Poule poule) {
		poule.setAge(poule.getAge()+0.25);
		if (this.age==0.25) {
			passageAdulte();
		}
		if (femelle && !poussin) {
			temperament.majVariables(this);
		}
		mort();
	}

	public void manger() {
		poulailler.setNourriture(poulailler.getNourriture()-1);
		this.saisonSansManger=0;
		System.out.println("La poule "+this.prenom+"(id="+this.id+") a mangé.");
	}

	public void mort() {
		Random r = new Random();
		if ( r.nextDouble() < maladie ) {
			causeMort = CauseMort.Maladie;
			System.out.println("La poule "+this.prenom+"(id="+this.id+") est morte de maladie.");
		} else if (r.nextDouble() < predation) {
			if (r.nextDouble() < (poulailler.getNbPsychopathe()*0.05)){
				causeMort = CauseMort.Predation;
				System.out.println("La poule "+this.prenom+"(id="+this.id+") est morte à cause d'un prédateur.");
			}
		} else if (r.nextDouble() < 0.0025 * (age * age) && age > 10) {
			causeMort = CauseMort.Age;
			System.out.println("La poule "+this.prenom+"(id="+this.id+") est morte de Vieillesse.");
		} else if (r.nextDouble() < saisonSansManger * 0.334) {
			causeMort = CauseMort.Faim;
		}
	}

	public void passageAdulte() {
		this.poussin=false;
		if (this.femelle) {
			if (this.poulailler.getSecurite()>5 && pyromane.getPoules().size()<5) {
				Random r = new Random();
				int alea = r.nextInt(11); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
				if (alea==0 || alea==1 || alea==2) {
					this.temperament = serieuse;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue serieuse.");
				} else if (alea==3 || alea==4 || alea==5) {
					this.temperament = mamanPoule;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Maman Poule.");
				} else if (alea==6 || alea==7 || alea==8) {
					this.temperament = insouciante;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Insouciante.");
				} else if (alea==9) {
					this.temperament = psychopathe;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Psychopathe.");
				} else if (alea==10) {
					this.temperament = pyromane;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Pyromane.");
				}
			} else {
				Random r = new Random();
				int alea = r.nextInt(10); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
				if (alea==0 || alea==1 || alea==2) {
					this.temperament = serieuse;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue serieuse.");
				} else if (alea==3 || alea==4 || alea==5) {
					this.temperament = mamanPoule;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Maman Poule.");
				} else if (alea==6 || alea==7 || alea==8) {
					this.temperament = insouciante;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Insouciante.");
				} else if (alea==9) {
					this.temperament = pyromane;
					System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Psychopathe.");
				}
			}
			this.temperament.getPoules().add(this);
			temperament.genererVariablesBase(this);
		}
	}

	public int oeufsPondus() {
		int pondus=(int) (ponte);
		if (etat == Etat.Liberte) {
			System.out.println("La poule "+this.prenom+" a pondu "+pondus+" oeufs.");
			return (int) (ponte);
		} else {return 0;}
	}

	public int oeufsEclos(int oeufsCouves) {
		int oeufsEclos;
		System.out.println("Le taux de maternage est "+getMaternage());
		oeufsEclos=((int) (getMaternage()*oeufsCouves));
		System.out.println("La poule "+this.prenom+"(id="+this.id+") a donné naissance à "+oeufsEclos+" sur "+oeufsCouves+" oeufs couvés.");
		for (int i = 0; i < oeufsEclos; i++) {
			naissance();
		}
		return oeufsEclos;
	}

	public void naissance() {
		Random r = new Random();
		Poule p;
		if (r.nextDouble()<0.1) {
			p = new Poule(jsonNamePaser.genererNomCoq(true), false, this.poulailler);
			
		} else {
			p = new Poule(jsonNamePaser.genererNomPoule(true), true, this.poulailler);
		}
		poulailler.indiquerNaissance(p);
		System.out.println("La poule "+p.prenom+"(id="+p.id+") est né !!!.");

	}

}
