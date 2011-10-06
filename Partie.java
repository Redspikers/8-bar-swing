import java.util.ArrayList;


public class Partie implements Enums_Interfaces.Hauteur, Enums_Interfaces.Symbole {
	
	private static int NB_CARTES_PAR_JOUEUR = 8;
	private int nbJoueurs;
	private int nbHumains;
	private ArrayList<Joueur> mesJoueurs;
	private Pioche maPioche;
	private Pile maPile;
	
	public Partie(int nbJ, int nbH){
		this.nbJoueurs = nbJ;
		this.nbHumains = nbH;
		
		this.mesJoueurs = new ArrayList<Joueur>();
		
		// Création de la pioche
		this.maPioche = new Pioche();
		
		// Création de la pile
		this.maPile = new Pile();
		
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
		System.out.println("Bergère : "+traduireCarte(retournerBergere()));
		System.out.println("Haut de pile : "+traduireCarte(maPile.getHautDePile()));
		
		

		
		// Vérifications
	}
	
	public void gestionDuJeu(){		
		
	}

	public void distribuer(){
		for(Joueur monJoueur : mesJoueurs){
			for(int i=0; i<NB_CARTES_PAR_JOUEUR; i++){
				monJoueur.recevoirCarte(maPioche.piocherCarte());
				//System.out.println("Joueur " + this.id + " --> Reçu : " + maCarte.get()[0] + ":" + maCarte.get()[1]);
			}
		}

	}
	public Carte retournerBergere(){
		//Tant que la bergère est un joker,
		//on recréer une pioche et on reéssaye.
		
		Carte c = maPioche.piocherCarte();
		while (c.getS() == JOKER){
			this.maPioche = new Pioche();
			c = maPioche.piocherCarte();
		}
		maPile.empiler(c);
		return c;
	}
	public String traduireCarte(Carte c){
		//Exemple : pour c tel que c.hauteur = 11 et c.symb = 2
		//cette fonction retournera la chaine "valet de trèfle"
		//On pourra aussi la modifier pour retourner {"valet", "trèfle"}
		
		int[] haut_symb = c.get();
		//System.out.println(haut_symb[0] + "  " + haut_symb[1]);
		if (haut_symb[1] == JOKER)
			return Symbole[haut_symb[1]];
		else
			return Hauteur[haut_symb[0]]+ " de " + Symbole[haut_symb[1]];
		
	}

}






