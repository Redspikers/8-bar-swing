import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public abstract class Joueur implements Enums_Interfaces.Hauteur, Enums_Interfaces.Symbole{
	
	private int id;
	protected boolean etat;
	private boolean disCarte;
	
	protected ArrayList<Carte> monJeu;
	
	public abstract Carte jouer(Carte hautDePile, int nbAs);
	public static Scanner in = new Scanner(System.in);
	
	public Joueur(int id){
		this.id = id;
		this.etat = true;
		this.disCarte = false;
		this.monJeu = new ArrayList<Carte>();
	}
	
	public int getId() {
		return id;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public boolean isEtat() {
		return etat;
	}
	
	public boolean jouerCarte(Carte c, Carte hautDePile, int nbAs) {
		int[] c_tab    = c.get();
		int[] pile_tab = hautDePile.get();
		
		if(c_tab[0] == pile_tab[0] || //si même hauteur
		  //si même couleur(symbole) et que le précédent joueur n'a pas posé d'as
		  (c_tab[1] == pile_tab[1] && nbAs == 0) || 
		  //Si on veut jouer un 8 ou joker derriere autre chose qu'un as
		  ((c_tab[0]==8 || c_tab[1]==JOKER) && pile_tab[0] != AS))
			return true;
		return false;
	}
	
	public boolean recevoirCarte(Carte maCarte) {
		this.monJeu.add(maCarte);
		return true;
	}
	
	public Carte donnerCarte() {
		Random r = new Random();
		return this.monJeu.remove(r.nextInt(monJeu.size()));
	}
	
	public Carte donnerCarte(Carte c) {
		for (Carte c2 : monJeu){
			if (java.util.Arrays.equals(c.get(), c2.get())){
				monJeu.remove(c2);
				break;
			}
		}
		return c;
	}
	
	public void afficherJeu(){
		//Affiche le jeu du joueur.
		for(Carte c : monJeu){
			System.out.print(traduireCarte(c)+", ");
		}
		System.out.print("\n");
	}
	
	protected boolean inJeu(Carte c){
		//La carte c est-il dans monJeu ?
		//C à dire : Existe-t-il une carte dans monJeu
		//qui a la même hauteur et le même symbole que c ?
		
		for (Carte c2 : monJeu){
			//System.out.println(traduireCarte(c)+" - "+traduireCarte(c2));
			if (java.util.Arrays.equals(c.get(), c2.get()))
				return true;
		}
		return false;
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
