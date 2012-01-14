package com.jeu.ihm;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Classe créant un listener écoutant les évênements se déroulant sur une fenêtre
 * @author Nicolas et Victor
 * @version 1.0
 */
public class WindowsListenerCustom implements WindowListener{
	
	private Fenetre maFenetre;
	
	/**
	 * Constructeur du listener d'évênements
	 * @param maFenetre La fenêtre à écouter
	 */
	public WindowsListenerCustom(Fenetre maFenetre){
		this.maFenetre = maFenetre; 
	}

	public void windowActivated(WindowEvent e) {
		
	}

	public void windowClosed(WindowEvent e) {
		
	}

	/**
	 * Méthode appelée lorsque l'utilisateur souhaite fermer la fenêtre
	 */
	public void windowClosing(WindowEvent e) {
		// Appel de la fenêtre demandant la sauvegarde de la Partie
		FenetreSauvegardePartie f = new FenetreSauvegardePartie(maFenetre);
		if(f.getSauver())
			maFenetre.getPartie().sauvegarderPartie();
	}

	public void windowDeactivated(WindowEvent e) {
		
	}

	public void windowDeiconified(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
		
	}

	public void windowOpened(WindowEvent e) {
		
	}

}
