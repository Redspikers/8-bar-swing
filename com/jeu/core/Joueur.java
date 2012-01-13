package com.jeu.core;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Observable;

/**
 * Permet de définir les comportements des joueurs qui seront soit Humain soit Virtuel
 * @author Nicolas et Victor 
 * @version 1.0
 */
public abstract class Joueur extends Observable implements Enums_Interfaces.Symbole, Enums_Interfaces.Hauteur, java.io.Serializable{
	
	private int id;
	protected boolean etat;
	private boolean disCarte;
	protected ArrayList<Carte> monJeu;
	public abstract Carte jouer(Carte hautDePile, int nbAs);
	public static Scanner in = new Scanner(System.in);
	
	/**
	 * Constructeur permettant de créer un joueur
	 * @param id L'identifiant unique du joueur dans la partie
	 */
	public Joueur(int id){
		this.id = id;
		this.etat = true;
		this.disCarte = false;
		this.monJeu = new ArrayList<Carte>();
	}
	
	/**
	 * Récupère l'identifiant du joueur
	 * @return L'identifiant du joueur
	 */
	public int getId() {
		return id;
	}

	/**
	 * Défini l'état du joueur
	 * @param etat L'état du joueur
	 */
	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	/**
	 * Récupère l'état du joueur
	 * @return L'état du joueur
	 */
	public boolean isEtat() {
		return etat;
	}
	
	/**
	 * Permet de savoir si le joueur peut poser une carte de sa main dans le contexte de la partie
	 * @param hautDePile La carte sur laquelle le joueur doit jouer
	 * @param nbAs Le nombre d'As qui ont été posés
	 * @return Vrai si le joueur à le droit de jouer, Faux sinon
	 */
	public boolean isJeuJouable(Carte hautDePile, int nbAs){
		//Le joueur a-t-il au moins une carte jouable ?
		for (Carte c : this.monJeu){
			if(jouerCarte(c, hautDePile, nbAs))
				return true;
		}
		return false;
	}
	
	/**
	 * Permet de poser une carte sur la pile
	 * @param c La carte à poser sur la pile
	 * @param hautDePile La carte déjà présente sur laquelle le joueur doit jouer
	 * @param nbAs Le nombre d'As qui ont été joués sur le haut de la pile
	 * @return Vrai si le joueur à posé sa carte
	 */
	public boolean jouerCarte(Carte c, Carte hautDePile, int nbAs) {
		int[] c_tab    = c.get();
		int[] pile_tab = hautDePile.get();
		int symboleEtudie;
		boolean conditionHuitJoker; //La carte que l'on veut placée est-elle un huit ou un joker ?
		
		if (hautDePile instanceof CarteSpeciale)
			symboleEtudie = ((CarteSpeciale)hautDePile).getSymboleChoisi();
		else
			symboleEtudie = pile_tab[1];
		
		
		//Si on veut jouer un 8 ou joker
		if(c_tab[0]==8 || c_tab[1]==JOKER){
			//derriere autre chose qu'un as et que c'est pas sa dernière carte.
			if(pile_tab[0] != AS && this.getNbCartesJeu() > 1)
				  return true;
		} else {
			if (c_tab[1] == symboleEtudie && nbAs == 0)
				return true;//si même couleur(symbole) et que le précédent joueur n'a pas posé d'as
			else if(c_tab[0] == pile_tab[0])
				return true;//si même hauteur
		}
		return false;
	}
	
	/**
	 * Ajoute une carte au jeu du joueur
	 * @param maCarte La carte à ajouter à la main du joueur
	 */
	public void recevoirCarte(Carte maCarte) {
		if(maCarte != null)
			this.monJeu.add(maCarte);
	}
	
	/**
	 * Donne une carte à un autre joueur
	 * @return La carte donnée à un autre joueur
	 */
	public Carte donnerCarte() {
		Random r = new Random();
		Carte c = null;
		if (monJeu.size() != 0)
			c = this.monJeu.remove(r.nextInt(monJeu.size()));
		return c;
	}
	
	public Carte donnerCarte(Carte c) {
		for (Carte c2 : monJeu){
			if (java.util.Arrays.equals(c.get(), c2.get())){
				monJeu.remove(c2);
				break;
			}
		}
		return c;
	}
	
	/**
	 * Affiche le jeu du joueur en mode console
	 */
	public void afficherJeu(){
		for(Carte c : monJeu){
			System.out.print(traduireCarte(c)+", ");
		}
		System.out.print("\n");
	}
	
	/**
	 * Récupère le nombre de cartes dans la main du joueur
	 * @return Le nombre de cartes dans la main du joueur
	 */
	public int getNbCartesJeu(){
		return monJeu.size();
	}
	
	/**
	 * Récupère les cartes du joueur
	 * @return Les cartes du joueur
	 */
	public ArrayList<Carte> getCartes(){
		return monJeu;
	}
	
	/**
	 * Calcule les points du joueurs en fonction des cartes restantes
	 * @return Le score du joueur
	 */
	public int calculPoints(){
		int score = 0;
		for(Carte c: this.monJeu){
			switch(c.getH()){
			case JOKER_H: score+=50; break;
			case 8:       score+=32; break;
			case AS:      score+=20; break;
			case ROI:
			case DAME:
			case VALET:   score+=10; break;
			default:      score+=c.getH();
			}
		}
		return score;
	}
	
	/**
	 * Savoir si une carte précisée existe dans la main du joueur
	 * @param c La carte à rechercher
	 * @return Vrai si la carte existe, Faux sinon
	 */
	protected boolean inJeu(Carte c){
		//La carte c est-elle dans monJeu ?
		//C'est-à-dire : Existe-t-il une carte dans monJeu
		//qui a la même hauteur et le même symbole que c ?
		
		for (Carte c2 : monJeu){
			if (java.util.Arrays.equals(c.get(), c2.get()))
				return true;
		}
		return false;
	}
	
	/**
	 * Traduit la hauteur et le symbole numérique d'une carte en un texte
	 * @param c La carte à traduire
	 * @return La traduction en texte
	 */
	public String traduireCarte(Carte c){
		//Exemple : pour c tel que c.hauteur = 11 et c.symb = 2
		//cette fonction retournera la chaine "valet de trèfle"
		//On pourra aussi la modifier pour retourner {"valet", "trèfle"}
		
		int[] haut_symb = c.get();
		if (haut_symb[1] == JOKER)
			return Symbole[haut_symb[1]];
		else
			return Hauteur[haut_symb[0]]+ " de " + Symbole[haut_symb[1]];
		
	}
	
	/**
	 * Notifie la vue
	 */
	public void notifyVue(){
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Dit "Carte !" lorsqu'il ne reste plus qu'une carte au joueur
	 */
	public void direCarte(){
		if(monJeu.size() <= 2)
			this.disCarte = true;
	}
	
	/**
	 * Le joueur qui avait dit "Carte !" ne le dit plus car il a reçu de nouvelles cartes dans sa main
	 */
	public void finiDireCarte(){
		this.disCarte = false;
	}
	
	/**
	 * Permet de savoir si un joueur à dit "Carte !" ou non
	 * @return Vrai si un joueur à dit "Carte !"
	 */
	public boolean aDitCarte(){
		return disCarte;
	}
}
