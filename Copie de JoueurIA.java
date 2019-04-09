import java.util.ArrayList;
/*
bug facile : si le joueur est jaune, il restera toujours a la meme place, 
solution sortir les poins aleatoirement au lieu que ce soit dans l'ordre indice++
*/

class JoueurIA extends Joueur {

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
				listePointTuiles.add(new Point(x,y));
			}
		}
	}
	
	public void joue(JeuDuLabyrinthe jdl) {

		int nbTour;
		int indice;
		Point p;
		boolean tresorTrouve; // vrai lorsque les coordonnees du tresor cherche sont trouvees
		boolean tresorSurTuileSortie; // vaut vrai si le tresor cherche est sur la tuile sortie
		
		// tourner la tuile
		nbTour = (int)(Math.random() * 4); // entre 0 et 4-1
		plateau.getTuileSortie().tourne(nbTour);
		jdl.getVuePlateau().actualiser();
		//attendre();
		
		// inserer la tuile
		indice = (int)(Math.random() * listePointInsertion.size());
		p = listePointInsertion.get(indice);
		plateau.insererTuile(p.getX(),p.getY(),jdl);
		jdl.getVuePlateau().actualiser();
		//attendre();
		
		tresorSurTuileSortie =  (plateau.getTuileSortie().getTresor() == null) ? false : plateau.getTuileSortie().getTresor().equals(paquet.get(0));
		
		// deplacer le pion
		// si le paquet n'est pas vide et si le tresor qu'on cherche ne se trouve pas sur la tuile sortie
		if (!paquet.isEmpty() && !tresorSurTuileSortie) {

			// recuperer les coordonnees de la tuile ou se trouve le tresor qu'on cherche
			indice = 0;
			do {
				p = listePointTuiles.get(indice);
				tresorTrouve = (plateau.getTuile(p).getTresor() == null) ? false : plateau.getTuile(p).getTresor().equals(paquet.get(0));
				indice++;
				// le tresor est obligatoirement sur une tuile, la deuxieme condition n'est pas necessaire ?
				//}while(!tresorTrouve && indice < listePointTuiles.size());
			}while(!tresorTrouve);
System.out.println("tresor trouve en " + p);
			// a la sortie de la boucle, p contient les coordonnees du tresor recherche
			// le tresor est obligatoirement trouve, la premiere condition n'est pas necessaire ?
			//if (tresorTrouve && plateau.deplacerPion(position,p,numJoueur)) {
			// si le joueur se deplace sur la tuile ou se trouve le tresor recherche
			if (plateau.deplacerPion(position,p,numJoueur)) {
				plateau.getTuile(p).retirerTresor(paquet.get(0));
				paquet.remove(0);
				position = p;
			}
			else {
				deplacementAleatoire();
			}
		}
		//le paquet est vide ou le tresor qu'on cherche est sur la tuile sortie
		else  {
			// si le paquet et vide
			if (paquet.isEmpty()) {
				// le joueur s'est deplace a la position de depart
				if (plateau.deplacerPion(position,positionDepart,numJoueur)) {
					// fin de la partie
				}
				// le joueur ne s'est pas deplace a sa position de depart
				else {
					deplacementAleatoire();
				}
			}
			// si le paquet n'est pas vide alors le tresor recherche se trouve sur la tuile sortie
			else {
				deplacementAleatoire();
			}
		}
		jdl.getVuePlateau().actualiser();
	}
	
	private void deplacementAleatoire() {
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
	}
	
	private static void attendre() {
		try {
			Thread.currentThread().sleep(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
