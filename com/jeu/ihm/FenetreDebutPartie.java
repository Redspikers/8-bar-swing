package com.jeu.ihm;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe permettant de créer une fenêtre demandant de choisir les paramètres de la nouvelle Partie
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class FenetreDebutPartie extends JDialog{
    
    private int nbJTotal = 2;
    private int nbHumains = 2;
    private JComboBox comboJTotal;
    private JComboBox comboHumains;
    private static final String[] tabJTotal = {"2", "3", "4", "5", "6", "7", "8"};
    private static final String[] tabNbHumains = {"1", "0", "2", "3", "4", "5", "6", "7", "8"};
    
    /**
     * Constructeur affichant une fenêtre de configuration de la Partie
     * @param parent Fenêtre parente
     */
    public FenetreDebutPartie(JFrame parent){
        super(parent, "Choix des parametres", true);
        this.setSize(300, 170);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(new JLabel("Nombre de joueurs total "), gbc);
        gbc.gridx = 1;
        comboJTotal = new JComboBox(tabJTotal);
        this.add(comboJTotal, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new JLabel("Nombre d'humains "), gbc);
        gbc.gridx = 1;
        comboHumains = new JComboBox(tabNbHumains);
        this.add(comboHumains, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton b = new JButton("Valider"); 
        b.addActionListener(new JButtonListener());
        this.add(b, gbc);
       
        this.setVisible(true);
    }
    
    /**
     * Getter du nombre de Joueurs Humains séléctionné
     * @return Le nombre de Joueurs Humains
     */
    public int getNbHumains(){
        return nbHumains;
    }
    
    /**
     * Getter du nombre de Joueurs
     * @return Le nombre de Joueurs de la Partie
     */
    public int getNbJoueursTotal(){
    	return nbJTotal;
    }
    
    /**
     * Ferme la fenêtre de configuration
     */
	public void destroyDialog(){
		this.dispose();
	}
    
	/**
	 * Classe permettant de récupérer le clic sur le JButton
	 * @author Nicolas et Victor
	 * @version 1.0
	 */
	class JButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            nbJTotal = Integer.parseInt(tabJTotal[comboJTotal.getSelectedIndex()]);
            nbHumains = Integer.parseInt(tabNbHumains[comboHumains.getSelectedIndex()]);
            destroyDialog();
        }
    }

}