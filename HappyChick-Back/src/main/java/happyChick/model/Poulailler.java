package happyChick.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import happyChick.model.jsonview.JsonViews;


@Entity
public class Poulailler {
	@JsonView(JsonViews.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@JsonView(JsonViews.Base.class)
	protected int securite;
	
	@JsonView(JsonViews.Base.class)
	protected int taille;
	
	@JsonView(JsonViews.Base.class)
	protected int nourriture;
	
	@JsonView(JsonViews.Base.class)
	protected int oeufs;
	
	@JsonView(JsonViews.Base.class)
	protected int annee;
	
	@JsonView(JsonViews.Base.class)
	protected Saison saison;
	
	@JsonView(JsonViews.Base.class)
	protected int nbMort=0;
	
	@JsonView(JsonViews.Base.class)
	private String nom;
	
	@JsonView(JsonViews.Base.class)
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('SortieGenerale', 'Danse', 'Tricot', 'Escrime')")
	protected Activite activiteSaison;
	
	@JsonView(JsonViews.PoulaillerWithPoules.class)
	@OneToMany(mappedBy="poulailler")
	List<Poule> listePoules = new ArrayList();
	
	@ManyToOne
	@JoinColumn(name="id_user")
	transient private User user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Activite getActiviteSaison() {
		return activiteSaison;
	}
	public void setActiviteSaison(Activite activiteSaison) {
		this.activiteSaison = activiteSaison;
	}
	
	public Poulailler() {
		// TODO Auto-generated constructor stub
	}
	public Poulailler(int securite, int taille, int nourriture, int oeufs, int annee, Saison saison) {
		this.securite = securite;
		this.taille = taille;
		this.nourriture = nourriture;
		this.oeufs = oeufs;
		this.annee = annee;
		this.saison = saison;
		nom="TODO";
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void indiquerNaissance(Poule p) {
		listePoules.add(p);
		//System.out.println(p.getPrenom() + " est née !");
	}

	public int getSecurite() {
		return securite;
	}

	public void setSecurite(int securite) {
		this.securite = securite;
	}
	
	public Saison getSaison() {
		return saison;
	}
	
	public void setSaison(Saison saison) {
		this.saison = saison;
	}
	
	public Poule getPouleById(int id) {
		//TODO
		for(Poule p : listePoules) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Poule> getPoulesVivantes() {
		ArrayList<Poule> vivantes = new ArrayList();
		for (Poule p : listePoules) {
			if (p.getCauseMort() == null) {
				vivantes.add(p);
			}
		}
		return vivantes;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getNourriture() {
		return nourriture;
	}

	public void setNourriture(int nourriture) {
		this.nourriture = nourriture;
	}

	public int getOeufs() {
		return oeufs;
	}

	public void setOeufs(int oeufs) {
		this.oeufs = oeufs;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public List<Poule> getListePoules() {
		return listePoules;
	}



	public void setListePoules(List<Poule> listePoules) {
		this.listePoules = listePoules;
	}



	public int getNbMort() {
		return nbMort;
	}

	public void setNbMort(int nbMort) {
		this.nbMort = nbMort;
	}
	
	public int getNbPoulesVivantes() {
		return getPoulesVivantes().size();
	}
	
	public int getPrixAgrandir() {
		return 10;
	}
	
	public void agrandir() {
		taille += 10;
		this.oeufs-=this.getPrixAgrandir();
		System.out.println("La taille du poulailler est "+taille+" vous avez maintenant"+oeufs+"oeufs");
	}
	
	public int getPrixAugmenterSecurite() {
		return 10;
	}
	
	public void augmenterSecurite() {
		securite += 1;
		oeufs -= getPrixAugmenterSecurite();
		System.out.println("La securite du poulailler est monté au niveau "+securite+" vous avez maintenant"+oeufs+"oeufs");
	}
	
	public int getNbPsychopathe() {
		int cpt = 0;
		for (Poule p : listePoules) {
			if (p.getTemperament() instanceof Psychopathe){
				cpt ++;
			}
		}
		return cpt;
	}

	@Override
	public String toString() {
		return "Poulailler [securite=" + securite + ", taille=" + taille + ", nourriture=" + nourriture + ", oeufs="
				+ oeufs + ", annee=" + annee + ", saison=" + saison + ", nbMort=" + nbMort + ", activiteSaison="
				+ activiteSaison + ", listePoules=" + listePoules + "]";
	}
	
//	S'il y a plus ou autant de portion de nouriture que de poule --> pas de problème, sinon x (nb de poule - nb de portion) 
	//   poules ne pondent pas d'oeufs (si le probleme persiste une autre saison les poules meurent)

}
