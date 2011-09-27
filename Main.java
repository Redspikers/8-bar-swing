
public class Main implements Enums_Interfaces.Symbole, Enums_Interfaces.Hauteur{

	public static void main(String[] args) {
				
		// Création de huit joueurs
		int i;
		Joueur mesJoueurs[ ] = new Joueur[8];
		mesJoueurs[0] = new Humain(0);
		mesJoueurs[1] = new Humain(1);
		for(i=2; i<8; i++){
			mesJoueurs[i] = new Virtuel(i);
		}
		
		for(i=0; i<8; i++)	System.out.println("Joueur : " + mesJoueurs[i].getId());

		System.out.println("test : " + AS);

	}

}
