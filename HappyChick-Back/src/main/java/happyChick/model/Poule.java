package happyChick.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import happyChick.tools.JsonNameParser;

@Entity

public class Poule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String prenom;
	protected double age;
	protected double bonheur;
	protected double ponte;
	protected double maternage;
	protected double predation;
	protected double maladie;

	protected boolean poussin;
	protected boolean femelle;

	@ManyToOne
	protected Temperament temperament;

	@ManyToOne
	protected Poulailler poulailler;

	protected int oeufsCouves;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Couvaison', 'Maternage', 'Liberte')")
	protected Etat etat;
	protected int saisonSansManger;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Faim', 'Meurtre', 'Predation', 'Maladie', 'Age')")
	protected CauseMort causeMort;
	
	@Autowired
	private Serieuse serieuse;
	
	@Autowired
	private Insouciante insouciante;
	
	@Autowired
	private MamanPoule mamanPoule;
	
	@Autowired
	private Psychopathe psychopathe;
	
	@Autowired
	private Pyromane pyromane;
	
	@Autowired
	private JsonNameParser jsonNamePaser;


	@Override
	public String toString() {
		return "Poule [id=" + id + ", prenom=" + prenom + ", age=" + age + ", bonheur=" + bonheur + ", poussin="
				+ poussin + ", femelle=" + femelle + ", oeufsCouves=" + oeufsCouves
				+ ", etat=" + etat + ", Nombre de saisons Sans Manger=" + saisonSansManger + "]";
	}

	public String toStringCouveuse() {
		return "Poule [id=" + id + ", prenom=" + prenom + ", age=" + age + ", bonheur=" + bonheur + ", temperament="+ this.getTemperament()+"]";
	}


	public Poule() {
		// TODO Auto-generated constructor stub
	
	}
	public Poule(String prenom, boolean femelle, Poulailler poulailler) {
		super();
		this.prenom = prenom;
		this.age = 0;
		this.poussin = true;
		this.femelle = femelle;
		this.poulailler = poulailler;
		this.etat=Etat.Liberte;
		saisonSansManger = 0;		
	}



	// fonctions
	public void step(boolean nourriture, int oeufsCouves) {
		System.out.println("OEUF COUVES : " + oeufsCouves);
		System.out.println(this);

		if (etat == Etat.Couvaison) {
			oeufsEclos(this.oeufsCouves);
			this.oeufsCouves=0;
			etat = etat.Maternage;
			System.out.println("la poule "+this.prenom+"est en train de materner.");
		} else if (etat == Etat.Liberte && oeufsCouves !=0) {
			this.oeufsCouves=oeufsCouves;
			etat = Etat.Couvaison;
			this.setPonte(0);
		} else if (etat == Etat.Maternage) {
			etat = etat.Liberte;
		}


		if (nourriture) {
			manger();
		} else {
			saisonSansManger ++;
		}

		grandir();
	}

	public void grandir() {
		this.age+=0.25;
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

	public boolean isCoq() {
		if ( (!femelle) && (!poussin) ) {
			return true;
		}
		return false;
	} 

	public void setCauseMort(CauseMort cause) {
		causeMort = cause;
	}

	public double getBonheur() {
		return bonheur;
	}

	public void setBonheur(double bonheur) {
		this.bonheur = bonheur;
	}

	public double getPonte() {
		return ponte;
	}

	public void setPonte(double ponte) {
		this.ponte = ponte;
	}

	public double getMaternage() {
		return maternage;
	}

	public void setMaternage(double maternage) {
		this.maternage = maternage;
	}


	public Temperament getTemperament() {
		return temperament;
	}

	public void setTemperament(Temperament temperament) {
		this.temperament = temperament;
	}

	public Poulailler getPoulailler() {
		return poulailler;
	}

	public void setPoulailler(Poulailler poulailler) {
		this.poulailler = poulailler;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getAge() {
		return age;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public double getPredation() {
		return predation;
	}

	public void setPredation(double predation) {
		this.predation = predation;
	}

	public double getMaladie() {
		return maladie;
	}

	public void setMaladie(double maladie) {
		this.maladie = maladie;
	}

	public boolean isPoussin() {
		return poussin;
	}

	public void setPoussin(boolean poussin) {
		this.poussin = poussin;
	}

	public boolean isFemelle() {
		return femelle;
	}

	public void setFemelle(boolean femelle) {
		this.femelle = femelle;
	}

	public int getOeufsCouves() {
		return oeufsCouves;
	}

	public void setOeufsCouves(int oeufsCouves) {
		this.oeufsCouves = oeufsCouves;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSaisonSansManger() {
		return saisonSansManger;
	}

	public void setSaisonSansManger(int saisonSansManger) {
		this.saisonSansManger = saisonSansManger;
	}

	public CauseMort getCauseMort() {
		return causeMort;
	}
}

//<K, v>
//<K, v>
//<Psychopathe, list>
//<Maman, list>
//<Coq, list>
//<Poussin, list>

//Liste avec toutes les poules
//Liste avec les sérieuses
