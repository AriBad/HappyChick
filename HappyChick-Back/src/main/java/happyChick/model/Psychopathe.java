package happyChick.model;

import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("psychopathe")
public class Psychopathe extends Temperament {

	public Psychopathe() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void majVariables(Poule p) {
		super.majVariables(p);
		tuerPoule(p);
	}


	public void majBonheur(Poule poule) { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.  
		double bonheur;
		if ((poule.getPoulailler().getActiviteSaison()==Activite.Danse) ) {
			bonheur= poule.getBonheur()+20 + 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes();// -0.1*nb de saison sans mort.e.s --> A ajouter après !
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.SortieGenerale)  {
			bonheur= poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes();
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes();
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}

	public void majPredation(Poule poule) {
		poule.setPredation( 0.05 * (1/poule.getPoulailler().getSecurite()) );
	}

	public void tuerPoule(Poule poule) {

		Random r1 = new Random();
		if ( r1.nextDouble() > (poule.getBonheur()/100)) {

			Random r2 = new Random();
			int alea = r2.nextInt(poule.getPoulailler().getNbPoulesVivantes());

			poule.getPoulailler().getPoulesVivantes().get(alea).setCauseMort(CauseMort.Meurtre);
			
			System.out.println("La poule psychopathe à tuer la poule "+poule.getPoulailler().getPoulesVivantes().get(alea));
		}
	}
}
