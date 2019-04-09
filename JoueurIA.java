import java.util.ArrayList;
/*
bug facile : si le joueur est jaune, il restera toujours a la meme place, 
solution sortir les poins aleatoirement au lieu que ce soit dans l'ordre indice++
*/

class JoueurIA extends Joueur {
private int nd = 0;
	private ArrayList<Point> listePointInsertion;
	private ArrayList<Point> listePointTuiles;

	public JoueurIA(String nom,int numJoueur, char coul, Plateau plateau) {
		super(nom,numJoueur, coul, plateau);
		
		listePointInsertion = new ArrayList<Point>();
		listePointTuiles = new ArrayList<Point>();
		
		listePointInsertion.add(new Point(0,1));
		listePointInsertion.add(new Point(0,3));
		listePointInsertion.add(new Point(0,5));
		listePointInsertion.add(new Point(1,0));
		listePointInsertion.add(new Point(3,0));
		listePointInsertion.add(new Point(5,0));
		listePointInsertion.add(new Point(1,6));
		listePointInsertion.add(new Point(3,6));
		listePointInsertion.add(new Point(5,6));
		listePointInsertion.add(new Point(6,1));
		listePointInsertion.add(new Point(6,3));
		listePointInsertion.add(new Point(6,5));		
		
		for (int x = 0 ; x < 7 ; x++) {
			for (int y = 0 ; y < 7 ; y++) {
				listePointTuiles.add(new Point(y,x));
			}
		}
	}
	
	public void joue(JeuDuLabyrinthe jdl) {
// //System.out.println("******************************* deplacement n:"+(++nd));
// //System.out.println(position);
		attendre();
		int nbTour;
		int indice;
		Point p;
		boolean tresorTrouve; // vrai lorsque les coordonnees du tresor cherche sont trouvees
		boolean tresorSurTuileSortie; // vaut vrai si le tresor cherche est sur la tuile sortie
		
		/**********TOURNER LA TUILE**********/
		nbTour = (int)(Math.random() * 4); // entre 0 et 4-1
		plateau.getTuileSortie().tourne(nbTour);
		jdl.getVuePlateau().actualiser();
		
		/**********INSERER LA TUILE**********/
		indice = (int)(Math.random() * listePointInsertion.size());
		p = listePointInsertion.get(indice);
		plateau.insererTuile(p.getX(),p.getY(),jdl);
		jdl.getVuePlateau().actualiser();
		
		/**********DEPLACER LE PION**********/
		
		
		if (plateau.getTuileSortie().getTresor() == null)
			tresorSurTuileSortie = false;
		else
			tresorSurTuileSortie = plateau.getTuileSortie().getTresor().equals(paquet.get(0));
		
		// s'il y a encore des tresors a trouver
		// et si le tresor qu'on cherche ne se trouve pas sur la tuile sortie
		if (!paquet.isEmpty() && !tresorSurTuileSortie) {
		// recuperer les coordonnees de la tuile ou se trouve le tresor qu'on cherche
		p = coordonneesTresor();
//System.out.println("tresor trouve en " + p);
			
			// si le joueur se deplace sur la tuile ou se trouve le tresor recherche
			if (plateau.deplacerPion(position,p,numJoueur)) {
//System.out.println("deplacement");
//System.out.println("tresor ramasse en " + p);
				plateau.getTuile(p).retirerTresor(paquet.get(0));
				paquet.remove(0);
				position = p;
			}
			else {
//System.out.println("chemin impossible vers tresor, random move");
				deplacementAleatoire();
//System.out.println("deplacement");
			}
		}
		// le paquet est vide ou le tresor qu'on cherche est sur la tuile sortie
		else  {
			// s'il n'y a plus de tresors a trouver
			if (paquet.isEmpty()) {
//System.out.println("plus de tresor a trouver");
				// si le joueur s'est deplace a la position de depart
				if (plateau.deplacerPion(position,positionDepart,numJoueur)) {
//System.out.println("deplacement");
//System.out.println("partie finie");
					position = p;
					// fin de la partie
				}
				// le joueur ne s'est pas deplace a sa position de depart
				else {
//System.out.println("chemin vers positionDepart impossible random move");
					deplacementAleatoire();
//System.out.println("deplacement");
				}
			}
			// si le paquet n'est pas vide alors le tresor recherche se trouve sur la tuile sortie
			else {
//System.out.println("tresor sur tuile sortie, random move");
				deplacementAleatoire();
//System.out.println("deplacement");
			}
		}
		jdl.getVuePlateau().actualiser();
//System.out.println(position);
//System.out.println("*******************************"+nd);
	}
	
	private void deplacementAleatoire() {
//System.out.println("DEBUT deplacementAleatoire");
		int indice;
		Point p;
		//ArrayList<Point> listePointEssayes = new ArrayList<Point>();
		
		// deplacement du point du joueur en fin de liste
		// ne sert a rien si c'est aleatoire
		// listePointTuiles.remove(position);
		// listePointTuiles.add(position);
		do {
			indice = (int)(Math.random() * listePointTuiles.size());
			p = listePointTuiles.get(indice);
			//listePointEssayes.add(p)
		//}while(!listePointEssayes.contains(p) && !plateau.deplacerPion(position,p,numJoueur));
		}while(!plateau.deplacerPion(position,p,numJoueur));
		position = p;
//System.out.println("deplacement en " + p);
//System.out.println("FIN deplacementAleatoire");
	}
	
	/*
	 * Retourne les coordonnees du tresor recherche
	 */
	private Point coordonneesTresor() {
	
		int indice = 0;
		Point p;
		boolean tresorTrouve;
		
		do {
			p = listePointTuiles.get(indice);
			
			tresorTrouve = (plateau.getTuile(p).getTresor() == null) ? false : plateau.getTuile(p).getTresor().equals(paquet.get(0));
			
			indice++;
			// le tresor est obligatoirement sur une tuile, la deuxieme condition n'est pas necessaire ?
			//}while(!tresorTrouve && indice < listePointTuiles.size());
		}while(!tresorTrouve);
		// a la sortie de la boucle, p contient les coordonnees du tresor recherche
		
		return p;
	}

	private static void attendre() {
		try {
			Thread.currentThread().sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
