
public class Partie {

	
	public Partie(){
		// Création de huit joueurs
		int i;
		Joueur mesJoueurs[ ] = new Joueur[8];
		mesJoueurs[0] = new Humain(0);
		mesJoueurs[1] = new Humain(1);
		for(i=2; i<8; i++){
			mesJoueurs[i] = new Virtuel(i);
		}
		
		// Création de la pioche
		Pioche maPioche = new Pioche(); 
		
		// Vérifications
		for(i=0; i<8; i++)	System.out.println("Joueur : " + mesJoueurs[i].getId() + " Etat : " + mesJoueurs[i].isEtat());
		for(i=0; i<8; i++){
			Carte maCarte = maPioche.piocherCarte();
			System.out.println("Carte : " + maCarte.get()[0] + " " + maCarte.get()[1]);
		}
	}


}
