package com.jeu.core;
import java.util.ArrayList;

public class Pile implements java.io.Serializable {
	
	public ArrayList<Carte> cPile = new ArrayList<Carte>();
	

	public Pile(){
		
	}
	
	public Carte getHautDePile(){
		if(cPile.size() > 0)
			return cPile.get(cPile.size()-1);
		System.out.println("La pile est vide et on demande le haut de pile.");
		System.exit(-1);
		return null;
	}
	public void empiler(Carte c){
		cPile.add(c);
	}


}
