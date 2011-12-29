package Enums_Interfaces;

public interface Messages {
	public final static String[] MESSAGE = {
		"", 
		"Pas de cartes jouables. Le joueur doit piocher et passer.", 
		"Le joueur doit passer son tour.",
		"Le joueur suivant pioche deux cartes.",
		"Le joueur suivant reçoit une carte du joueur qui vient de jouer",
		"Le joueur a changé la couleur",
		"On change le sens du jeu",
		"Le joueur suivant pioche deux fois le nombre d'as qui ont été joués.",
		"Le joueur a changé la couleur et le joueur suivant se prend 4 cartes."};
	
	public final static int AUCUNE_CARTE_JOUABLE  = 1;
	public final static int DOIT_PASSER           = 2;
	public final static int POSE_2 				  = 3;
	public final static int POSE_7      		  = 4;
	public final static int POSE_8     		      = 5;
	public final static int POSE_10 			  = 6;
	public final static int POSE_AS               = 7;
	public final static int POSE_JOKER            = 8;
}
