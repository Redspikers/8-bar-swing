import java.util.ArrayList;

public abstract class Joueur {
	
	private int id;
	private boolean etat;
	
	private ArrayList<Carte> monJeu;
	
	public abstract boolean jouer();

	
	public Joueur(int id){
		this.id = id;
		this.etat = true;
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
	
	public boolean jouerCarte(Carte c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean passerTour() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean recevoirCarte(Carte maCarte) {
		this.monJeu.add(maCarte);
		return true;
	}
	
}
