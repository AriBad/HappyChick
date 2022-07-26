package model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@DiscriminatorValue("serieuse")
public class Serieuse extends Temperament {
	
	private static List<Poule> listSerieuses;
	
	
	public Serieuse(Poule poule, Poulailler poulailler) {
		super(poule, poulailler);
		// TODO Auto-generated constructor stub
		
	}
	public Serieuse() {
		// TODO Auto-generated constructor stub
	}
	
	public void majBonheur() { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.
		double bonheur; // Ajouter le fait que la poule psychopathe augmente le bonheur des autres
		if (Arrays.toString(poule.getActivite().values())=="Tricot") {
			bonheur= poule.getBonheur()+20 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()-poulailler.getSecurite()*5+poulailler.getNbPsychopathe()*0.1;
		} else if (Arrays.toString(poule.getActivite().values())=="SortieGenerale") {
			bonheur= poule.getBonheur()+10 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}
	
	public void majPredation() {
		poule.setPredation( 0.3 * (1/poulailler.getSecurite()) );
	}
}
