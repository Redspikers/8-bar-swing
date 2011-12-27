import java.util.ArrayList;
import java.util.Collections;

public class Pioche implements Enums_Interfaces.Symbole{
	
	private ArrayList<Carte> cPioche = new ArrayList<Carte>();
	

	public Pioche(){
		
		// Création des deux paquets de cartes sans joker
		for(int j=0; j<104; j++){
			if (((j%52)%13)+2 == 8)
				cPioche.add(new CarteSpeciale(8, (j%52)/13));

			else
				cPioche.add(new Carte(((j%52)%13)+2, (j%52)/13));
			//System.out.println(((j%52)%13)+2+" - " + (j%52)/13);
		}
		
		// Création des jokers
		cPioche.add(new CarteSpeciale(15, JOKER));
		cPioche.add(new CarteSpeciale(15, JOKER));
		
		System.out.println("Taille de la pioche : " + cPioche.size());
		// Mélange du tas
		Collections.shuffle(cPioche);
	}


	public Carte piocherCarte(){
		//System.out.println("Taille de la pioche : " + maPioche.size());
		return (Carte) cPioche.remove(0);
	}
	

}
