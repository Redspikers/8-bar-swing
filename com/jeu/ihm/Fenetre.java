package com.jeu.ihm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


import com.jeu.core.Carte;
import com.jeu.core.CarteSpeciale;
import com.jeu.core.Joueur;
import com.jeu.core.Partie;


/**
 * Classe créant la fenêtre principale du jeu où sera dessinée la table de jeu
 * @author Nicolas et Victor
 * @version 1.0
 */
public class Fenetre extends JFrame implements Observer, Enums_Interfaces.Hauteur, Enums_Interfaces.Messages, Enums_Interfaces.Symbole{
	
	public static final int TEMPS_VIRTUEL = 10; //Temps avant de laisser l'IA jouer.
	private Partie maPartie = null;
	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel conteneurTotal = new JPanel(new BorderLayout());
	private JPanel conteneurMilieu = new JPanel(gbl);
	private JPanel conteneurStatusBar = new JPanel();
	private ArrayList<BoutonCarte>       lBoutonCarte = new ArrayList<BoutonCarte>(); 
	private ArrayList<BoutonCarteJoueur> lBoutonCarteJoueur = new ArrayList<BoutonCarteJoueur>(); 
	private ArrayList<JLabel>            lJLabel = new ArrayList<JLabel>(); 
	private BoutonCarte boutonPile;
	private BoutonCarteDos boutonPioche;
	private JLabel statusBarLabel;
	private JLabel label_jeuJoueur = new JLabel();
	
	/**
	 * Constructeur appelé pour créer la fenêtre principale
	 */
	public Fenetre(){
		
		this.setTitle("Jeu de cartes");
		this.setMinimumSize(new Dimension(400, 400));
		this.setSize(1020, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowsListenerCustom(this));
        this.setContentPane(conteneurTotal);
        this.setIconImage(new ImageIcon(this.getClass().getResource("logo.png")).getImage()); 
        
        this.initPartie();
		
		
        //Création de la barre de menu.
        JMenuBar menuBar = new JMenuBar();
        JMenu menu, submenu;
        JMenuItem menuItem;
        
        //Menu Actions
        menu = new JMenu("Actions");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Pour effectuer une action sur la partie");
        menuBar.add(menu);

        //Les items du menu Actions
        menuItem = new JMenuItem("Nouvelle partie", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Lance une nouvelle partie");
        menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) { 
				initPartie();
				updateGraphique();
				lancerCPU();}
		});
        menu.add(menuItem);

        menuItem = new JMenuItem("Sauvegarder & Quitter",
                                 new ImageIcon("images/save.png"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) { 
				getPartie().sauvegarderPartie();
				destroyFenetre();}
		});
        menu.add(menuItem);
        
        menu.addSeparator();
        menuItem = new JMenuItem("Quitter sans sauvegarder",
                new ImageIcon("images/exit.png"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) { destroyFenetre(); }
		});
		menu.add(menuItem);

    
        

        //Menu Aide
        menu = new JMenu("Aide");
        menu.setMnemonic(KeyEvent.VK_I);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("A Propos");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) { 
		new FenetreAbout(getFenetre());}
		});
		menu.add(menuItem);
		
		gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.boutonPile = new BoutonCarte(maPartie.getPile().getHautDePile());
        conteneurMilieu.add(boutonPile, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 30, 10);
        conteneurMilieu.add(new JLabel("Pile"), gbc);
        
        gbc.insets = new Insets(10, 10, 10, 30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.boutonPioche = new BoutonCarteDos();
        this.boutonPioche.addActionListener(new BoutonPiocheListener());
        conteneurMilieu.add(boutonPioche, gbc);
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 10, 30, 10);
        conteneurMilieu.add(new JLabel("Pioche"), gbc);
        gbc.gridx = 2;
        gbc.gridwidth=8;
        gbc.insets = new Insets(0, 10, 30, 10);
        conteneurMilieu.add(label_jeuJoueur, gbc);
        gbc.gridwidth=1;

        
        statusBarLabel = new JLabel("Bienvenue !!");
        conteneurStatusBar.add(statusBarLabel);
        conteneurTotal.add(menuBar, BorderLayout.NORTH);
        conteneurTotal.add(conteneurMilieu,BorderLayout.WEST);
        conteneurTotal.add(conteneurStatusBar,BorderLayout.PAGE_END);
        this.updateGraphique();
        this.setVisible(true);

        this.lancerCPU();

		
	}
	
	/**
	 * Initialise la Partie
	 */
	public void initPartie(){

		
		
		// Lecture de l'objet sauvegardé s'il existe
		FileInputStream f_in = null;
		ObjectInputStream obj_in = null;
		try {
			f_in = new FileInputStream("sauvegarde.8bar");
			obj_in = new ObjectInputStream (f_in);
			maPartie = (Partie) obj_in.readObject();
			File fichier_sauvegarde = new File("sauvegarde.8bar");
			fichier_sauvegarde.delete(); 
		} catch (Exception e) {	
			FenetreDebutPartie maFenetreDebut = new FenetreDebutPartie(this);	
			maPartie = new Partie(maFenetreDebut.getNbHumains(), maFenetreDebut.getNbJoueursTotal());
		}
		
		
		
        int i = 0;
        BoutonCarteJoueur boutonJoueur;
		for(Joueur jo : maPartie.getJoueurs()){//On ecrit les differents joueurs
			jo.addObserver(this);
		    gbc.gridx = i;
	        gbc.gridy = 0;
	        gbc.gridheight = 1;
	        gbc.gridwidth = 1;
	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.insets = new Insets(10, 10, 10, 10);
	        
	        
	        if (i < lBoutonCarteJoueur.size()){
            	lBoutonCarteJoueur.get(i).setJoueurID(i);
            	boutonJoueur = lBoutonCarteJoueur.get(i);
            	lJLabel.get(i).setText("Joueur " + i + " (8)");
            }else{
            	boutonJoueur = new BoutonCarteJoueur(i);
            	boutonJoueur.addActionListener(new BoutonCarteListener());
            	lBoutonCarteJoueur.add(boutonJoueur);
            	lJLabel.add(new JLabel("Joueur " + i + " (8)"));
            }

	        boutonJoueur.addActionListener(new BoutonCarteJoueurListener());
	        conteneurMilieu.add(boutonJoueur, gbc);
	        gbc.gridy = 1;
	        gbc.insets = new Insets(0, 10, 30, 10);
	        lJLabel.add(new JLabel("Joueur " + i + " (8)"));
	        conteneurMilieu.add(lJLabel.get(i), gbc);
	        i++;
		}
		
		//On enleve les cartes représentants chaque joueur de l'ancienne partie,
		//de l'interface graphique, s'il y en avait plus que pour la partie actuelle
        for(;i<lBoutonCarteJoueur.size(); i++){
        	conteneurMilieu.remove(lBoutonCarteJoueur.get(i));
        	conteneurMilieu.remove(lJLabel.get(i));
        }
		
	}
	
	/**
	 * Dessine sur le table de jeu la main passée en paramètre
	 * @param lCartes Cartes à dessiner sur le table de jeu
	 */
	public void afficherMain(ArrayList<Carte> lCartes){
        gbc.insets = new Insets(10, 10, 10, 0);
        int i=0;
        BoutonCarte b;
        gbc.gridy = 2;
        for(Carte maCarte : lCartes){
        	if(i>=8){
        	   gbc.gridy = 4;
        	   gbc.gridx = i-8;
           } else
        	   gbc.gridx = i+2;
           
           System.out.print(maCarte.getH()+"-");

            
            //Soit on modifie une carte deja affichée (et instanciée), soit on l'instancie.
            if (i < lBoutonCarte.size()){
            	lBoutonCarte.get(i).setCarte(maCarte);
            	b = lBoutonCarte.get(i);
            }else{
            	b = new BoutonCarte(maCarte);
            	b.addActionListener(new BoutonCarteListener());
            	lBoutonCarte.add(b);
            }
            conteneurMilieu.add(b, gbc);
            i++; 
        }
        
        //On vide les cartes du joueur précédent de l'interface graphique
        //S'il en avait plus que le joueur actuel.
        gbc.gridy = 4;
        for(;i<lBoutonCarte.size(); i++){
        	if(i>=8){
         	   gbc.gridy = 4;
         	   gbc.gridx = i-8;
            } else
         	   gbc.gridx = i+2;
            conteneurMilieu.remove(lBoutonCarte.get(i));
            
        }
	}
	
	public void update(Observable o, Object arg){
		super.repaint();
		if(!maPartie.getEnMarche())
			return;
		
		Joueur jCourant;
		System.out.println(maPartie.getPile().getHautDePile().getH());
		maPartie.gestionDuJeu();
		jCourant = maPartie.getJoueurCourant();
		this.updateGraphique();

		
		if(maPartie.isVictoire()){
			new FenetreFinPartie(getFenetre());
			updateGraphique();
			return;
		}else if(jCourant instanceof com.jeu.core.Virtuel){
			try {
				Thread.sleep(TEMPS_VIRTUEL);
			} catch (InterruptedException e) {e.printStackTrace();}
			jCourant.notifyVue();
		} else if(!jCourant.isEtat()){
			new InformationDialog(getFenetre(), "Le Joueur "+maPartie.getNumJoueurCourant()+" doit passer son tour :'(");
			jCourant.notifyVue();
		}
		
				
	}
	
	/**
	 * Met à jour la partie graphique de l'application
	 */
	public void updateGraphique(){
		Joueur jCourant = this.maPartie.getJoueurCourant();
		Joueur jSuivant = this.maPartie.getJoueurSuivant();
		this.updateStatusBar();
		//On met a jour le nombre de carte de chaque joueur
		lJLabel.get(jCourant.getId()).setText("Joueur " + jCourant.getId() + " (" + jCourant.getNbCartesJeu() + ")");
		lJLabel.get(jSuivant.getId()).setText("Joueur " + jSuivant.getId() + " (" + jSuivant.getNbCartesJeu() + ")");
		//On précise quel jeu de quel joueur on est en train de voir
        label_jeuJoueur.setText("Jeu du joueur "+jCourant.getId());
		updateJoueursCouleurs();//On met en rouge le joueur qui jouera
		this.boutonPile.setCarte(maPartie.getPile().getHautDePile()); //on actualise l'image de la carte sur la pile
		afficherMain(jCourant.getCartes());//on affiche le jeu
		super.repaint();
	}
	
	/**
	 * Met à jour la barre de status en bas de la fenêtre
	 */
	public void updateStatusBar(){
		String couleurDemandee = "Couleur demandée : ";
		Carte hautDePile = maPartie.getPile().getHautDePile();
		if(hautDePile.getH() == 8 || hautDePile.getS() == JOKER)
			couleurDemandee += Symbole[((CarteSpeciale)hautDePile).getSymboleChoisi()];
		else
			couleurDemandee += Symbole[hautDePile.getS()];
		statusBarLabel.setText(couleurDemandee+" | "+maPartie.getMessageActuel());//+" | "+statusBarLabel.getText()
	}
	
	/**
	 * Met à jour la couleur du numéro du Joueur qui est en train de jouer
	 */
	public void updateJoueursCouleurs(){		
		//On remet d'abord en noir tout les labels des joueurs
		for (JLabel label : lJLabel)
			label.setForeground(Color.BLACK);
		
		//On colore en rouge le joueur qui doit jouer
		lJLabel.get(maPartie.getNumJoueurCourant()).setForeground(Color.red);
	}
	
	/**
	 * Méthode appelée lorsque le Joueur qui doit joué n'est pas Humain
	 */
	public void lancerCPU(){
		//Si c'est au CPU de commencer, on attend aucun evenement graphique !
        if(maPartie.getJoueurCourant() instanceof com.jeu.core.Virtuel){
			try {
				Thread.sleep(TEMPS_VIRTUEL);
			} catch (InterruptedException e) {e.printStackTrace();}
			maPartie.getJoueurCourant().notifyVue();
        }
	}
	
	/**
	 * Getter de Fenetre
	 * @return La fenêtre de jeu courante
	 */
	public Fenetre getFenetre(){
		return this;
	}
	
	/**
	 * Quitte la fenêtre de jeu
	 */
	public void destroyFenetre(){
		this.dispose();
	}
	
	/**
	 * Getter de la Partie associée au jeu en cours
	 * @return La Partie associée au jeu en cours
	 */
	public Partie getPartie(){
		return maPartie;
	}
	
    class BoutonCarteListener implements ActionListener{
   	 	
    	//Lors d'un clic sur une carte du jeu d'un joueur.
        public void actionPerformed(ActionEvent arg0) {
        	//Carte sur laquelle on a cliqué
        	Carte c = ((BoutonCarte)arg0.getSource()).getCarte();
        	
        	System.out.println("Carte : " + c.getS() + "|" + c.getH());
        	
        	//Carte est jouable et il peut jouer ?
        	if(maPartie.getJoueurCourant().
        	jouerCarte(c, maPartie.getPile().getHautDePile(), maPartie.getNbAs())
        	&&
        	maPartie.getJoueurCourant().isEtat()){
        		//Une carte spéciale qui peut nous faire choisir la couleur ?
        		if (c.getH()==8 || c.getS()==JOKER){
        			CarteSpeciale cs = new CarteSpeciale(c.getH(), c.getS());
        			cs.setSymboleChoisi(new ChoixSymbole((JFrame)getFenetre()).getSymbole());
        			maPartie.analyserPassage(cs);
        		}else
        			maPartie.analyserPassage(c);
        		maPartie.getJoueurCourant().notifyVue();
        	}
        }
        
    }
    
    class BoutonPiocheListener implements ActionListener{
   	 	//Lors d'un clic sur la pioche
        public void actionPerformed(ActionEvent arg0) {
        	Carte hautDePile = maPartie.getPile().getHautDePile();
        	int nbAs = maPartie.getNbAs();
        	if(maPartie.getJoueurCourant().isJeuJouable(hautDePile, nbAs))
        		return;
        	//On le fait piocher que s'il n'a aucune carte jouable.
        	maPartie.analyserPassage(null);
        	maPartie.getJoueurCourant().notifyVue();
        }
        
    }
    
    class BoutonCarteJoueurListener implements ActionListener{
   	 	//Lors d'un clic sur un Joueur
        public void actionPerformed(ActionEvent arg0) {
        	int joueurID = ((BoutonCarteJoueur) arg0.getSource()).getJoueurID();
        	Joueur jCourant = maPartie.getJoueurCourant();
        	if(jCourant.getId() != joueurID){
        		// Code lorsqu'un joueur a denonce un autre joueur
        		System.out.println("Joueur " + jCourant.getId() + " denonce le Joueur " + joueurID);
        		if(maPartie.denoncier(jCourant.getId(), joueurID)){
        			new InformationDialog(getFenetre(), "Le Joueur "+ joueurID + " se prend deux cartes car il a ete denonce.");
        		}else{
        			new InformationDialog(getFenetre(), "Le Joueur "+ jCourant.getId() + " se prend deux cartes car il a denonce de travers.");
        		}
        		
        	}else{
        		// Code lorsqu'un joueur dit "Carte"
        		jCourant.direCarte();
        		maPartie.setMessageActuel(DIT_CARTE);
        	}
        	updateGraphique();
        }
        
    }

    /**
     * Méthode appelée lors du lancement du programme
     * @param args inutilisé
     */
	public static void main(String[] args) {
		new Fenetre();
	}

}
