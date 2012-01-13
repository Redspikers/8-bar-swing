package com.jeu.core;

/**
 * Classe utilisée pour définir une carte ayant un comportement spécial vis à vis des règles du jeu
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class CarteSpeciale extends Carte {
	
	private int symboleChoisi; 
	
	/**
	 * Constructeur permettant de créer un carte spéciale
	 * @param h Hauteur de la carte 
	 * @param s Symbole de la carte
	 */
	public CarteSpeciale(int h, int s){
		super(h, s);
	}
	
	/**
	 * Récupère le nouveau symbole qui sera joué au prochain tour
	 * @return Le nouveau symbole du jeu
	 */
	public int getSymboleChoisi(){
		return this.symboleChoisi;
	}
	
	/**
	 * Défini le nouveau symbole du jeu
	 * @param symboleChoisi Le symbole qui sera joué choisi par l'utilisateur 
	 */
	public void setSymboleChoisi(int symboleChoisi){
		this.symboleChoisi = symboleChoisi;
	}
}
