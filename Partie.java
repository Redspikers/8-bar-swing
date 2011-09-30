import java.util.ArrayList;


public class Partie {
	
	private static int NB_CARTES_PAR_JOUEUR = 2 ;
	private int nbJoueurs;
	private int nbHumains;
	private ArrayList<Joueur> mesJoueurs;
	private Pioche maPioche;
	
	public Partie(int nbJ, int nbH){
		this.nbJoueurs = nbJ;
		this.nbHumains = nbH;
		
		this.mesJoueurs = new ArrayList<Joueur>();
		
		// Création de la pioche
		this.maPioche = new Pioche();
		
		//Création des joueurs 
		int i;
		for(i=0; i<this.nbJoueurs; i++){
			if(i<this.nbHumains)
				mesJoueurs.add(new Humain(i));
			else
				mesJoueurs.add(new Virtuel(i));
		}
		
		//Distributions des cartes aux joueurs
		distribuer();

		//ICI on va retourner la bergère.
		
		
		

		
		// Vérifications
	}
	
	public void gestionDuJeu(){		
		
	}

	public void distribuer(){
		for(Joueur monJoueur : mesJoueurs){
			for(int i=0; i<NB_CARTES_PAR_JOUEUR; i++){
				monJoueur.recevoirCarte(maPioche.piocherCarte());
			}
		}

	}

}
