import java.util.ArrayList;
import java.util.Collections;

public class Pioche implements Enums_Interfaces.Symbole{
	
	private ArrayList<Carte> maPioche = new ArrayList<Carte>();
	

	public Pioche(){
		
		// Création des deux paquets de cartes sans joker
		for(int j=0; j<104; j++){
			maPioche.add(new Carte(((j%52)%13)+2, (j%52)/13));
			//System.out.println(((j%52)%13)+2+" - " + (j%52)/13);
		}
		
		// Création des jokers
		maPioche.add(new Carte(15, JOKER));
		maPioche.add(new Carte(15, JOKER));
		
		System.out.println("Taille de la pioche : " + maPioche.size());
		// Mélange du tas
		Collections.shuffle(maPioche);
	}


	public Carte piocherCarte(){
		//System.out.println("Taille de la pioche : " + maPioche.size());
		return (Carte) maPioche.remove(0);
	}
	

}
