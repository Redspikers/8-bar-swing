
public class Virtuel extends Joueur {

	public Virtuel(int id) {
		super(id);
		
	}

	@Override
	public Carte jouer(Carte hautDePile, int nbAs) {
		// TODO Auto-generated method stub
		return new Carte(-1, -1);
	}

}
