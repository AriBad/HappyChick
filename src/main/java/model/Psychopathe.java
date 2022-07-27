package model;

import java.util.Arrays;
import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@DiscriminatorValue("psychopathe")
public class Psychopathe extends Temperament {

	public Psychopathe() {
		// TODO Auto-generated constructor stub
	}
	public Psychopathe(Poule poule, Poulailler poulailler) {
		super(poule, poulailler);
		// TODO Auto-generated constructor stub
	}


	public void majBonheur() { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.  
		double bonheur;
		if ((poulailler.getActiviteSaison()==Activite.Danse) ) {
			bonheur= poule.getBonheur()+20 + 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule();// -0.1*nb de saison sans mort.e.s --> A ajouter après !
		} else if (poulailler.getActiviteSaison()==Activite.SortieGenerale)  {
			bonheur= poule.getBonheur()+10 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule();
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule();
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}

	public void majPredation() {
		poule.setPredation( 0.05 * (1/poulailler.getSecurite()) );
	}

	public void tuerPoule() {

		Random r1 = new Random();
		if ( r1.nextDouble() > (poule.getBonheur()/100)) {

			Random r2 = new Random();
			int alea = r2.nextInt(poulailler.getNbPoule());

			poulailler.indiquerMort(poulailler.getListePoules().get(alea), CauseMort.Meurtre);
			
			System.out.println("La poule psychopath à tuer la poule "+poulailler.getListePoules().get(alea));
		}
	}
}
