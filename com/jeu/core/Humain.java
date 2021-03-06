package com.jeu.core;

/**
 * Un joueur humain
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class Humain extends Joueur{

	private static final long serialVersionUID = 1L;

	public Humain(int id) {
		super(id);
	}
	
	public Carte jouer(Carte hautDePile, int nbAs) {
		
		if(this.etat == false){
			return null;
		}
		int hauteur;
		int symbole;
		Carte c;
		

		if(!super.isJeuJouable(hautDePile, nbAs)){
			return null;
		}
		do{
			System.out.print("Entrez la hauteur : ");hauteur = in.nextInt();
			System.out.print("Entrez le symbole : ");symbole = in.nextInt();
			c = new Carte(hauteur, symbole);
			
		}while(!(inJeu(c) && jouerCarte(c, hautDePile, nbAs)));
		System.out.print("IL A TROUVE DANS LE JEU et c'est bon");
		
		//Si la carte est une carte spéciale, on lui demande de choisir la couleur.
		if (hauteur==8 || symbole==JOKER){
			CarteSpeciale cs = new CarteSpeciale(hauteur, symbole);
			System.out.print("\nEntrez la couleur choisie : ");
			cs.setSymboleChoisi(in.nextInt());
			return cs;
		}
		
		return c;
	}
}
