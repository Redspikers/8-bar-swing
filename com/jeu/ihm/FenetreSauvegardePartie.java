package com.jeu.ihm;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe créant une fenêtre demandant si le joueur souhaite sauvegarder la Partie
 * @author Nicolas et Victor
 * @version 1.0
 */
public class FenetreSauvegardePartie extends JDialog implements com.jeu.Enums_Interfaces.Hauteur{
	
	private static final long serialVersionUID = 1L;
	private boolean sauver = false;
    
	/**
	 * Consctructeur de la Fenêtre de sauvegarde de la Partie
	 * @param parent La fenêtre parente
	 */
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
        JButton bouton_sauver = new JButton("Sauver & quitter"); 
        bouton_sauver.setIcon(new ImageIcon("images/save.png"));
        this.add(bouton_sauver, gbc);
        gbc.gridx=1;
        JButton bouton_quitter = new JButton("Quitter sans sauvegarder");
        bouton_quitter.setIcon(new ImageIcon("images/exit.png"));
        this.add(bouton_quitter, gbc);
        bouton_sauver.addActionListener(new bouton_sauverListener());
        bouton_quitter.addActionListener(new bouton_quitterListener());
       
        this.pack();
        this.setVisible(true);
    }
	
    
	/**
	 * Ferme la fenêtre
	 */
	public void destroyDialog(){
		this.dispose();
	}	
	
	/**
	 * Ferme la fenêtre ainsi que la fenêtre de jeu
	 */
	public void destroyAll(){
		((Fenetre)this.getParent()).destroyFenetre();
	}
    
	/**
	 * Getter de sauver
	 * @return L'état de sauver
	 */
	public boolean getSauver() {
		return sauver;
	}

	/**
	 * Classe permettant de récupérer le clic sur le JButton "Quitter"
	 * @author Nicolas et Victor
	 * @version 1.0
	 */
	class bouton_quitterListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            destroyAll();
        }
    }
    
	/**
	 * Classe permettant de récupérer le clic sur le JButton "Sauver"
	 * @author Nicolas et Victor
	 * @version 1.0
	 */
	class bouton_sauverListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
        	sauver = true;
        	destroyAll();
        }
    }

}