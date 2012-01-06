package com.jeu.ihm;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import com.jeu.core.Partie;

public class WindowsListenerCustom implements WindowListener{
	
	private Fenetre maFenetre;
	
	public WindowsListenerCustom(Fenetre maFenetre){
		this.maFenetre = maFenetre; 
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		FenetreSauvegardePartie f = new FenetreSauvegardePartie(maFenetre);
		if(f.getSauver())
			maFenetre.getPartie().sauvegarderPartie();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
