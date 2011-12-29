package com.jeu.strategie;
import java.util.ArrayList;
import com.jeu.core.*;

public interface Strategie {
	public Carte choisirCarte(ArrayList<Carte> cartesJouables); 

}
