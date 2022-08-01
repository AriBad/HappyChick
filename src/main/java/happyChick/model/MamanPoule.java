package happyChick.model;

import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("maman_poule")
public class MamanPoule extends Temperament {

	public MamanPoule() {
	}
	
	public void majBonheur(Poule poule) { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.
		double bonheur; // Ajouter le fait que la poule psychopathe augmente le bonheur des autres
		if (poule.getPoulailler().getActiviteSaison()==Activite.Escrime)  {
			bonheur= poule.getBonheur()+20 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule()-poule.getPoulailler().getSecurite()*5+poule.getPoulailler().getNbPsychopathe()*0.1;
		} else if (poule.getPoulailler().getActiviteSaison()==Activite.SortieGenerale)  {
			bonheur= poule.getBonheur()+10 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule()+poule.getPoulailler().getNbPsychopathe()*0.1;
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poule.getPoulailler().getNbMort()+0.1*poule.getPoulailler().getNbPoule()+poule.getPoulailler().getNbPsychopathe()*0.1;
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}
	
	public void majMaternage(Poule poule) {
		poule.maternage=0.8;
	}
	
	public void majMaladie(Poule poule) {
		
	poule.setMaladie((0.5*(poule.getPoulailler().getNbPoule()/poule.getPoulailler().getTaille())*(1-(poule.getBonheur()/100))));
	}
	
	public void majPredation(Poule poule) {
		poule.setPredation( 0.5 * (1/poule.getPoulailler().getSecurite()) );
	}
	
	public void majPonte(Poule poule) {
		Random r = new Random();
		int alea = r.nextInt(10) + 20;
		poule.setPonte((poule.getBonheur()/100)*(3/(1+poule.getAge()))*alea);
		
	}
}
