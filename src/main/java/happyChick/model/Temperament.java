package happyChick.model;

import java.util.List;
import java.util.Random;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte", columnDefinition = "ENUM('insouciante','maman_poule', 'psychopathe', 'serieuse')")
public abstract class Temperament {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "temperament")
	protected List<Poule> poules;

	
	
	
	public Temperament(){
		
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
		poule.setBonheur(50);
	}
	
	public void majPredation(Poule poule) {
		poule.setPredation( 0.5 * (1/poule.getPoulailler().getSecurite()) );
	}

	public void majBonheur(Poule poule) { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.  
		double bonheur;
		if (poule.getPoulailler().getActiviteSaison()==Activite.SortieGenerale)  {
			bonheur= poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule();
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule();
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}
	
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
