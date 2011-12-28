package com.jeu.ihm;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.jeu.core.Carte;


public class BoutonCarte extends JButton implements MouseListener{

	protected Carte carte;
	protected Image img;
	
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
	
	public void setCarte(Carte c){
		this.carte = c;
		this.img = new ImageIcon("cartes/" + c.getS() + "-" + c.getH() + ".png").getImage();
	}
	
	public Carte getCarte(){
		return this.carte;
	}
	
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
    }

	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
