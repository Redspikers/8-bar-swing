package com.jeu.core;
/**
 * Permet de créer une carte 
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class Carte implements java.io.Serializable {
	
	private int hauteur;	//2, 3, 4, ..., 15
	private int symb;		//0, 1, 2, 3, 4 = Pique, carreau, trèfle, coeur et joker
	
	/**
	 * Constructeur permettant de créer la carte
	 * @param h Hauteur de la carte comprise entre 2 et 15
	 * @param s Symbole de la carte compris entre 0 et 4 (0, 1, 2, 3, 4 = Pique, carreau, trèfle, coeur et joker)
	 */
	public Carte(int h, int s){
		this.hauteur = h;
		this.symb = s;
	}
	
	/**
	 * Récupère les informations de hauteur et de symbole de la carte
	 * @return Hauteur et Symbole
	 */
	public int[] get(){
		return new int[]{this.hauteur, this.symb};
	}
	
	/**
	 * Récupère l'information de hauteur de la carte
	 * @return Hauteur de la carte
	 */
	public int getH(){
		return this.hauteur;
	}
	
	/**
	 * Récupère l'information de symbole de la carte
	 * @return Symbole de la carte
	 */
	public int getS(){
		return this.symb;
	}
}
