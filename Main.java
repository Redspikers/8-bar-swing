
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int entier = 5;
		String str ="HO HO HO !";
		Partie test = new Partie(18, 'M', 1.85);
		
		System.out.println(test.getSexe());
		test.setSexe('F');
		System.out.println(test.getSexe());
		
		System.out.println(test.getAge());
		test.setAge(115);
		System.out.println(test.getAge());
		
		System.out.println(test.getTaille());
		test.setTaille(2.49);
		System.out.println(test.getTaille());
		

		System.out.println(new Carte(11, 2)).get()[0]);

		

	}

}
