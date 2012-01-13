package com.jeu.strategie;
import java.util.ArrayList;
import com.jeu.core.Carte;

/**
 * Permet d'attribuer la stratégie consistant se débarrasser des cartes au hasard
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class StrategieRandom implements Strategie, java.io.Serializable{
	
	public Carte choisirCarte(ArrayList<Carte> cartesJouables){
		return cartesJouables.get((int)(Math.random()*cartesJouables.size()));
	}
}
