package happyChick.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte", columnDefinition = "ENUM('insouciante','maman_poule', 'psychopathe', 'serieuse', 'pyromane')")
public abstract class Temperament {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy = "temperament", fetch = FetchType.EAGER)
	protected List<Poule> poules = new ArrayList();

	
	
	
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
			bonheur= poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes();
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes();
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
		poule.setMaladie((0.5*(poule.getPoulailler().getNbPoulesVivantes()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100)))*5);
		}
		else {
		poule.setMaladie((0.5*(poule.getPoulailler().getNbPoulesVivantes()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100))));
		}
	}	
}
