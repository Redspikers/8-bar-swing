package com.jeu.ihm;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class BoutonCarteJoueur extends BoutonCarteDos implements MouseListener{


	protected int joueurID;
	
	public BoutonCarteJoueur(int joueurID){
		this.joueurID = joueurID;
	}
	
	public int getJoueurID(){
		return this.joueurID;
	}
	
	public void setJoueurID(int ID){
		this.joueurID = ID;
	}
}

