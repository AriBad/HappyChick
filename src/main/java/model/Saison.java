package model;

import javax.persistence.Embeddable;

@Embeddable
public enum Saison {
	Automne, Hiver, Printemps, Eté;
	
	public static Saison saisonSuivante(Saison s) {
		switch (s) {
			case Automne :
			return Hiver;
			case Hiver:
			return Printemps;
			case Printemps:
			return Eté;
			default:
			return Automne;
			
		}
	}
}
