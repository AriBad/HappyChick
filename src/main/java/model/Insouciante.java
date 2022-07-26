package model;

import java.util.Arrays;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("insouciante")
public class Insouciante extends Temperament {

public Insouciante() {
	// TODO Auto-generated constructor stub
}
	public Insouciante(Poule poule, Poulailler poulailler) {
		super(poule, poulailler);
		// TODO Auto-generated constructor stub
	}
	public void majVariables() {
		//TODO
	}
	public void genererStats() {
		//TODO
	}

	public void majBonheur() { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.
		double bonheur; // Ajouter le fait que la poule psychopathe augmente le bonheur des autres
		if (Arrays.toString(poule.getActivite().values())=="Danse") {
			bonheur= poule.getBonheur()+20 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		} else if (Arrays.toString(poule.getActivite().values())=="SortieGenerale") {
			bonheur= poule.getBonheur()+10 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}

	public void majMaternage() {

	}

	public void majPredation() {
		poule.setPredation( 0.75 * (1/poulailler.getSecurite()) );
	}

}
