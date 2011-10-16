import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


public class Partie implements Enums_Interfaces.Hauteur, Enums_Interfaces.Symbole{
	
	private static int NB_CARTES_PAR_JOUEUR = 8;

	private ArrayList<Joueur> mesJoueurs;
	private Pioche maPioche;
	private Pile maPile;
	
	private int nbJoueurs;
	private int nbHumains;
	private int numJoueurCourant = 0; //Le numéro du joueur qui doit jouer
	//Le déroulement est croissant (joueur0 puis joueur1 puis ...) au début mais peut s'inverser.
	private boolean sensCroissant = true; 
	
	public static Scanner in = new Scanner(System.in);
	
	private int nbAs;
	
	public Partie(int nbJ, int nbH){
		this.nbJoueurs = nbJ;
		this.nbHumains = nbH;
		this.nbAs = 0;
		this.mesJoueurs = new ArrayList<Joueur>();
		
		// Création de la pioche
		this.maPioche = new Pioche();
		
		// Création de la pile
		this.maPile = new Pile();
		
		//Création des joueurs 
		int i;
		for(i=0; i<this.nbJoueurs; i++){
			if(i<this.nbHumains)
				mesJoueurs.add(new Humain(i));
			else
				mesJoueurs.add(new Virtuel(i));
		}
		Collections.shuffle(mesJoueurs);
		
		//Distributions des cartes aux joueurs
		distribuer();

		//ICI on va retourner la bergère.
		analyserPassage(retournerBergere());
		
	}
	
	public void gestionDuJeu(){
		boolean enMarche = true;
		Joueur jCourant;
		while(enMarche){
			System.out.println("\n");
			Messages.Message_Info = 0;
			System.out.println("Haut de pile : "+traduireCarte(maPile.getHautDePile()));
			System.out.println("Le joueur "+numJoueurCourant+" doit jouer.");
			jCourant = mesJoueurs.get(numJoueurCourant);
			
			jCourant.afficherJeu();
			analyserPassage(jCourant.jouer(maPile.getHautDePile(), this.nbAs));
			jCourant.setEtat(true);
			numJoueurCourant = (sensCroissant) ? (numJoueurCourant+1)%nbJoueurs : (numJoueurCourant+1)%nbJoueurs;
			System.out.println(Messages.MESSAGE[Messages.Message_Info]);
		}
	}
	
	public void analyserPassage(Carte c){
		//Cette fonction reçoit la carte qui vient d'être jouée (c)
		//Elle permet d'effectuer tous les mechanismes entre le joueurN et le joueurN+1
		//(piocher une carte chez un autre joueur, dans la pioche, compter les As, ...)
		
		int i;
		Joueur jCourant = mesJoueurs.get(numJoueurCourant);
		Joueur jSuivant = mesJoueurs.get((sensCroissant) ? (numJoueurCourant+1)%nbJoueurs : (numJoueurCourant+1)%nbJoueurs);
		
		if (c.getH() != -1){ //Si le joueur courant vient de poser une carte
			maPile.empiler(jCourant.donnerCarte(c));
			
			switch(c.getH()){
			case JOKER_H:
				for(i=0; i<5; i++)
					jSuivant.recevoirCarte(maPioche.piocherCarte());
				jSuivant.setEtat(false);
				break;	
			case 10: sensCroissant = !sensCroissant; break;
			case 7:	jSuivant.recevoirCarte(jCourant.donnerCarte());	break;
			case 2:
				for(i=0; i<2; i++)
					jSuivant.recevoirCarte(maPioche.piocherCarte());
				jSuivant.setEtat(false);
				break;
			case AS: nbAs++; break;
			}
		}
		else if (this.nbAs > 0){ //Si son prédécesseur avait joué un as, et qu'il n'a pas d'as.
			for(i=0; i<2*this.nbAs; i++)
				jCourant.recevoirCarte(maPioche.piocherCarte());
		}
		else if(jCourant.etat){ //S'il n'a pas joué car il ne pouvait pas poser, il pioche !
			jCourant.recevoirCarte(maPioche.piocherCarte());
		}
		
		if (c.getH() != AS)
			nbAs = 0;
	}

	public String traduireCarte(Carte c){
		//Exemple : pour c tel que c.hauteur = 11 et c.symb = 2
		//cette fonction retournera la chaine "valet de trèfle"
		//On pourra aussi la modifier pour retourner {"valet", "trèfle"}
		
		int[] haut_symb = c.get();
		//System.out.println(haut_symb[0] + "  " + haut_symb[1]);
		if (haut_symb[1] == JOKER)
			return Symbole[haut_symb[1]];
		else
			return Hauteur[haut_symb[0]]+ " de " + Symbole[haut_symb[1]];
		
	}
	public void distribuer(){
		for(Joueur monJoueur : mesJoueurs){
			for(int i=0; i<NB_CARTES_PAR_JOUEUR; i++){
				monJoueur.recevoirCarte(maPioche.piocherCarte());
				//System.out.println("Joueur " + this.id + " --> Reçu : " + maCarte.get()[0] + ":" + maCarte.get()[1]);
			}
		}

	}
	public Carte retournerBergere(){
		//Tant que la bergère est un joker,
		//on recréer une pioche et on reéssaye.
		
		Carte c = maPioche.piocherCarte();
		while (c.getS() == JOKER){
			this.maPioche = new Pioche();
			c = maPioche.piocherCarte();
		}
		maPile.empiler(c);
		return c;
	}

}






