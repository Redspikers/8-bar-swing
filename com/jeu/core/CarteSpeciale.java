package com.jeu.core;
public class CarteSpeciale extends Carte {
	
	private int symboleChoisi; 
	public CarteSpeciale(int h, int s){
		super(h, s);
	}
	public int getSymboleChoisi(){
		return this.symboleChoisi;
	}
	public void setSymboleChoisi(int symboleChoisi){
		this.symboleChoisi = symboleChoisi;
	}
}
