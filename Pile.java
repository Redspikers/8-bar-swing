import java.util.ArrayList;

public class Pile {
	
	private static ArrayList<Carte> maPile = new ArrayList<Carte>();
	

	public Pile(){
		
	}
	
	public Carte getHautDePile(){
		if(maPile.size() > 0)
			return maPile.get(maPile.size()-1);
		System.out.println("La pile est vide et on demande le haut de pile.");
		System.exit(-1);
		return null;
	}
	public void empiler(Carte c){
		maPile.add(c);
	}


}
