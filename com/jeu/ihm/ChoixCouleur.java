package com.jeu.ihm;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.jeu.core.Carte;

public class ChoixCouleur extends JDialog{
    
    private int couleur;
    
    public ChoixCouleur(JFrame parent){
        super(parent, "Choix de la nouvelle couleur", true);
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
    
    public int getCouleur(){
        return couleur;
    }
	public void destroyDialog(){
		this.dispose();
	}
    
    
    class BoutonCarteListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            BoutonCarte b = (BoutonCarte) arg0.getSource();
            couleur = b.getCarte().getS();
            destroyDialog();
        }
    }

}