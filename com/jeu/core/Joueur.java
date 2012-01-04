package com.jeu.core;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Observable;
public abstract class Joueur extends Observable implements Enums_Interfaces.Symbole, Enums_Interfaces.Hauteur{
	
	private int id;
	protected boolean etat;
	private boolean disCarte;
	
	protected ArrayList<Carte> monJeu;
	
	public abstract Carte jouer(Carte hautDePile, int nbAs);
	public static Scanner in = new Scanner(System.in);
	
	
	public Joueur(int id){
		this.id = id;
		this.etat = true;
		this.disCarte = false;
		this.monJeu = new ArrayList<Carte>();
	}
	
	public int getId() {
		return id;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public boolean isEtat() {
		return etat;
	}
	
	public boolean isJeuJouable(Carte hautDePile, int nbAs){
		//Le joueur a-t-il au moins une carte jouable ?
		
		for (Carte c : this.monJeu){
			if(jouerCarte(c, hautDePile, nbAs))
				return true;
		}
		return false;
	}
	public boolean jouerCarte(Carte c, Carte hautDePile, int nbAs) {
		int[] c_tab    = c.get();
		int[] pile_tab = hautDePile.get();
		int couleurEtudiee;
		if(c_tab[0] == pile_tab[0])
			return true;//si même hauteur
		if (hautDePile instanceof CarteSpeciale)
			couleurEtudiee = ((CarteSpeciale)hautDePile).getSymboleChoisi();
		else
			couleurEtudiee = pile_tab[1];
		if (c_tab[1] == couleurEtudiee && nbAs == 0)//si même couleur(symbole) et que le précédent joueur n'a pas posé d'as
			return true;
		  //Si on veut jouer un 8 ou joker derriere autre chose qu'un as
		if((c_tab[0]==8 || c_tab[1]==JOKER) && pile_tab[0] != AS)
		  return true;
		return false;
	}
	
	public boolean recevoirCarte(Carte maCarte) {
		this.monJeu.add(maCarte);
		if(this.getNbCartesJeu() > 2)
			this.disCarte = false;
		return true;
	}
	
	public Carte donnerCarte() {
		Random r = new Random();
		Carte c = this.monJeu.remove(r.nextInt(monJeu.size()));
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
	
	public void afficherJeu(){
		//Affiche le jeu du joueur.
		for(Carte c : monJeu){
			System.out.print(traduireCarte(c)+", ");
		}
		System.out.print("\n");
	}
	public int getNbCartesJeu(){
		//Affiche le nombre de cartes dans le jeu du joueur.
		return monJeu.size();
	}
	
	public ArrayList<Carte> getCartes(){
		// Retourne les cartes dans la main du joueur
		return monJeu;
	}
	
	protected boolean inJeu(Carte c){
		//La carte c est-il dans monJeu ?
		//C à dire : Existe-t-il une carte dans monJeu
		//qui a la même hauteur et le même symbole que c ?
		
		for (Carte c2 : monJeu){
			//System.out.println(traduireCarte(c)+" - "+traduireCarte(c2));
			if (java.util.Arrays.equals(c.get(), c2.get()))
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
		else
			return Hauteur[haut_symb[0]]+ " de " + Symbole[haut_symb[1]];
		
	}
	
	public void notifyVue(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public void direCarte(){
		if(monJeu.size() <= 2)
			this.disCarte = true;
	}
	
	public boolean aDitCarte(){
		return disCarte;
	}
}
