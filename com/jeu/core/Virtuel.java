package com.jeu.core;
import java.util.ArrayList;

import com.jeu.strategie.*;

/**
 * Représente un joueur contrôlé par l'ordinateur
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class Virtuel extends Joueur {
	
	private Strategie strategie;
	
	/**
	 * Constructeur de joueur virtuel
	 * @param id L'identifiant unique du joueur dans la partie
	 */
	public Virtuel(int id) {
		super(id);	
	}

	public Carte jouer(Carte hautDePile, int nbAs) {
		Carte carteChoisie; 
		
		if(this.etat == false){
			return null;
		}
		
		
		//On fait un sous-tableau de son jeu contenant les cartes jouables.
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		for (Carte c : monJeu){
			if(jouerCarte(c, hautDePile, nbAs))
				cartesJouables.add(c);
		}
		
		//Le joueur a-t-il au moins une carte jouable ?
		if(cartesJouables.size() == 0)
			return null;
		
		//On applique la stratégie
		carteChoisie = this.strategie.choisirCarte(cartesJouables);
		//Si la carte est un 8 ou un joker, on choisit le symbole majoritaire dans notre jeu
		if (carteChoisie.getH() == 8 || carteChoisie.getS() == JOKER)
			((CarteSpeciale)carteChoisie).setSymboleChoisi(getSymboleMajorInJeu());
		
		//Enfin, si c'est son avant dernière carte, on lui fait dire carte
		if(super.getNbCartesJeu()<=2)
			super.direCarte();
		return carteChoisie;
	}
	
	/**
	 * Définit la stratégie selon laquelle un joueur virtuel va jouer
	 * @param s La stratégie à utiliser
	 */
	public void setStrategie(Strategie s){
		this.strategie = s;
	}
	
	/**
	 * Recupère le symbole qui existe en plus grand nombre dans le jeu d'un joueur
	 * @return Le symbole existant en plus grand nombre
	 */
	public int getSymboleMajorInJeu(){
		//Renvoie le symbole le plus présent dans le jeu.
		int[] nbSymbole = {0, 0, 0, 0};
		
		for(Carte c : monJeu){
			if(c.getS() != JOKER)
				nbSymbole[c.getS()]++;
		}
		
		int max = nbSymbole[0];
		int i_max = 0;
		//On cherche la couleur majoritaire
		for(int i = 1; i<nbSymbole.length; i++){
			if(nbSymbole[i]>max){
				max = nbSymbole[i];
				i_max = i;
			}
		}
		return i_max;
	}
}
