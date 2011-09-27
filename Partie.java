
public class Partie {
	private int age;

	private char sexe;

	private double taille;

	public Partie(){
		this.age = 20;
		
		this.sexe = 'H';
		
		this.taille = 1.83;
		
	}
	public Partie(int age, char sexe, double taille){
		this.age = age;
		
		this.sexe = sexe;
		
		this.taille = taille;
	}
	public int getAge(){
		return this.age;
	}
	public double getTaille(){
		return this.taille;
	}
	public char getSexe(){
		return this.sexe;
	}
	public void setAge(int input){
		if ((input>2) && (input<114))
			this.age = input;
	}
	public void setTaille(double input){
		if ((input>1) && (input<2.50))
			this.taille = input;
	}
	public void setSexe(char input){
		if ((input=='M') || (input=='F'))
			this.sexe = input;
	}


}
