package com.jeu.ihm;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jeu.core.Carte;
import com.jeu.core.Joueur;
import com.jeu.core.Partie;

public class Fenetre extends JFrame{
	
	private Partie maPartie;
	private GridBagConstraints gbc = new GridBagConstraints();
	private BoutonCarte boutonPile;
	
	public Fenetre(){
		
		this.setTitle("Jeu de cartes");
		this.setMinimumSize(new Dimension(400, 400));
		this.setSize(800, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		/*
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		this.setLayout(new GridBagLayout());
		
		int nbVirtuels = 1;
		int nbJoueurs = 3;
		maPartie = new Partie(nbVirtuels, nbJoueurs);
		
		for(int i=0; i<nbJoueurs; i++){
	        gbc.gridx = i;
	        gbc.gridy = 0;
	        gbc.gridheight = 1;
	        gbc.gridwidth = 1;
	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.insets = new Insets(10, 10, 10, 10);
	        this.add(new BoutonCarteDos(), gbc);
	        gbc.gridy = 1;
	        gbc.insets = new Insets(0, 10, 30, 10);
	        this.add(new JLabel("Joueur " + i), gbc);
		}
		
		gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.boutonPile = new BoutonCarte(maPartie.getPile().getHautDePile());
        this.add(boutonPile, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 30, 10);
        this.add(new JLabel("Pile"), gbc);
        
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(new BoutonCarteDos(), gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 30, 10);
        this.add(new JLabel("Pioche"), gbc);
        	

        
        afficherMain(maPartie.getJoueurCourant().getCartes());	
		
        this.setVisible(true);
        
		
	}
	
	public void afficherMain(ArrayList<Carte> c){
        gbc.insets = new Insets(10, 10, 10, 10);
        int i=0;
        for(Carte maCarte : c){
            gbc.gridx = i;
            gbc.gridy = 4;
            BoutonCarte b = new BoutonCarte(maCarte);
            this.add(b, gbc);
            b.addActionListener(new BoutonCarteListener());
            i++;
        }
        
	}
	
	public void update(){
		System.out.println(maPartie.getPile().getHautDePile().getH());
		
		this.boutonPile.setCarte(maPartie.getPile().getHautDePile()); //on actualise l'image de la carte sur la pile
		maPartie.changerDeJoueur(); //On passe la main au joueur suivant
		
		//
		afficherMain(maPartie.getJoueurSuivant().getCartes());
		super.repaint();
		
	}
	
    class BoutonCarteListener implements ActionListener{
    	 
        public void actionPerformed(ActionEvent arg0) {
        	BoutonCarte b = (BoutonCarte) arg0.getSource();
        	System.out.println("Carte : " + b.getCarte().getS() + "|" + b.getCarte().getH());
        	if(maPartie.getJoueurCourant().jouerCarte(b.getCarte(), maPartie.getPile().getHautDePile(), maPartie.getNbAs())){
        		System.out.println("YEAH");
        		maPartie.analyserPassage(b.getCarte());
        		update();
        	}
        }
        
    }


	public static void main(String[] args) {
		new Fenetre();
	}

}
