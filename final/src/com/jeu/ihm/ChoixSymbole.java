package com.jeu.ihm;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.jeu.core.Carte;

/**
 * Classe permettant de créer une fenêtre demandant de choisir le symbole de la prochaine carte
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class ChoixSymbole extends JDialog{
    
	private static final long serialVersionUID = 1L;
	private int symbole;
    
    /**
     * Constructeur permettant de créer la fenêtre
     * @param parent La fenêtre parente
     */
    public ChoixSymbole(JFrame parent){
        super(parent, "Choix du nouveau symbole", true);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        for(int i=0; i<4; i++){
            gbc.gridx = i;
            BoutonCarte b = new BoutonCarte(new Carte(14, i));
            b.addActionListener(new BoutonCarteListener());
            this.add(b, gbc);
        }
        this.setVisible(true);
    }
    
    /**
     * Getter de symbole
     * @return Le prochain symbole à jouer choisi par le joueur
     */
    public int getSymbole(){
        return symbole;
    }
    
    /**
     * Ferme la fenêtre
     */
	public void destroyDialog(){
		this.dispose();
	}
    
	/**
	 * Classe permettant de récupérer le symbole cliqué
	 * @author Nicolas et Victor
	 * @version 1.0
	 */
    class BoutonCarteListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            BoutonCarte b = (BoutonCarte) arg0.getSource();
            symbole = b.getCarte().getS();
            destroyDialog();
        }
    }

}