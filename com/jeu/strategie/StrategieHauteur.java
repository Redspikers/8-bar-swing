package com.jeu.strategie;
import java.util.ArrayList;
import com.jeu.core.Carte;


/**
 * Permet d'attribuer la stratégie consistant se débarrasser des cartes de plus forte valeur en premier
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class StrategieHauteur implements Strategie, java.io.Serializable{
	
	public Carte choisirCarte(ArrayList<Carte> cartesJouables){
		Carte carteChoisie = cartesJouables.get(0);
		for(Carte c : cartesJouables){
			if (c.getH() > carteChoisie.getH())
				carteChoisie = c;
		}
		return carteChoisie;
	}
	
}