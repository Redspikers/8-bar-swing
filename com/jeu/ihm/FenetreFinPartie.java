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

public class FenetreFinPartie extends JDialog implements Enums_Interfaces.Hauteur{
    
	public FenetreFinPartie(JFrame parent){
        super(parent, "Fin de la partie", true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx=0;
        gbc.gridwidth= 2;
        int i=0, points;
        Joueur jGagnant = null;
        for(Joueur j: ((Fenetre)parent).getPartie().getJoueurs()){
        	
            points = j.calculPoints();
            this.add(new JLabel("Score du joueur "+j.getId()+" : "+points+" points"), gbc);
            if(points==0)
            	jGagnant = j;
            i++;
            gbc.gridy = i;
        }
        this.add(new JLabel("Le grand gagnant est le joueur n°"+jGagnant.getId()), gbc);
        
        gbc.gridy++;
        this.add(new JLabel("Voulez-vous rejouer avec nous ?"), gbc);
        gbc.gridy++;
        gbc.gridwidth= 1;
        JButton bouton_oui = new JButton("Oui"); 
        this.add(bouton_oui, gbc);
        gbc.gridx=1;
        JButton bouton_non = new JButton("NOOOOONNN !"); 
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
    
    class bouton_nonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            destroyAll();
        }
    }
    
    class bouton_ouiListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	((Fenetre)getParent()).initPartie();
        	destroyDialog();
        }
    }

}