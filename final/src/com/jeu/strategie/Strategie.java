package com.jeu.strategie;
import java.util.ArrayList;
import com.jeu.core.*;

/**
 * Interface permettant d'implémenter le stratégie des joueurs virtuels
 * @author Nicolas et Victor 
 * @version 1.0
 */
public interface Strategie {
	
	/**
	 * Choisi une carte parmis celles jouables
	 * @param cartesJouables Collection de cartes de la main du joueur virtuel qui sont jouables dans l'état actuel du jeu
	 * @return La carte qui sera posée sur la pile
	 */
	public Carte choisirCarte(ArrayList<Carte> cartesJouables); 

}
