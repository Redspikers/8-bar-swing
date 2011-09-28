import java.util.ArrayList;


public class Partie {
	
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
		
		//Création des joueurs et 
		//Distributions des cartes aux joueurs
		int i;
		for(i=0; i<this.nbJoueurs; i++){
			if(i<this.nbHumains)
				mesJoueurs.add(new Humain(i));
			else
				mesJoueurs.add(new Virtuel(i));
			mesJoueurs.get(i).distribuer(maPioche);
		}

		//ICI on va retourner la bergère.
		
		
		

		
		// Vérifications
	}
	public void gestionDuJeu(){		
		System.out.println("ez");
	}


}
