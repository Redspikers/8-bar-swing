import javax.swing.JFrame;


public class Vue {
	
	public Vue(){
		JFrame fenetre = new JFrame();
		//Définit un titre pour votre fenêtre
        fenetre.setTitle("Ma première fenêtre java");
        //Définit une taille pour celle-ci ; ici, 400 px de large et 500 px de haut
        fenetre.setSize(400, 500);
        //Nous allons maintenant dire à notre objet de se positionner au centre
        fenetre.setLocationRelativeTo(null);
        //Terminer le processus lorsqu'on clique sur "Fermer"
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fenetre.setVisible(true);
	}
}
