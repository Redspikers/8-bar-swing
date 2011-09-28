import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	
	private ArrayList<Carte> maPioche = new ArrayList<Carte>();
	

	public Pioche(){
		
		// Création des deux paquets de cartes sans joker
		int h,s,i=0;
		for(h=2; h<14; h++){
			for(s=0; s<3; s++){
				maPioche.add(new Carte(h,s));
				i++;
			}
		}
		
		// Création des jokers
		maPioche.add(new Carte(15,4));
		i++;
		maPioche.add(new Carte(15,4));
		
		// Mélange du tas
		Collections.shuffle(maPioche);
	}


	public Carte piocherCarte() {
		
		return (Carte) maPioche.get(0);
	}
	

}
