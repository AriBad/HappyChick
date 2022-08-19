package happyChick.model;

import java.util.List;

public class ChoixUsers {
	int nourriture;
	boolean securite;
	boolean taille;
	Activite activite;
	List<PouleCouveuse> listeCouveuses;
	public ChoixUsers() {

	}
	public int getNourriture() {
		return nourriture;
	}
	public void setNourriture(int nourriture) {
		this.nourriture = nourriture;
	}
	public boolean isSecurite() {
		return securite;
	}
	public void setSecurite(boolean securite) {
		this.securite = securite;
	}
	public boolean isTaille() {
		return taille;
	}
	public void setTaille(boolean taille) {
		this.taille = taille;
	}
	public Activite getActivite() {
		return activite;
	}
	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	public List<PouleCouveuse> getListeCouveuses() {
		return listeCouveuses;
	}
	public void setListeCouveuses(List<PouleCouveuse> listeCouveuses) {
		this.listeCouveuses = listeCouveuses;
	}
	
	
}
