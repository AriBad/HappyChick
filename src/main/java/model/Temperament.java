package model;

import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public abstract class Temperament {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "temperament")
	protected List<Poule> poules;

	
	
	
	public Temperament(){

		//gerer coté poule.adulte
		//poule.setBonheur(50);
		
	}

	public void majVariables(Poule poule) {
		majBonheur(poule);
		majMaternage(poule);
		majPredation(poule);
		majMaladie(poule);
		majPonte(poule);
		
	}
	

	public void genererVariablesBase(Poule poule) {
		this.majMaternage(poule);
		this.majPonte(poule);
	}
	
	protected abstract void majPredation(Poule poule);

	protected abstract void majBonheur(Poule poule);
	
	public void majPonte(Poule poule) {
		if (poule.getEtat()==Etat.Liberte) {
			Random r = new Random();
			int alea = r.nextInt(10) + 20;
			poule.setPonte((poule.getBonheur()/100)*(3/(1+poule.getAge()))*alea);
		} else {
			poule.setPonte(0);
		}
		
	}
	
	public List<Poule> getPoules() {
		return poules;
	}

	public void setPoules(List<Poule> poules) {
		this.poules = poules;
	}

	public void majMaternage(Poule poule) {
		poule.maternage=0.6;
	}
	
	
	public void majMaladie (Poule poule){
		
		if (poule.getEtat() == Etat.Couvaison ){
		poule.setMaladie((0.5*(poule.getPoulailler().getNbPoule()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100)))*5);
		}
		else {
		poule.setMaladie((0.5*(poule.getPoulailler().getNbPoule()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100))));
		}
	}	
	// Il faut ajouter un booleen pour dire si la poule couve ou si la poule materne (elle ne peux pas pondre mais elle ne couve pas (est-ce quon fait une difference ?))
	// avec ce boolen on pourra faire un if pour savoir si la poule couve -- est-ce qu'on met le boolean dans temperament ou dans poule ? 
}
