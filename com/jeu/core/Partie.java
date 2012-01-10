package com.jeu.core;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import com.jeu.strategie.*;

public class Partie implements Enums_Interfaces.Hauteur, Enums_Interfaces.Symbole, Enums_Interfaces.Messages, java.io.Serializable{
	
	private static final int NB_CARTES_PAR_JOUEUR = 8;
	private static final int NOMBRE_STRATEGIES = 2;
	public static int Message_Info = 0;
	private ArrayList<Joueur> mesJoueurs;
	private Pioche maPioche;
	private Pile maPile;
	
	private int nbJoueurs;
	private int nbHumains;
	private int numJoueurCourant; //Le numéro du joueur qui doit jouer
	//Le déroulement est croissant (joueur0 puis joueur1 puis ...) au début mais peut s'inverser.
	private boolean sensCroissant; 
	private boolean debut;
	boolean enMarche;
	
	public static Scanner in = new Scanner(System.in);
	private ArrayList<Strategie> mesStrategies;
	private int nbAs;
	
	public Partie(int nbH, int nbJ){
		
		
		//On enregistre les stratégies à notre disposition
		//Pour les affecter aux joueurs virtuels
		this.mesStrategies = new ArrayList<Strategie>();
		this.mesStrategies.add(new StrategieRandom());
		this.mesStrategies.add(new StrategieHauteur());
		
		
		init(nbH, nbJ);
		
	}
	
	public void init(int nbH, int nbJ){
		this.nbJoueurs = nbJ;
		this.nbHumains = nbH;
		this.numJoueurCourant = 0;
		this.sensCroissant = true;
		this.debut = true;
		this.enMarche = true;
		this.nbAs = 0;
		this.mesJoueurs = new ArrayList<Joueur>();
		
		// Création de la pioche
		this.maPioche = new Pioche();
		
		// Création de la pile
		this.maPile = new Pile();
		
		//Création des joueurs 
		int i;
		Virtuel v;
		for(i=0; i<this.nbJoueurs; i++){
			if(i<this.nbHumains)
				mesJoueurs.add(new Humain(i));
			else{
				v = new Virtuel(i);
				v.setStrategie(this.mesStrategies
						.get((int)(Math.random()*NOMBRE_STRATEGIES)));
				mesJoueurs.add(v);
			}
		}
		//Collections.shuffle(mesJoueurs); 
		
		//Distributions des cartes aux joueurs
		distribuer();

		//ICI on va retourner la bergère.
		analyserPassage(retournerBergere());
		
		//gestionDuJeuConsole();
		
	}
	
	public void gestionDuJeu(){
		
		System.out.println("\n");
		Message_Info = 0;
		
		Joueur jCourant = getJoueurCourant();
		jCourant.finiDireCarte();
		if (jCourant instanceof Virtuel){
			analyserPassage(jCourant.jouer(maPile.getHautDePile(), this.nbAs));
		}
		jCourant.setEtat(true);
		this.verifierPioche();
		this.changerDeJoueur();
		System.out.println(getMessageActuel());
		if (isVictoire())
				enMarche = false;
	}
	
	public void gestionDuJeuConsole(){
		boolean enMarche = true;
		Carte c;
		Joueur jCourant;
		while(enMarche){
			System.out.println("\n");
			Messages.Message_Info = 0;
			System.out.println("Haut de pile : "+traduireCarte(maPile.getHautDePile()));
			System.out.println("Le joueur "+numJoueurCourant+" doit jouer.");
			jCourant = mesJoueurs.get(numJoueurCourant);
			
			jCourant.afficherJeu();
			c = jCourant.jouer(maPile.getHautDePile(), this.nbAs);
			System.out.println("\nIl joue : " + traduireCarte(c));
			analyserPassage(c);
			jCourant.setEtat(true);
			this.changerDeJoueur();
			System.out.println(""+MESSAGE[Message_Info]);
			if (isVictoire())
				enMarche = false;
		}
		System.out.println("La partie est terminée, on va rejouer.");
		init(2, 2);
	}
	public void changerDeJoueur(){
		//Calcule l'indice du joueur suivant après la fin du tour du joueur actuel.
		this.numJoueurCourant = getNumJoueurSuivant();
	}
	public void analyserPassage(Carte c){
		//Cette fonction reçoit la carte qui vient d'être jouée (c)
		//Elle permet d'effectuer tous les mechanismes entre le joueurN et le joueurN+1
		//(piocher une carte chez un autre joueur, dans la pioche, compter les As, ...)
		
		int i;
		
		
		Joueur jCourant = getJoueurCourant();
		Joueur jSuivant = getJoueurSuivant();
		

		if (c != null){ //Si le joueur courant vient de poser une carte
			if(!debut)
				maPile.empiler(jCourant.donnerCarte(c));
			
			switch(c.getH()){
			case JOKER_H:
				for(i=0; i<5; i++)
					jSuivant.recevoirCarte(maPioche.piocherCarte());
				jSuivant.setEtat(false);
				Message_Info = POSE_JOKER;
				break;	
			case 10: 
				sensCroissant = !sensCroissant; 
				Message_Info = POSE_10; 
				break;
			case 8: Message_Info = POSE_8; break;
			case 7:	
				jSuivant.recevoirCarte(jCourant.donnerCarte()); 
				Message_Info = POSE_7;	
				break;
			case 2:
				for(i=0; i<2; i++)
					jSuivant.recevoirCarte(maPioche.piocherCarte());
				jSuivant.setEtat(false);
				Message_Info = POSE_2;
				break;
			case AS: nbAs++; break;
			}
			
			
			if (c.getH() != AS)
				nbAs = 0;
		}
		else{
			Message_Info = DOIT_PASSER;
			if (this.nbAs > 0){ //Si son prÃ©dÃ©cesseur avait jouÃ© un as, et qu'il n'a pas d'as.
				for(i=0; i<2*this.nbAs; i++)
					jCourant.recevoirCarte(maPioche.piocherCarte());
				Message_Info = POSE_AS;
				this.nbAs = 0;
			}
			else if(jCourant.etat){ //S'il n'a pas jouÃ© car il ne pouvait pas poser, il pioche !
				Message_Info = AUCUNE_CARTE_JOUABLE;
				jCourant.recevoirCarte(maPioche.piocherCarte());
			}
		}
		debut = false;
		
	}

	public Joueur getJoueurPrecedent(){
		if (debut) //au premier tour, c'est la partie qui retourne la bergère.
			return this.getJoueurCourant();
		return mesJoueurs.get(getNumJoueurPrecedent());
	}
	public Joueur getJoueurCourant(){
		return mesJoueurs.get(numJoueurCourant);
	}
	public Joueur getJoueurSuivant(){
		if (debut) //au premier tour, c'est la partie qui retourne la bergère.
			return this.getJoueurCourant();
		return mesJoueurs.get(getNumJoueurSuivant());
	}
	 public void setEnMarche(boolean enMarche){
		 this.enMarche = enMarche;
	 }


	public boolean isVictoire(){
		for(Joueur j : mesJoueurs){
			if(j.getNbCartesJeu() == 0)
				return true;
		}
		return false;
	}
	public String traduireCarte(Carte c){
		//Exemple : pour c tel que c.hauteur = 11 et c.symb = 2
		//cette fonction retournera la chaine "valet de trèfle"
		//On pourra aussi la modifier pour retourner {"valet", "trèfle"}
		
		int[] haut_symb = c.get();
		//System.out.println(haut_symb[0] + "  " + haut_symb[1]);
		if (haut_symb[1] == JOKER)
			return Symbole[haut_symb[1]];
		else if (haut_symb[1] == -1)
			return "rien";
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
		//on recreer une pioche et on reessaye.
		
		Carte c;
		do{
			c = maPioche.piocherCarte();
			maPile.empiler(c);
		}while (c.getS() == JOKER || c.getH() == 8 || c.getH() == 10 || c.getH() == 2 || c.getH() == AS || c.getH() == 7);
		return c;
	}
	
	public ArrayList<Joueur> getJoueurs(){
		return this.mesJoueurs;
	}
	
	public int getNbAs(){
		return this.nbAs;
	}
	public Pile getPile(){
		return this.maPile;
	}	
	public Pioche getPioche(){
		return this.maPioche;
	}
	
	public boolean getEnMarche(){
		return this.enMarche;
	}
	
	public int getNumJoueurPrecedent(){
		int res = (sensCroissant) ? (numJoueurCourant-1)%nbJoueurs : (numJoueurCourant+1)%nbJoueurs;
		if (res<0)
			return nbJoueurs-1;
		return res;
	}
	
	public int getNumJoueurCourant(){
		return this.numJoueurCourant;
	}
	
	public int getNumJoueurSuivant(){
		int res = (sensCroissant) ? (numJoueurCourant+1)%nbJoueurs : (numJoueurCourant-1)%nbJoueurs;
		if (res<0)
			return nbJoueurs-1;
		return res;
	}
	public String getMessageActuel(){
		return MESSAGE[Message_Info];
	}
	
	public void setMessageActuel(int messageID){
		Message_Info = messageID;
	}
	public Joueur getJoueur(int id){
		return mesJoueurs.get(id);
	}
	
	public boolean denoncier(int joueurDenonciateur, int joueurDenoncie){
		Joueur leJoueurDenonciateur = getJoueur(joueurDenonciateur);
		Joueur leJoueurDenoncie     = getJoueur(joueurDenoncie);

		if((leJoueurDenoncie.getNbCartesJeu() < 2) && (!leJoueurDenoncie.aDitCarte())){
			leJoueurDenoncie.recevoirCarte(maPioche.piocherCarte());
			leJoueurDenoncie.recevoirCarte(maPioche.piocherCarte());
			return true;
		}else{
			leJoueurDenonciateur.recevoirCarte(maPioche.piocherCarte());
			leJoueurDenonciateur.recevoirCarte(maPioche.piocherCarte());
			return false;
		}
		
	}
	
	public void verifierPioche(){
		//Si la pioche n'a presque plus de carte, on en prend 
		//dans la pile qui contient pas mal de cartes.
		if(maPioche.isPiochePresqueVide())
			maPioche.retournerPile(maPile);
	}
	public void sauvegarderPartie(){
		// Write to disk with FileOutputStream
		FileOutputStream f_out = null;
		try {
			f_out = new 
				FileOutputStream("sauvegarde.8bar");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = null;
		try {
			obj_out = new
				ObjectOutputStream(f_out);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Write object out to disk
		try {
			obj_out.writeObject(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}






