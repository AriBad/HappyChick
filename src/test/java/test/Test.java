package test;
import java.util.Scanner;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

import model.Activite;
import model.Poulailler;
import model.Poule;
import model.Saison;

public class Test {
   
   static Poulailler poulailler;
   
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
   
   	public static void main(String[] args) {
		System.out.println("Bienvenue dans votre poulailler dont vous allez être poule en chef. \nVous avez découvert dans un fossé 5 oeufs qui viennent d'éclore, et qui ont donné naissance à 5 magnifiques poussins. \nVous avez également reçu un un don anonyme de 10 portions de nourriture pour lancer votre activité");
		poulailler = new Poulailler(1,100, 10, 5, 2022, Saison.Printemps);

		
		HashMap<Poule, Integer> map = new HashMap();
		System.out.println("Vous ne pouvez encore rien faire en cette saison");
		poulailler.step(null, 5, map, false, false);
		
		System.out.println("\n--------------------------------- \n");
		System.out.println("Une saison s'est écoulée, nous sommes maintenant en " + poulailler.getSaison() + " " + poulailler.getAnnee());
		map.put(poulailler.getListePoules().get(0), 5);
		map.put(poulailler.getListePoules().get(1), 5);
		poulailler.step(Activite.Danse, 7, map, false, false);
		System.out.println("\n--------------------------------- \n");
		System.out.println("Une saison s'est écoulée, nous sommes maintenant en " + poulailler.getSaison() + " " + poulailler.getAnnee());
		map.clear();
		poulailler.step(Activite.Escrime, 7, map, false, false);
		System.out.println("\n--------------------------------- \n");
		System.out.println("Une saison s'est écoulée, nous sommes maintenant en " + poulailler.getSaison() + " " + poulailler.getAnnee());
		
		poulailler.step(Activite.SortieGenerale, 7, map, false, false);
		System.out.println("\n--------------------------------- \n");
		System.out.println("Une saison s'est écoulée, nous sommes maintenant en " + poulailler.getSaison() + " " + poulailler.getAnnee());
		
		poulailler.step(Activite.Tricot, 5, map, false, false);

	} //Erreur maternage à résoudre, les oeufs sont couvés mais n'éclosent pas
	
}
		
		
		
		
		
		
		/*		for (int i = 0 ; i < 15 ; i++) {
         	int nbOeufs = poulailler.getOeufs();
         	int nbCouveuses = saisieInt("Vous avez " + poulailler.getNbPoule() + " poules ainsi que " + nbOeufs + " oeufs. Combien de poules doivent couver un oeuf pour cette saison ?");
         	if (nbCouveuses > poulailler.getNbPoule()) {
               nbCouveuses = poulailler.getNbPoule();
               System.out.println("Vous n'avez pas assez d'oeufs ! ");
            }
            if (nbCouveuses > poulailler.getOeufs()) {
               nbCouveuses = poulailler.getOeufs();
               System.out.println("Vous n'avez pas assez d'oeufs ! ");
            }
            System.out.println(nbCouveuses + " couveuses pour cette saison;");
            
           
//            nbOeufs = nbOeufs - nbCouveuses;
         
         	int nourriture = saisieInt("Vous avez " + nbOeufs + " oeufs disponibles, combien voulez-vous acheter de nourriture ? (2 oeufs = une portion de nourriture)");
         	if (nourriture > nbOeufs /2) {
               nourriture = nbOeufs /2;
               System.out.println("Vous n'avez pas assez d'oeufs ! ");
            }
            System.out.println(nourriture + "portions de nourriture achetées");
            nbOeufs = nbOeufs - (2* nourriture);
            Activite activite = Activite.valueOf(saisieString("Saisissez l'activité sur cette saison parmi : " + Activite.values()));
            
            boolean amelioSecu = saisieBoolean("Voulez-vous améliorer la sécurité ? Cela coute 20 oeufs (true/false)");
            if  (amelioSecu && nbOeufs >= 20) {
               System.out.println("Sécurité améliorée !");
               nbOeufs -= 20;
            }
            else {
               System.out.println("Sécurité non améliorée.");
            }
            
            boolean agrandir = saisieBoolean("Voulez-vous agrandir le poulailler ? Cela coute 20 oeufs (true/false)");
            if  (amelioSecu && nbOeufs >= 20) {
               System.out.println("Poulailler agrandi avec succès !");
               nbOeufs -= 20;
            }
            else {
               System.out.println("Sécurité non agrandie.");
            }
            
       	}
		
		
	}
   
}

   date = 0000-00-00
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
  3. les poules qui vont partir en voyage =>
  
   
   
   
   
*/