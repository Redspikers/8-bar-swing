package com.jeu.ihm;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jeu.core.Carte;
import com.jeu.core.Joueur;

public class FenetreSauvegardePartie extends JDialog implements Enums_Interfaces.Hauteur{
	
	private boolean sauver = false;
    
	public FenetreSauvegardePartie(JFrame parent){
        super(parent, "Sauvegarde", true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth= 2;
        this.add(new JLabel("Vous avez quitté la partie avant sa fin."), gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(new JLabel("Voulez-vous la sauvegarder ?"), gbc);
        gbc.gridy++;
        gbc.gridwidth= 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        JButton bouton_oui = new JButton("Sauver"); 
        this.add(bouton_oui, gbc);
        gbc.gridx=1;
        JButton bouton_non = new JButton("Quitter"); 
        bouton_oui.addActionListener(new bouton_ouiListener());
        bouton_non.addActionListener(new bouton_nonListener());
        this.add(bouton_non, gbc);
        this.pack();
        this.setVisible(true);
    }
	
    
	public void destroyDialog(){
		this.dispose();
	}	
	
	public void destroyAll(){
		((Fenetre)this.getParent()).destroyFenetre();
	}
    
	public boolean getSauver() {
		return sauver;
	}

	class bouton_nonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            destroyAll();
        }
    }
    
    class bouton_ouiListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	sauver = true;
        	destroyDialog();
        }
    }

}