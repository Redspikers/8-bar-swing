package com.jeu.core;
public class CarteSpeciale extends Carte {
	
	private int couleurChoisie; 
	public CarteSpeciale(int h, int s){
		super(h, s);
	}
	public int getCouleurChoisie(){
		return this.couleurChoisie;
	}
	public void setCouleurChoisie(int couleurChoisie){
		this.couleurChoisie = couleurChoisie;
	}
}
