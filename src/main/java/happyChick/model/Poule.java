package happyChick.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import happyChick.context.Singleton;

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
	
	transient private static List<String> prenomsFilles = new ArrayList();
	transient private static List<String> prenomsGarcons = new ArrayList();
	

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
	Collections.addAll(prenomsFilles, "Adélaïde", "Perle", "Lolita", "Marie-Thérèse", "Clémence", "Prune");
	Collections.addAll(prenomsGarcons,"Edouard", "Abricot", "Persée", "Jordan", "Thésée", "Ulysse");
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
		Collections.addAll(prenomsFilles, "Adélaïde", "Perle", "Lolita", "Marie-Thérèse", "Clémence", "Prune");
		Collections.addAll(prenomsGarcons,"Edouard", "Abricot", "Persée", "Jordan", "Thésée", "Ulysse");
		
	}



	// fonctions
	public void step(boolean nourriture, int oeufsCouves) {
		System.out.println("OEUF COUVES : " + oeufsCouves);
		System.out.println(this);
		
		  if (etat == Etat.Couvaison) { //c'est quoi le souci ?  en gros, si elle materne et qu'elle vient juste d'être libre, on donnera à l'user l'option de la faire couver. Mais du coup elle passera pas par le else if. Elle recevra des oeufs à couver, mais elle couvera pas.
			oeufsEclos(this.oeufsCouves);
			this.oeufsCouves=0;
			etat = etat.Maternage;
			System.out.println("la poule "+this.prenom+"est en train de materner.");
		} else if (etat == Etat.Liberte && oeufsCouves !=0) { //ici c'est un else if, je pense on peut juste le passer en if, mais faut réfléchir si ça pète pas autre chose x)
			//System.out.println("\n\nON AFFICHE LES OEUFS COUVES");
			//System.out.println(oeufsCouves);
			this.oeufsCouves=oeufsCouves;
			//System.out.println(this.oeufsCouves);
			etat = Etat.Couvaison;
			this.setPonte(0);
			//System.out.println("la poule "+this.prenom+"est en train de couver.");
		} else if (etat == Etat.Maternage) {
			etat = etat.Liberte;
		} // c'est mieux comme ça je pense ? nop x) en gros là la poule qui maternait à la saison d'avant pourra pas couver' --> oui mais tant pis, on dit qu'il lui faut une pause d'une saison
		  
		
		
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
			//System.out.println("La poule "+this.prenom+"(id="+this.id+") est passée à l'âge adulte.");
		}
		if (femelle && !poussin) {
			temperament.majVariables(this);
		}
	}

	public void manger() {
		poulailler.setNourriture(poulailler.getNourriture()-1);
		this.saisonSansManger=0;
		System.out.println("La poule "+this.prenom+"(id="+this.id+") a mangé.");
	}
	
	public void mort() {
		Random r = new Random();
		if ( r.nextDouble() < maladie ) {
			poulailler.indiquerMort(this, CauseMort.Maladie);
			System.out.println("La poule "+this.prenom+"(id="+this.id+") est morte de maladie.");
		} else if (r.nextDouble() < predation) {
			if (r.nextDouble() < (poulailler.getNbPsychopathe()*0.05)){
				poulailler.indiquerMort(this, CauseMort.Predation);
				System.out.println("La poule "+this.prenom+"(id="+this.id+") est morte à cause d'un prédateur.");
			}
		} else if (r.nextDouble() < 0.0025 * (age * age) && age > 10) {
			poulailler.indiquerMort(this, causeMort.Age);
			System.out.println("La poule "+this.prenom+"(id="+this.id+") est morte de Vieillesse.");
		} else if (r.nextDouble() < saisonSansManger * 0.334) {
			poulailler.indiquerMort(this, CauseMort.Faim);
		}
	}

	public void passageAdulte() {
		this.poussin=false;
		if (this.femelle) {
			Random r = new Random();
			int alea = r.nextInt(10); // FAIRE UNE MAJ DES STATS DES POULES --> MAJ MATERNAGE, MAJ PONTE etc...
			if (alea==0 || alea==1 || alea==2) {
				this.temperament = Singleton.getInstance().getSerieuse();
				System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue serieuse.");
			} else if (alea==3 || alea==4 || alea==5) {
				this.temperament = Singleton.getInstance().getMamanPoule();
				System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Maman Poule.");
			} else if (alea==6 || alea==7 || alea==8) {
				this.temperament = Singleton.getInstance().getInsouciante();
				System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Insouciante.");
			} else if (alea==9) {
				this.temperament = Singleton.getInstance().getPsychopathe();
				System.out.println("La poule "+this.prenom+"(id="+this.id+") est devenue Psychopathe.");
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
		//System.out.println("\n\nON EST DANS LE CALCUL DES OEUFS ECLOS !!!");
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
			Collections.shuffle(prenomsGarcons);
			p = new Poule(prenomsGarcons.get(1), false, this.poulailler);
			
		} else {
			Collections.shuffle(prenomsFilles);
			p = new Poule(prenomsFilles.get(1), true, this.poulailler);
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
