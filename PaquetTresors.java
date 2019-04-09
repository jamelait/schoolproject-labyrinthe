import java.util.ArrayList;
import java.util.Arrays;
/*
 * PaquetTresors.java
 * Cette classe permet de gerer l'ensemble des tresors et de les distribuer aux joueurs.
 */
public class PaquetTresors {

	private Tresor[] tresors;
	
	public PaquetTresors (String[] nomDesTresors){
		tresors = new Tresor[nomDesTresors.length];
		for (int i = 0 ; i<tresors.length ; i++) {
			 tresors[i] = new Tresor(nomDesTresors[i]);
		}
	}
	
	public Tresor getTresor(int i){
		return tresors[i];
	}
	
	/*
	 * Repartit aleatoirement les tresors entre les joueurs.
	 * Methode appelee en debut de partie avant de demarrer le jeu.
	 */
	public void distribueTresors(Joueur[] joueurs){
		
		int indJoueur = 0;
		int indAleatoire;
		// cree une nouvelle liste avec le contenu du tableau de tresors
		ArrayList<Tresor> listeTresors = new ArrayList<Tresor>(Arrays.asList(tresors));
		
		  // joueurs[0].ajouterTresor(listeTresors.get(0));
		  // joueurs[1].ajouterTresor(listeTresors.get(1));

		// tant qu'il reste des tresors
		// on genere un indice aleatoire compris entre 0 et la taille de la liste - 1
		// on ajoute une carte tresor au paquet du joueur et on la supprime de la liste
 		while (!listeTresors.isEmpty() ) {
			indAleatoire=(int)(Math.random()*listeTresors.size());
			joueurs[indJoueur].ajouterTresor(listeTresors.get(indAleatoire));
			listeTresors.remove(indAleatoire);
			/*on passe au joueur suivant*/
			indJoueur++;
			if (indJoueur == joueurs.length) {
				indJoueur = 0;
			}
		}
	}
}
