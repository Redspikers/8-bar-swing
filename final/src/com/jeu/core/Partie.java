package com.jeu.core;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.jeu.strategie.*;

/**
 * Partie de huit américain joué
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class Partie implements com.jeu.Enums_Interfaces.Hauteur, com.jeu.Enums_Interfaces.Symbole, com.jeu.Enums_Interfaces.Messages, java.io.Serializable{
	

	private static final long serialVersionUID = 1L;
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
	private boolean enMarche;
	public static Scanner in = new Scanner(System.in);
	private ArrayList<Strategie> mesStrategies;
	private int nbAs;
	
	/**
	 * Construit une nouvelle partie
	 * @param nbH Nombre de joueurs humains
	 * @param nbJ Nombre de joueurs au total
	 */
	public Partie(int nbH, int nbJ){
		//On enregistre les stratégies à notre disposition
		//Pour les affecter aux joueurs virtuels
		this.mesStrategies = new ArrayList<Strategie>();
		this.mesStrategies.add(new StrategieRandom());
		this.mesStrategies.add(new StrategieHauteur());
		init(nbH, nbJ);
	}
	
	/**
	 * Initialise la partie créer
	 * @param nbH Nombre de joueurs Humains
	 * @param nbJ Nombre de joueurs au total
	 */
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
		
		//Distributions des cartes aux joueurs
		distribuer();

		//Retourne la bergère.
		analyserPassage(retournerBergere());
		
		
	}
	
	/**
	 * Permet de gérer le déroulement du jeu pour que les joueurs jouent tour à tour en mode graphique
	 */
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
	
	/**
	 * Permet de gérer le déroulement du jeu pour que les joueurs jouent tour à tour en mode console
	 * Méthode gardée pour une éventuelle rétro-compatibilité avec le mode console du jeu
	 * @Deprecated
	 */
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
	
	/**
	 * Calcule l'indice du joueur suivant après la fin du tour du joueur actuel
	 */
	public void changerDeJoueur(){
		this.numJoueurCourant = getNumJoueurSuivant();
	}
	
	/**
	 * Pose la carte choisie, et gère les mécanismes spéciaux, entre le joueur N et le joueur suivant, liés à la pose d'une carte spéciale
	 * @param c La carte qui vient d'être posée
	 */
	public void analyserPassage(Carte c){
		
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
			if (this.nbAs > 0){ //Si son prédecesseur avait joué un as, et qu'il n'a pas d'as.
				for(i=0; i<2*this.nbAs; i++)
					jCourant.recevoirCarte(maPioche.piocherCarte());
				Message_Info = POSE_AS;
				this.nbAs = 0;
			}
			else if(jCourant.etat){ //S'il n'a pas joué car il ne pouvait pas poser, il pioche
				Message_Info = AUCUNE_CARTE_JOUABLE;
				jCourant.recevoirCarte(maPioche.piocherCarte());
			}
		}
		debut = false;
		
	}

	/**
	 * Récupère le joueur qui vient de jouer
	 * @return Le joueur venant de jouer
	 */
	public Joueur getJoueurPrecedent(){
		if (debut) //au premier tour, c'est la partie qui retourne la bergère.
			return this.getJoueurCourant();
		return mesJoueurs.get(getNumJoueurPrecedent());
	}
	
	/**
	 * Récupère le joueur qui joue
	 * @return Le joueur en train de joeur
	 */
	public Joueur getJoueurCourant(){
		return mesJoueurs.get(numJoueurCourant);
	}
	
	/**
	 * Récupère le joueur qui va jouer après le tour du joueur courant
	 * @return Le joueur qui va jouer
	 */
	public Joueur getJoueurSuivant(){
		if (debut) //au premier tour, c'est la partie qui retourne la bergère.
			return this.getJoueurCourant();
		return mesJoueurs.get(getNumJoueurSuivant());
	}
	
	/**
	 * Définit si le jeu est en marche
	 * @param enMarche L'état du jeu
	 */
	public void setEnMarche(boolean enMarche){
		 this.enMarche = enMarche;
	}

	/**
	 * Teste si un joueur a gagné la partie
	 * @return Vrai si un joueur a gagné
	 */
	public boolean isVictoire(){
		for(Joueur j : mesJoueurs){
			if(j.getNbCartesJeu() == 0)
				return true;
		}
		return false;
	}
	
	/**
	 * Traduit une carte du format numérique en format textuel
	 * @param c La carte à traduire
	 * @return Le texte de la traduction
	 */
	public String traduireCarte(Carte c){
		//Exemple : pour c tel que c.hauteur = 11 et c.symb = 2
		//cette fonction retournera la chaine "valet de trèfle"
		
		int[] haut_symb = c.get();
		if (haut_symb[1] == JOKER)
			return Symbole[haut_symb[1]];
		else if (haut_symb[1] == -1)
			return "rien";
		else
			return Hauteur[haut_symb[0]]+ " de " + Symbole[haut_symb[1]];
		
	}
	
	/**
	 * Distribue les cartes aux joueurs
	 */
	public void distribuer(){
		for(Joueur monJoueur : mesJoueurs){
			for(int i=0; i<NB_CARTES_PAR_JOUEUR; i++){
				monJoueur.recevoirCarte(maPioche.piocherCarte());
			}
		}

	}
	
	/**
	 * Retourne la bergère dans la pioche pour créer la pile
	 * @return La carte bergère
	 */
	public Carte retournerBergere(){
		//Tant que la bergère est une carte avec effet,
		//on repioche.
		
		Carte c;
		do{
			c = maPioche.piocherCarte();
			maPile.empiler(c);
		}while (c.getS() == JOKER || c.getH() == 8 || c.getH() == 10 || c.getH() == 2 || c.getH() == AS || c.getH() == 7);
		return c;
	}
	
	/**
	 * Recupère une collection de joueurs
	 * @return Les joueurs participant à la partie
	 */
	public ArrayList<Joueur> getJoueurs(){
		return this.mesJoueurs;
	}
	
	/**
	 * Récupère le nombre d'As joués à la suite
	 * @return Le nombre d'As joués à la suite
	 */
	public int getNbAs(){
		return this.nbAs;
	}
	
	/**
	 * Récupère la pile du jeu
	 * @return La pile du jeu
	 */
	public Pile getPile(){
		return this.maPile;
	}	
	
	/**
	 * Récupère la pioche du jeu
	 * @return La pioche du jeu
	 */
	public Pioche getPioche(){
		return this.maPioche;
	}
	
	/**
	 * Récupère l'état du jeu
	 * @return Vrai si le jeu est en marche
	 */
	public boolean getEnMarche(){
		return this.enMarche;
	}
	
	/**
	 * Récupère le numéro du joueur précédent
	 * @return Le numéro du joueur précédent
	 */
	public int getNumJoueurPrecedent(){
		int res = (sensCroissant) ? (numJoueurCourant-1)%nbJoueurs : (numJoueurCourant+1)%nbJoueurs;
		if (res<0)
			return nbJoueurs-1;
		return res;
	}
	
	/**
	 * Récupère le numéro du joueur courant
	 * @return Le numéro du joueur courant
	 */
	public int getNumJoueurCourant(){
		return this.numJoueurCourant;
	}
	
	/**
	 * Récupère le numéro du joueur suivant
	 * @return Le numéro du joueur suivant
	 */
	public int getNumJoueurSuivant(){
		int res = (sensCroissant) ? (numJoueurCourant+1)%nbJoueurs : (numJoueurCourant-1)%nbJoueurs;
		if (res<0)
			return nbJoueurs-1;
		return res;
	}
	
	/**
	 * Récupère le message actuel
	 * @return Le message actuel
	 */
	public String getMessageActuel(){
		return MESSAGE[Message_Info];
	}
	
	/**
	 * Définit le message à afficher dans la status bar
	 * @param messageID Le message à afficher
	 */
	public void setMessageActuel(int messageID){
		Message_Info = messageID;
	}
	
	/**
	 * Récupère la référence sur un joueur en fonction de son identifiant
	 * @param id L'identifiant du joueur
	 * @return Le joueur recherché
	 */
	public Joueur getJoueur(int id){
		return mesJoueurs.get(id);
	}
	
	/**
	 * Dénonce un joueur qui n'a pas dit "Carte !"
	 * @param joueurDenonciateur Le joueur qui dénonce
	 * @param joueurDenoncie Le joueur dénoncé
	 * @return Vrai si La dénonciation était justifiée, faux sinon
	 */
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
	
	/**
	 * Verifie que la pioche a encore suffisamment de cartes, et la remplit dans le cas contraire
	 */
	public void verifierPioche(){
		//Si la pioche n'a presque plus de carte, on en prend 
		//dans la pile qui contient des cartes.
		if(maPioche.isPiochePresqueVide())
			maPioche.retournerPile(maPile);
	}
	
	/**
	 * Sauvegarde la partie sur l'ordinateur
	 */
	public void sauvegarderPartie(){
		FileOutputStream f_out = null;
		try {
			f_out = new 
				FileOutputStream("sauvegarde.8bar"); // Fichier enregistré sur l'ordinateur
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		ObjectOutputStream obj_out = null;
		try {
			obj_out = new
				ObjectOutputStream(f_out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			obj_out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}






