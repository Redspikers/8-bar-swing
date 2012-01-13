package com.jeu.ihm;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.jeu.core.Carte;

/**
 * Classe permettant de créer un JButton ayant l'aspect d'une carte à jouer
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class BoutonCarte extends JButton implements MouseListener{

	private static final long serialVersionUID = 1L;
	protected Carte carte;
	protected Image img;
	
	/**
	 * Constructeur du bouton
	 * @param c La carte à afficher sur le bouton
	 */
	public BoutonCarte(Carte c){
		this.addMouseListener(this);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(78, 106));
        this.setMinimumSize(new Dimension(78, 106));
        this.setMaximumSize(new Dimension(768, 1063));
        
        this.setCarte(c);
	}
	
	
	/**
	 * Setter de la Carte associée
	 * @param c La Carte à associer au bouton
	 */
	public void setCarte(Carte c){
		this.carte = c;
		this.img = new ImageIcon("images/cartes/" + c.getS() + "-" + c.getH() + ".png").getImage();
	}
	
	/**
	 * Getter de la Carte associée au bouton
	 * @return La Carte associée au bouton
	 */
	public Carte getCarte(){
		return this.carte;
	}
	
    /**
     * Dessine le composant graphique
     */
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
    }

	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	public void mouseExited(MouseEvent arg0) {
		
		
	}

	
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
		
	}
	
}
