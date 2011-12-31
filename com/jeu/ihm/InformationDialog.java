package com.jeu.ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jeu.core.Carte;
import com.jeu.ihm.ChoixSymbole.BoutonCarteListener;

public class InformationDialog extends JDialog {
	public InformationDialog(JFrame parent, String message){
		super(parent, "Message d'information", true);
        this.setSize(400, 100);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
		this.add(new JLabel("   "+message), BorderLayout.CENTER);
		JButton b = new JButton("OK");
        b.addActionListener(new OKListener());
        this.add(b, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	public void destroyDialog(){
		this.dispose();
	}
	
    class OKListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            JButton b = (JButton) arg0.getSource();
            destroyDialog();
        }
    }
}
