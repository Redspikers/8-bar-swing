


public class Humain extends Joueur {

	public Humain(int id) {
		super(id);
		
	}
	
	@Override
	public Carte jouer(Carte hautDePile, int nbAs) {
		int hauteur;
		int symbole;
		int choix;
		Carte c;
		
		do{
			System.out.print("Voulez-vous passer ? non(0)/oui(1) ");choix = in.nextInt();
			if (choix == 1)
				return new Carte(-1, -1);
			
			System.out.print("Entrez la hauteur : ");hauteur = in.nextInt();
			System.out.print("Entrez le symbole : ");symbole = in.nextInt();
			c = new Carte(hauteur, symbole);
			
		}while(!(inJeu(c) && jouerCarte(c, hautDePile, nbAs)));
		System.out.print("IL A TROUVE DANS LE JEU et c'est bon");
		return c;
	}

}
