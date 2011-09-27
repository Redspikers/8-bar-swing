
public class Pioche {
	
	private Carte maPioche[ ] = new Carte[106];

	public Pioche(){
		// Création des deux paquets de cartes
		int h,s,i=0;
		for(h=2; h<14; h++){
			for(s=0; s<3; s++){
				maPioche[i] = new Carte(h,s);
				i++;
			}
		}
		// Création des jokers
		maPioche[i] = new Carte(15,4);
		i++;
		maPioche[i] = new Carte(15,4);
		
		// Mélange du tas à faire ici
		
	}


	public Carte piocherCarte() {
		
		return maPioche[0];
	}
	

}
