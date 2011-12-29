package com.jeu.core;
import java.util.ArrayList;

import com.jeu.strategie.*;
public class Virtuel extends Joueur {
	
	private Strategie strategie;
	public Virtuel(int id) {
		super(id);
		
	}

	@Override
	public Carte jouer(Carte hautDePile, int nbAs) {
		if(this.etat == false){
			return new Carte(-1, -1);
		}
		
		
		//On fait un sous-tableau de son jeu contenant les cartes jouables.
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		for (Carte c : monJeu){
			if(jouerCarte(c, hautDePile, nbAs))
				cartesJouables.add(c);
		}
		
		//Le joueur a-t-il au moins une carte jouable ?
		if(cartesJouables.size() == 0)
			return new Carte(-1, -1);
		
		//On applique la stratégie
		return this.strategie.choisirCarte(cartesJouables);
	}
	
	public void setStrategie(Strategie s){
		this.strategie = s;
	}

}
