package happyChick.model;

import java.util.Random;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Pyromane")
public class Pyromane extends Temperament {

	public Pyromane() {
		// TODO Auto-generated constructor stub
	}

	public void toutBruler(Poule poule) {
		int nbOeufs=poule.getPoulailler().getOeufs();
		boolean premier =true;
		for (int i = 2; i < nbOeufs; i++) {
			if (nbOeufs % i == 0)
				premier=false;
		}
		if (premier==true) {
			if (poule.getPoulailler().getSecurite()>1) {
				poule.getPoulailler().setSecurite(poule.getPoulailler().getSecurite()-1);
			}
		}
	}
}
