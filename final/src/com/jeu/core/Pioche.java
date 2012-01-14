package com.jeu.core;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tas de cartes représentant la pioche
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class Pioche implements com.jeu.Enums_Interfaces.Symbole, java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Carte> cPioche = new ArrayList<Carte>();
	
	/**
	 * Constructeur de la pioche de la partie
	 * Permet de créer les cartes à jouer
	 */
	public Pioche(){
		
		// Création des deux paquets de cartes sans joker
		for(int j=0; j<104; j++){
			if (((j%52)%13)+2 == 8)
				cPioche.add(new CarteSpeciale(8, (j%52)/13));
			else
				cPioche.add(new Carte(((j%52)%13)+2, (j%52)/13));
		}
		
		// Création des jokers
		cPioche.add(new CarteSpeciale(15, JOKER));
		cPioche.add(new CarteSpeciale(15, JOKER));
		
		System.out.println("Taille de la pioche : " + cPioche.size());
		// Mélange du tas
		Collections.shuffle(cPioche);
	}

	/**
	 * Pioche une carte dans le haut du tas
	 * @return La carte piochée
	 */
	public Carte piocherCarte(){
		return cPioche.remove(0);
	}
	
	/**
	 * Permet de savoir si la pioche est bientôt vide
	 * @return Vrai si la pioche est presque vide
	 */
	public boolean isPiochePresqueVide(){
		return cPioche.size()<5;
	}
	
	/**
	 * Retourne la pile pour en faire une pioche
	 * @param pile La pile du jeu
	 */
	public void retournerPile(Pile pile){
		Carte hautDePile = pile.getHautDePile();
		while(pile.size()>0)
			cPioche.add(pile.depiler());
		pile.empiler(hautDePile);
	}

}
