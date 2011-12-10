
public class Virtuel extends Joueur {

	public Virtuel(int id) {
		super(id);
		
	}

	@Override
	public Carte jouer(Carte hautDePile, int nbAs) {
		
		if(this.etat == false){
			return new Carte(-1, -1);
		}
		
		//Le joueur a-t-il au moins une carte jouable ?
		for (Carte c : monJeu){
			if(jouerCarte(c, hautDePile, nbAs)){
				return c;
			}
		}

		return new Carte(-1, -1);
	}

}
