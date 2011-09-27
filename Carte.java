
public class Carte {
	//2, 3, 4, ..., 14
	private int hauteur;
	
	//0, 1, 2 et 3 = Pique, carreau, trèfle et coeur
	private int symb;
	

	public Carte(){
		this.hauteur = 2;
		this.symb = 0;
	}
	public Carte(int h, int s){
		this.hauteur = h;
		this.symb = s;
	}
	
	public int[] get(){
		return new int[]{this.hauteur, this.symb};
	}
}
