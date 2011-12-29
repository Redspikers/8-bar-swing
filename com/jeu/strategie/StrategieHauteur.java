package com.jeu.strategie;
import java.util.ArrayList;
import com.jeu.core.Carte;

//Préfere donner la carte avec la plus grande hauteur
public class StrategieHauteur implements Strategie{
	public Carte choisirCarte(ArrayList<Carte> cartesJouables){
		Carte carteChoisie = cartesJouables.get(0);
		for(Carte c : cartesJouables){
			if (c.getH() > carteChoisie.getH())
				carteChoisie = c;
		}
		return carteChoisie;
		
	}
}
