package model;

import java.security.PermissionCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Temperament {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected Poule poule;
	protected Poulailler poulailler;
	
	public Temperament() {
		// TODO Auto-generated constructor stub
	}
	
	public Temperament(Poule poule, Poulailler poulailler) {
		this.poule= poule;
		this.poulailler=poulailler;
		poule.setBonheur(50);
		
	}

	public void majVariables() {
		majBonheur();
		majMaternage();
		majPredation();
		majMaladie();
		majPonte();
		
	}
	

	public void genererVariablesBase() {
		this.majMaternage();
		this.majPonte();
	}
	
	protected abstract void majPredation();

	protected abstract void majBonheur();
	
	public void majPonte() {
		if (this.poule.getEtat()==Etat.Liberte) {
			Random r = new Random();
			int alea = r.nextInt(10) + 20;
			poule.setPonte((poule.getBonheur()/100)*(3/(1+poule.getAge()))*alea);
		} else {
			this.poule.setPonte(0);
		}
		
	}
	
	public void majMaternage() {
		poule.maternage=0.6;
	}
	
	
	public void majMaladie (){
		
		if (poule.getEtat() == Etat.Couvaison ){
		poule.setMaladie((0.5*(poulailler.getNbPoule()/poulailler.getTaille())*(1-(poule.getBonheur()/100)))*5);
		}
		else {
		poule.setMaladie((0.5*(poulailler.getNbPoule()/poulailler.getTaille())*(1-(poule.getBonheur()/100))));
		}
	}	
	// Il faut ajouter un booleen pour dire si la poule couve ou si la poule materne (elle ne peux pas pondre mais elle ne couve pas (est-ce quon fait une difference ?))
	// avec ce boolen on pourra faire un if pour savoir si la poule couve -- est-ce qu'on met le boolean dans temperament ou dans poule ? 
}
