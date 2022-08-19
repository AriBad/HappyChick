package test;
import java.util.Scanner;

import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAOUser;
import happyChick.model.Activite;
import happyChick.model.Etat;
import happyChick.model.Poulailler;
import happyChick.model.Poule;
import happyChick.model.Saison;
import happyChick.model.User;
import happyChick.tools.JsonNameParser;

import java.util.Map;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;



public class Test {
	
	/*static User connected;
	static  IDAOUser daoU = Singleton.getInstance().getDaoUser();
   	
	public static int saisieInt(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextInt();
	}

	public static String saisieString(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextLine();
	}
	
	public static boolean saisieBoolean(String msg) {
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		return sc.nextBoolean();
	}
   
   	    
   	 public static void menuPrincipal() 
 	{
 		System.out.println("\nMenu principal");
 		System.out.println("1- Se connecter");
 		System.out.println("2- Inscription");
 		System.out.println("3- Stop");

 		int choix = saisieInt("Choisir un menu");


 		switch(choix) 
 		{
 		case 1 : connexion();break;
 		case 2 : inscription();break;
 		case 3 :Singleton.getInstance().getEmf().close(); System.exit(0);break;
 		}
 		menuPrincipal();
 	}
   		
   	public static void inscription() {
		System.out.println("inscription :");
		String login = saisieString("Saisir votre login :");
		String password = saisieString("Saisir votre password :");
		User u = new User(login,password);
		daoU.save(u);
		System.out.println("Nous vous avons bien inscrit ! ");

	}
   	public static void connexion() 
	{
		System.out.println("connexion :");
		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");
		connected = daoU.seConnecter(login, password);

	   if(connected instanceof User) {startGame();}
		else {System.out.println("Identifiants invalides");}
	}

   		public static void startGame() {
   		Poulailler poulailler;
   		//On teste la création de la partie, et le load
   		IDAOPoulailler daoPoulailler = Singleton.getInstance().getDaoPoulailler();
   		IDAOPoule daoPoule = Singleton.getInstance().getDaoPoule();
   		IDAOTemperament daoTemperament = Singleton.getInstance().getDaoTemperament();
   		
   		if ( saisieInt("1) Créer une Partie     2) Charger une partie") == 1 ) {
   			//on save les temperament, et on les manage
   			Singleton.getInstance().setInsouciante( (Insouciante) daoTemperament.save(Singleton.getInstance().getInsouciante()));
   			Singleton.getInstance().setSerieuse( (Serieuse) daoTemperament.save(Singleton.getInstance().getSerieuse()));
   			Singleton.getInstance().setPsychopathe( (Psychopathe) daoTemperament.save(Singleton.getInstance().getPsychopathe()));
   			Singleton.getInstance().setMamanPoule( (MamanPoule) daoTemperament.save(Singleton.getInstance().getMamanPoule()));
   			Singleton.getInstance().setPyromane( (Pyromane) daoTemperament.save(Singleton.getInstance().getPyromane()));
   			
   			poulailler = new Poulailler(1,100, 10, 5, 2022, Saison.Printemps);
   			poulailler= daoPoulailler.save(poulailler);
   			
   		    Poule p1 = new Poule ("Marlene",true,poulailler);
   	        Poule p2 = new Poule ("Clara",true,poulailler);
   	        Poule p3 = new Poule ("Jane",true,poulailler);
   	        Poule p4 = new Poule ("Luna",true,poulailler);
   	        Poule p5 = new Poule ("Marc",false,poulailler);
   	        Collections.addAll(poulailler.getListePoules(),p1,p2,p3,p4,p5);
   			
   			for (Poule p : poulailler.getListePoules()) {
   				daoPoule.save(p);
   			}
   			poulailler = daoPoulailler.findByIdWithPoules(poulailler.getId());
   		}
   		else {
   			Singleton.getInstance().setInsouciante((Insouciante) daoTemperament.findById(1));
   			Singleton.getInstance().setSerieuse((Serieuse) daoTemperament.findById(2));
   			Singleton.getInstance().setPsychopathe((Psychopathe) daoTemperament.findById(3));
   			Singleton.getInstance().setMamanPoule((MamanPoule) daoTemperament.findById(4));
   			Singleton.getInstance().setPyromane((Pyromane) daoTemperament.findById(5));
   			
   			poulailler = daoPoulailler.findByIdWithPoules(1);
   		}

		HashMap<Poule, Integer> map = new HashMap();
		for (int i = 0 ; i < 15 ; i++) {
			System.out.println("Etat du poulailler : "+poulailler);
			System.out.println("\nIl y a les poules suivantes dans le poulailler"+poulailler.getListePoules()+"\n");
         	int nbOeufs = poulailler.getOeufs();
         	boolean ajouterPoule = true;
         	do {
               if ( nbOeufs > 0 ) {
                  int couveuse;
                  int nbOeufsCouves = 0;
                  System.out.println("Souhaitez-vous ajouter une poule à couver ? Poules disponibles : ");
                  for (Poule p : poulailler.getPoulesVivantes()) {
                     if ( p.isFemelle() && !p.isPoussin() && p.getEtat() == Etat.Liberte) {
                        System.out.println(p.getId() + " - " + p.toStringCouveuse());
                     }
                  }
                  System.out.println(0 + " - Ne pas ajouter de poules");
                  couveuse = saisieInt("Choisir un nombre");
                  if (couveuse != 0) {
                     nbOeufsCouves = saisieInt("Combien d'oeufs à couver ? (entre 2 et 8)");
                     map.put(poulailler.getPouleById(couveuse), nbOeufsCouves); 
                     nbOeufs -= nbOeufsCouves;
                  } else {
                     ajouterPoule = false;
                  }
               } else {
                  ajouterPoule = false;
               }
            } while (ajouterPoule);
            
         
         
         	int nourriture = saisieInt("Vous avez " + nbOeufs + " oeufs disponibles, combien voulez-vous acheter de nourriture ? (50 oeufs = une portion de nourriture)");
         	if (nourriture > nbOeufs /50) {
               nourriture = nbOeufs /50;
               System.out.println("Vous n'avez pas assez d'oeufs ! ");
            }
            System.out.println(nourriture + "portions de nourriture achetées");
            nbOeufs = nbOeufs - (50* nourriture);
            Activite activite = Activite.valueOf(saisieString("Saisissez l'activité sur cette saison parmi : " + Arrays.toString(Activite.values())) );
            
            boolean amelioSecu = saisieBoolean("Voulez-vous améliorer la sécurité ? Cela coute 20 oeufs (true/false)");
            if  (amelioSecu && nbOeufs >= 20) {
            	System.out.println("Poulailler va etre securiser avec succès !");
            	nbOeufs -= poulailler.getPrixAugmenterSecurite();
            }
            else {
               System.out.println("pas d'agrandissement");
               amelioSecu=false;
            }
            
            boolean agrandir = saisieBoolean("Voulez-vous agrandir le poulailler ? Cela coute 20 oeufs (true/false)");
            if  (amelioSecu && nbOeufs >= 20) {
               System.out.println("Poulailler va etre agrandie avec succès !");
               nbOeufs -= poulailler.getPrixAgrandir();
            }
            else {
               System.out.println("taille non agrandie.");
               agrandir= false;
            }
            poulailler.step(activite, nourriture, map, agrandir, amelioSecu);
            daoPoulailler.save(poulailler);
            for (Poule p : poulailler.getListePoules()) {
            	daoPoule.save(p);
            }
            poulailler = daoPoulailler.findByIdWithPoules(poulailler.getId());
            
            map.clear();
   
       	}
		
		
	}
   
	public static void main(String[] args) {
   	
		menuPrincipal();
	}*/
}

  /* date = 0000-00-00
   saison = 1 = Automne / Printemps
   Poulailler = (taille = 100m²)
   Securite = 0
   joueur (nom : X)
   
   Le joueur commence avec 5 poussins = 5 poules avec le même age
   
   
   Temperament aléatoire mais pour cette exemple on va prendre 5 differents temp
   poule 1(nom : Marlene, temparement : MamanPoule, age= 0.25, bonheur = 50, Ponte par saison = X, Taux de maladie = X, Taux de predation = X, Taux de maternage = X)
   poule 2(nom: Clara, temparement : Psychopathe, age= 0.25, bonheur = 50, Ponte par saison = X, Taux de maladie = X, Taux de predation = X, Taux de maternage = X)
   poule 3(nom : Jane temparement : Serieuse, age= 0.25, bonheur = 50, Ponte par saison = X, Taux de maladie = X, Taux de predation = X, Taux de maternage = X)
   poule 4(nom : Luna, temparement : Insouciante, age= 0.25, bonheur = 50, , Ponte par saison = X, Taux de maladie = X, Taux de predation = X, Taux de maternage = X)
   poule 5(nom : Marc ,boolean male : coq, age= 0.25, Taux de maladie = X, Taux de predation = X)
   
   Vous avez obtenu cette saison X oeufs
   Vous avez donner naissance à X poussins
   X poules sont mortes (nom, temperament...)
   Vous avez X poules malades (nom, temperament...)
   
   
   Que voulez vous faire avec votre gain :
   1. Achetter Nourriture (Coût 2 oeufs= 1 portion blé, grains...) [(1 portion par poule par saison) = 5portions]
   2. Augmenter le niveau de sécurité, coût = X
   3. Achetter du terrain, Coût pour 1m²
   
   Le joueur doit choisir :
  1. les poules qui vont couver => 
  2. les activités pour la saison suivante =>
  3. les poules qui vont partir en voyage =>*/
  
   
   
   
   
