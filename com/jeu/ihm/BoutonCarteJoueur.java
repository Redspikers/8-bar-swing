package com.jeu.ihm;
import java.awt.event.MouseListener;

/**
 * Classe permettant de créer un JButton ayant l'aspect d'une carte à jouer retournée faisant référence à un Joueur
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class BoutonCarteJoueur extends BoutonCarteDos implements MouseListener{


	protected int joueurID;
	
	/**
	 * Constructeur permettant d'associer une image de carte à un Joueur
	 * @param joueurID Le joueur assicié au bouton
	 */
	public BoutonCarteJoueur(int joueurID){
		this.joueurID = joueurID;
	}
	
	/**
	 * Getter de JoueurID
	 * @return L'identifiant du Joueur associé au bouton
	 */
	public int getJoueurID(){
		return this.joueurID;
	}
	
	public void setJoueurID(int ID){
		this.joueurID = ID;
	}
}

