import java.util.ArrayList;


public class Partie {

	
	public Partie(){
		// Création de huit joueurs
		int i;
		ArrayList<Joueur> mesJoueurs = new ArrayList<Joueur>();
		mesJoueurs.add(new Humain(0));
		mesJoueurs.add(new Humain(1));
		for(i=2; i<8; i++){
			mesJoueurs.add(new Virtuel(i));
		}
		
		// Création de la pioche
		Pioche maPioche = new Pioche(); 
		
		// Vérifications
		System.out.println("Le premier joueur est : " + mesJoueurs.get(0).getId() + " avec un état " + mesJoueurs.get(0).isEtat());
		Carte maCarte = maPioche.piocherCarte();
		System.out.println("La première carte est : " + maCarte.get()[0] + " " + maCarte.get()[1]);
	}


}
