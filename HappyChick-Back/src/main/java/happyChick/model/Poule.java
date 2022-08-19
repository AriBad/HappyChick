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

	@Enumerated
	protected Temperament2 temperament;

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
