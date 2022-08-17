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


@Entity
public class Poulailler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected int securite;
	protected int taille;
	protected int nourriture;
	protected int oeufs;
	protected int annee;
	protected Saison saison;
	protected int nbMort=0;
	private String nom;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('SortieGenerale', 'Danse', 'Tricot', 'Escrime')")
	protected Activite activiteSaison;
	
	@OneToMany(mappedBy="poulailler")
	List<Poule> listePoules = new ArrayList();
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
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
	
	public void step(Activite a, int portionNourriture, Map<Poule, Integer> poulesCouveuses, boolean agrandir, boolean securiser) {
		echangeNourriture(portionNourriture);
		saison= Saison.saisonSuivante(saison);
		if (saison == Saison.Printemps) {
			annee++;
		}
		activiteSaison = a;
		if (agrandir) {agrandir();}
		if (securiser) {augmenterSecurite();}
		int cpt = 0;
		int nbOeufs = 0;
		
		List<Poule> listePoulestmp = getPoulesVivantes();
		Collections.shuffle(listePoulestmp);
		for (Poule p : listePoulestmp) {
			if (poulesCouveuses.containsKey(p)) {
				p.step(cpt<nourriture, poulesCouveuses.get(p));
			}
			else {
				p.step(cpt < nourriture, 0);
			}
			nbOeufs += p.oeufsPondus();
			cpt++;
		}
		this.oeufs+=nbOeufs;
		System.out.println("Il y a "+getNbPoulesVivantes()+" poules dans le poullailer. Il y a "+this.nourriture+" portions de nouriture. Le nombre d'eoufs pondus cette saison est de "+nbOeufs+". Il y a maintenant "+this.oeufs+" oeufs dans le poullailer.");
		
	}
	
	public void echangeNourriture(int portionNourriture) {
		this.oeufs-=portionNourriture*50;
		this.nourriture+=portionNourriture;
		System.out.println("Vous avez échangé "+(portionNourriture*50)+" oeufs contre "+portionNourriture+" portions de nourriture");
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
