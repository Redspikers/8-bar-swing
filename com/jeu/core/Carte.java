package com.jeu.core;

public class Carte implements java.io.Serializable {
	//2, 3, 4, ..., 15
	private int hauteur;
	
	//0, 1, 2, 3, 4 = Pique, carreau, trèfle, coeur et joker
	private int symb;
	
	public Carte(int h, int s){
		this.hauteur = h;
		this.symb = s;
	}
	
	public int[] get(){
		return new int[]{this.hauteur, this.symb};
	}
	public int getH(){
		return this.hauteur;
	}
	public int getS(){
		return this.symb;
	}
}
