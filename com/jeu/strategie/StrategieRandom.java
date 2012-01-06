package com.jeu.strategie;
import java.util.ArrayList;
import com.jeu.core.Carte;

//Préfere donner la carte avec la plus grande hauteur
public class StrategieRandom implements Strategie, java.io.Serializable{
	public Carte choisirCarte(ArrayList<Carte> cartesJouables){
		return cartesJouables.get((int)(Math.random()*cartesJouables.size()));
		
	}
}
