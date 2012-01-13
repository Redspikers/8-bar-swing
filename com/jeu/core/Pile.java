package com.jeu.core;
import java.util.ArrayList;

/**
 * Tas de cartes représentant la pile du jeu
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class Pile implements java.io.Serializable {
	
	private ArrayList<Carte> cPile = new ArrayList<Carte>();
	
	/**
	 * Constructeur de pile
	 */
	public Pile(){
		
	}
	
	/**
	 * Récupère la carte sur laquelle les joueurs doivent jouer
	 * @return La carte au sommet de la pile
	 */
	public Carte getHautDePile(){
		if(cPile.size() > 0)
			return cPile.get(cPile.size()-1);
		System.out.println("La pile est vide et on demande le haut de pile.");
		System.exit(-1);
		return null;
	}
	
	/**
	 * Ajoute une carte en haut de la pile
	 * @param c La carte à ajouter
	 */
	public void empiler(Carte c){
		cPile.add(c);
	}
	
	/**
	 * Retourne la pile pour en faire une pioche
	 * @return La carte du dessous de la pile
	 */
	public Carte depiler(){
		return cPile.remove(cPile.size()-1);
	}
	
	/**
	 * Récupère la taille de la pile
	 * @return La taille de la pile
	 */
	public int size(){
		return cPile.size();
	}


}
