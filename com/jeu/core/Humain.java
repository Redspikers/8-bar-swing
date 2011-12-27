package com.jeu.core;
import com.jeu.observer.Observateur;


public class Humain extends Joueur{

	public Humain(int id) {
		super(id);
		
	}
	
	@Override
	public Carte jouer(Carte hautDePile, int nbAs) {
		
		if(this.etat == false){
			return new Carte(-1, -1);
		}
		int hauteur;
		int symbole;
		int choix;
		boolean carteJouable = false;
		Carte c;
		
		//Le joueur a-t-il au moins une carte jouable ?
		for (Carte c2 : monJeu){
			if(jouerCarte(c2, hautDePile, nbAs)){
				carteJouable = true;break;
			}
		}
		if(!carteJouable){
			return new Carte(-1, -1);
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
			cs.setCouleurChoisie(in.nextInt());
			return cs;
		}
		
		return c;
	}
}
