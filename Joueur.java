import java.util.ArrayList;

public abstract class Joueur {
	
	private static int NB_CARTES_PAR_JOUEUR = 8 ;
	private int id;
	private boolean etat;
	
	private ArrayList<Carte> monJeu;
	
	public abstract boolean jouer();

	
	public Joueur(int id){
		this.id = id;
		this.etat = true;
		this.monJeu = new ArrayList<Carte>();
	}
	
	public void distribuer(Pioche pioche){
		for(int i=0; i< NB_CARTES_PAR_JOUEUR; i++){
			this.monJeu.add(pioche.piocherCarte());
		}

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
	
	public boolean jouerCarte() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean passerTour() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
