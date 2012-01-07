package com.jeu.ihm;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Classe permettant de créer un JButton ayant l'aspect d'une carte à jouer retournée
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class BoutonCarteDos extends JButton implements MouseListener{


	protected Image img;
	
	/**
	 * Constructeur permettant de créer une carte à jouer retournée
	 */
	public BoutonCarteDos(){
		img = new ImageIcon("cartes/back.png").getImage();
		this.addMouseListener(this);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(78, 106));
        this.setMinimumSize(new Dimension(78, 106));
        this.setMaximumSize(new Dimension(768, 1063));
	}
	
	/**
	 * Constructeur permettant de créer une carte à jouer retournée
	 * @param s Le texte du JButton
	 */
	public BoutonCarteDos(String s){
		super(s);
		img = new ImageIcon("cartes/back.png").getImage();
		this.addMouseListener(this);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(78, 106));
        this.setMinimumSize(new Dimension(78, 106));
        this.setMaximumSize(new Dimension(768, 1063));
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

