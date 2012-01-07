package com.jeu.ihm;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe créant une fenêtre de dialogue affichant un texte
 * @author Nicolas et Victor
 * @version 1.0
 */
public class InformationDialog extends JDialog {
	
	/**
	 * Constructeur de la fenêtre de dialogue
	 * @param parent La fenêtre parente
	 * @param message Le message à afficher
	 */
	public InformationDialog(JFrame parent, String message){
		super(parent, "Message d'information", true);
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
		this.add(new JLabel("   "+message), BorderLayout.CENTER);
		JButton b = new JButton("OK");
        b.addActionListener(new OKListener());
        this.add(b, BorderLayout.SOUTH);
        this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Ferme la fenêtre de dialogue
	 */
	public void destroyDialog(){
		this.dispose();
	}
	
	/**
	 * Classe permettant de récupérer le clic sur le JButton "Ok"
	 * @author Nicolas et Victor
	 * @version 1.0
	 */
    class OKListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            JButton b = (JButton) arg0.getSource();
            destroyDialog();
        }
    }
}
