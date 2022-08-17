package happyChick.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("serieuse")
public class Serieuse extends Temperament {
	
	private static List<Poule> listSerieuses;
	
	
	public Serieuse() {

	}

	public void majBonheur(Poule poule) { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.
		double bonheur; // Ajouter le fait que la poule psychopathe augmente le bonheur des autres
		if (poule.getPoulailler().getActiviteSaison()==Activite.Tricot) {
			bonheur= poule.getBonheur()+20 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()-poule.getPoulailler().getSecurite()*5+poule.getPoulailler().getNbPsychopathe()*0.1;
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.SortieGenerale)  {
			bonheur= poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()+poule.getPoulailler().getNbPsychopathe()*0.1;
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoulesVivantes()+poule.getPoulailler().getNbPsychopathe()*0.1;
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}
	
	public void majPredation(Poule poule) {
		poule.setPredation( 0.3 * (1/poule.getPoulailler().getSecurite()) );
	}
}
