package happyChick.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("insouciante")
public class Insouciante extends Temperament {

	public Insouciante() {
		// TODO Auto-generated constructor stub
	}


	public void majBonheur(Poule poule) { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.
		double bonheur; // Ajouter le fait que la poule psychopathe augmente le bonheur des autres
		if (poule.getPoulailler().getActiviteSaison()==Activite.Danse)  {
			bonheur= poule.getBonheur()+20 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule()+poule.getPoulailler().getNbPsychopathe()*0.1;
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.SortieGenerale)  {
			bonheur= poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule()+poule.getPoulailler().getNbPsychopathe()*0.1;
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule()+poule.getPoulailler().getNbPsychopathe()*0.1;
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}

	public void majPredation(Poule poule) {
		poule.setPredation( 0.75 * (1/poule.getPoulailler().getSecurite()) );
	}

}
