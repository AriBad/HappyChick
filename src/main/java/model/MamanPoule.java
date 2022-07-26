package model;

import java.util.Arrays;
import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("maman_poule")
public class MamanPoule extends Temperament {

	public MamanPoule(Poule poule, Poulailler poulailler) {
		super(poule, poulailler);
		// TODO Auto-generated constructor stub
	}
	
	public void majBonheur() { // Pour le moment, le poids pour la densité et le nombre de morts sont à 0.1. C'est à changer.
		double bonheur; // Ajouter le fait que la poule psychopathe augmente le bonheur des autres
		if (Arrays.toString(poule.getActivite().values())=="Escrime") {
			bonheur= poule.getBonheur()+20 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()-poulailler.getSecurite()*5+poulailler.getNbPsychopathe()*0.1;
		} else if (Arrays.toString(poule.getActivite().values())=="SortieGenerale") {
			bonheur= poule.getBonheur()+10 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		} else {
			bonheur= poule.getBonheur()+8 - 0.1*poulailler.getNbMort()+0.1*poulailler.getNbPoule()+poulailler.getNbPsychopathe()*0.1;
		}
		if (bonheur >100) {bonheur=100;}
		poule.setBonheur(bonheur);
	}
	
	public void majMaternage() {
		poule.maternage=0.8;
	}
	
	public void majMaladie() {
		
	poule.setMaladie((0.5*(poulailler.getNbPoule()/poulailler.getTaille())*(1-(poule.getBonheur()/100))));
	}
	
	public void majPredation() {
		poule.setPredation( 0.5 * (1/poulailler.getSecurite()) );
	}
	
	public void majPonte() {
		Random r = new Random();
		int alea = r.nextInt(10) + 20;
		poule.setPonte((poule.getBonheur()/100)*(3/(1+poule.getAge()))*alea);
		
	}
}
