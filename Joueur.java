
public abstract class Joueur {
	
	private int id;
	private boolean etat;
	
	public abstract boolean jouerCarte();
	public abstract boolean passerTour();
	
	public Joueur(int id){
		this.id = id;
		this.etat = true;
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
	
}
