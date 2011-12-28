package com.jeu.ihm;
import java.awt.Color;
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
import com.jeu.core.Messages;
import com.jeu.core.Partie;

public class Fenetre extends JFrame{
	
	private Partie maPartie;
	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagLayout gbl = new GridBagLayout();
	private ArrayList<BoutonCarte> lBoutonCarte = new ArrayList<BoutonCarte>(); 
	private ArrayList<JLabel> lJLabel = new ArrayList<JLabel>(); 
	private BoutonCarte boutonPile;
	private BoutonCarteDos boutonPioche;
	
	public Fenetre(){
		
		this.setTitle("Jeu de cartes");
		this.setMinimumSize(new Dimension(400, 400));
		this.setSize(1020, 550);
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
		this.setLayout(this.gbl);
		
		int nbHumains = 2;
		int nbJoueurs = 2;
		maPartie = new Partie(nbHumains, nbJoueurs);
		
		for(int i=0; i<nbJoueurs; i++){ //On ecrit les differents joueurs
	        gbc.gridx = i;
	        gbc.gridy = 0;
	        gbc.gridheight = 1;
	        gbc.gridwidth = 1;
	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.insets = new Insets(10, 10, 10, 10);
	        this.add(new BoutonCarteDos("texte"), gbc);
	        gbc.gridy = 1;
	        gbc.insets = new Insets(0, 10, 30, 10);
	        lJLabel.add(new JLabel("Joueur " + i));
	        this.add(lJLabel.get(i), gbc);
		}
		updateJoueursCouleurs();
		
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
        this.boutonPioche = new BoutonCarteDos();
        this.boutonPioche.addActionListener(new BoutonPiocheListener());
        this.add(boutonPioche, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 30, 10);
        this.add(new JLabel("Pioche"), gbc);
        	

        
        afficherMain(maPartie.getJoueurCourant().getCartes());	
		
        this.setVisible(true);
        
		
	}
	
	public void afficherMain(ArrayList<Carte> lCartes){
        gbc.insets = new Insets(0, 0, 0, 0);
        int i=0;
        BoutonCarte b;
        for(Carte maCarte : lCartes){
            gbc.gridx = i;
            gbc.gridy = 4;
        	System.out.print(maCarte.getH()+"-");

            
            //Soit on modifie une carte deja affichée (et instanciée), soit on l'instancie.
            if (i < lBoutonCarte.size()){
            	lBoutonCarte.get(i).setCarte(maCarte);
            	b = lBoutonCarte.get(i);
            }else{
            	b = new BoutonCarte(maCarte);
            	b.addActionListener(new BoutonCarteListener());
            	lBoutonCarte.add(b);
            }
            this.add(b, gbc);
            i++; 
        }
        for(;i<lBoutonCarte.size(); i++){
        	System.out.println("AZAZZAAAA");
	        gbc.gridx = i;
	        gbc.gridy = 4;
            this.remove(lBoutonCarte.get(i));
            
        }
	}
	
	
	public void update(){
		if(!maPartie.getEnMarche())
			return;
		super.repaint();
		System.out.println(maPartie.getPile().getHautDePile().getH());
		maPartie.gestionDuJeu();//On s'occupe de la mise a jour du modèle
		updateJoueursCouleurs();//On met en rouge le joueur qui jouera
		this.boutonPile.setCarte(maPartie.getPile().getHautDePile()); //on actualise l'image de la carte sur la pile
		afficherMain(maPartie.getJoueurCourant().getCartes());
		super.repaint();
		
	}
	
	public void updateJoueursCouleurs(){		
		//On remet d'abord en noir tout les labels des joueurs
		for (JLabel label : lJLabel)
			label.setForeground(Color.BLACK);
		
		//On colore en rouge le joueur qui doit jouer
		lJLabel.get(maPartie.getNumJoueurCourant()).setForeground(Color.red);
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
    
    class BoutonPiocheListener implements ActionListener{
   	 
        public void actionPerformed(ActionEvent arg0) {
        	for(Carte c : maPartie.getJoueurCourant().getCartes()){
        		//Si le joueur peut jouer au moins une carte, on ne l'autorise pas à piocher
        		if(maPartie.getJoueurCourant().jouerCarte(c, maPartie.getPile().getHautDePile(), maPartie.getNbAs()))
        			return;
        	}
        	//On le fait piocher.
        	maPartie.getJoueurCourant().recevoirCarte(maPartie.getPioche().piocherCarte());
        	update();
        }
        
    }


	public static void main(String[] args) {
		new Fenetre();
	}

}
