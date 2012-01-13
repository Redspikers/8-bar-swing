package com.jeu.ihm;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Classe permettant de créer une fenêtre "A propos"
 * @author Nicolas et Victor 
 * @version 1.0
 */
public class FenetreAbout extends JDialog {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur permettant de créer la fenêtre "A propos"
	 * @param parent La fenêtre parente
	 */
	public FenetreAbout(JFrame parent){
		super(parent, "A propos de 8-Bar Swing", true);
		this.setLocationRelativeTo(null);
		this.setSize(400, 400);
		this.centrer();
        this.setResizable(false);
        this.setUndecorated(true);
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx=0;
        gbc.gridy=0;
        
        JButton image_logo = new JButton(new ImageIcon("images/logo.png"));
        image_logo.setFocusPainted(false);
        image_logo.setBorderPainted(false);
        image_logo.setContentAreaFilled(false);
        this.add(image_logo, gbc);
        
        gbc.gridy++;
        JLabel label_nom = new JLabel("8-Bar Swing");
        label_nom.setFont(new Font("Serif", Font.PLAIN, 50));
		this.add(label_nom, gbc);
		gbc.gridy++;
		JLabel label_description = new JLabel("<html>8-Bar Swing est un logiciel codé en JAVA avec la bibliothèque graphique Swing." +
				"<br>C'est un jeu de huit américain créé par <br><table>" +
				"<tr><td>Nicolas LE MANCHET </td><td><i>nicolas.le.manchet@gmail.com</i></td></tr><br>" +
				"<tr><td>Victor MARTIN </td><td><i>victormartinfr@gmail.com</i></td></tr></table><br>" +
				"dans le cadre du cours de java de l'UTT (LO02) tutoré par Guillaume DOYEN et Baptiste CABLÉ.<br>" +
				"Le jeu est sous licence CeCILLv2"+
				"</html>");
		this.add(label_description, gbc);
		gbc.gridy++;
		JButton b = new JButton("OK");
        b.addActionListener(new OKListener());
        this.add(b, gbc);
        this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Centre la fenêtre sur l'écran
	 */
	public void centrer(){
		Dimension dim = getToolkit().getScreenSize();
		Rectangle abounds = this.getBounds();
		this.setLocation((dim.width - abounds.width) / 2,
		      (dim.height - abounds.height) / 2);
		      
	}
	
	/**
	 * Ferme la fenêtre
	 */
	public void destroyDialog(){
		this.dispose();
	}
	
	/**
	 * Classe permettant d'écouter les clics sur le bouton "OK"
	 * @author Nicolas et Victor 
	 * @version 1.0
	 */
	class OKListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            destroyDialog();
        }
    }
}
