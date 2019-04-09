import java.util.*;


class Joueur {

	protected Point position; // la position du joueur sur le labyrinthe
	protected String nom; // son nom
	protected char coul;  // la couleur du pion : 'j' pour jaune, 'b' pour bleu, 'v' pour vert, et 'r' pour rouge.
	// son paquet de tresors
	// (On pourrait definir une classe CarteTresor de maniere a avoir ici un 
	// paquet de cartes et non pas un paquet de tresors. 
	// On se contentera pour simplifier de modeliser le paquet de carte par 
	// un paquet de tresor, ce qui sera suffisant pour que le jeu fonctionne.)
	protected ArrayList<Tresor> paquet;
	// le plateau de jeu (il est partage entre tous les joueurs) 
	protected Plateau plateau; 
	protected int numJoueur;
	protected Point positionDepart;
	public String textePositionDepart;
	
	// intervalle des coordonnees
	final static String COORMIN = "a1";
	final static String COORMAX = "g7";
	
	/*
	 * Il faudra ecrire un constructeur qui sera appele dans creerJoueurs.
	 * Attention, lorqu'on donnera une position au joueur, il faudra mettre a jour
	 * la tuile sur laquelle il se place au depart. 
	 * jaune se met en 0,0
	 * vert en 0,6
	 * bleu en 6,6
	 * rouge en 6,0
	 */

	public Joueur(String nom,int numJoueur, char coul, Plateau plateau) {
		this.nom = nom;
		this.plateau = plateau;
		this.coul = coul;
		this.numJoueur = numJoueur;
		this.paquet = new ArrayList<Tresor>();
		// on donne une position au joueur selon son numero
		switch (coul) {
			case 'j' : // joueur numero 1
				position = new Point(0,0);
				positionDepart = new Point(0,0);
				textePositionDepart = "Retournez en haut a gauche";
				break;
			case 'v' : // joueur numero 2
				position = new Point(0,6);
				positionDepart = new Point(0,6);
				textePositionDepart = "Retournez en bas a gauche";
				break;
			case 'b' : // joueur numero 3
				position = new Point(6,6);
				positionDepart = new Point(6,6);
				textePositionDepart = "Retournez en bas a droite";
				break;
			case 'r' : // joueur numero 4
				position = new Point(6,0);
				positionDepart = new Point(6,0);
				textePositionDepart = "Retournez en haut a droite";
				break;
			default :
				position = null;
				break;
		}
		
		// on pose le pion du joueur sur la tuile de depart
		plateau.getTuile(position).poserPion(numJoueur);		
	}

	/*
	 * Implante un tour de jeu d'un joueur.
	 */
	public void joue(JeuDuLabyrinthe jdl){
		// a transformer.
		// Pour l'instant cette methode doit 
		// demander où le joueur  veut inserer la tuile sortie, et dans quel sens, et faire l'insertion 
		// de la tuile dans le labyrinthe si les coordonnees entrees sont correctes.
		// on ne s'occuppe pas de deplacer les pions, ni de prendre des tresors.

		int nbTour; // nombre de quarts de tours
		String coordonnees; // saisie du joueur
		Point destination; // la ou veut se deplacer le joueur
		int[] xy; // abscisse et ordonne sous forme d'indice
		
		//System.out.println("\n"+nom.toUpperCase()+" -> Joueur "+(numJoueur+1)+" -> Pion "+(numJoueur+1)+" :");
		
		if (!paquet.isEmpty()) {
			System.out.println("\n\tTresor a trouver : " + paquet.get(0) +"\n");
		}
		else { // sinon on lui dit de retourner a sa position initiale
			System.out.println("\nVous n'avez plus de tresors !!! Retournez en "+positionDepart+" pour gagner la partie !!!");
		}
		nbTour = Clavier.lireEntierDansIntervalle("Tourner la tuile combien de fois (quarts de tours, dans le sens des aiguilles d'une montre) ? ",0,3);

		// on fait tourner la tuile et on l'affiche
		plateau.getTuileSortie().tourne(nbTour);
		System.out.println("\tVotre tuile sortie : ");
		System.out.println(plateau.getTuileSortie() +"\n");

		// le joueur choisit ou inserer la tuile
		do {
			coordonnees = Coordonnees.lireCoordonnees("Inserer la tuile en : ",COORMIN,COORMAX);
			xy = Coordonnees.coordonneesEnIndice(coordonnees);
		// on insere la tuile dans le labyrinthe
		} while(!plateau.insererTuile(xy[0], xy[1], jdl));
		
		System.out.println(plateau);
		
		// le joueur choisit ou il veut se deplacer
		System.out.println("\nSaisir votre position actuelle pour rester a la meme place.");
		do {
			if (!paquet.isEmpty()) {
				System.out.println("\n\tTresor a trouver : " + paquet.get(0));
			}
			System.out.println("\tPosition actuelle : "+position);
			coordonnees = Coordonnees.lireCoordonnees("Deplacer votre pion en : ",COORMIN,COORMAX);
			xy = Coordonnees.coordonneesEnIndice(coordonnees);
			destination = new Point(xy[0], xy[1]);
		// on deplace le pion du joueur
		} while(!plateau.deplacerPion(position,destination,numJoueur));
		position = destination; // la position du joueur change
		
		System.out.println(plateau);
		
		if (!paquet.isEmpty()) { // s'il reste des tresors au joueurs
			// si la tuile, ou se trouve maintenant le joueur, contient le tresor d'indice 0, on le retire de la tuile
			if (plateau.getTuile(xy[0], xy[1]).retirerTresor(paquet.get(0))) {
				// on retire le tresor d'indice 0 du paquet
				System.out.println("\tVous avez ramasse le tresor : " + paquet.remove(0));
				System.out.println("\tIl vous reste " + paquet.size() + " tresors a ramasser.");
			}
		}

		//Console.readLine("\nAppuyez sur ENTREE...");
		System.out.println("\nAppuyez sur ENTREE...");
		Clavier.lireString();
	}

	/*
	 * 	 verifie si le joueur a gagne.
	 */
	public boolean aGagne() {
		// le joueur a gagne si son paquet de cartes est vide et s'il se trouve sur la position de depart
		return (paquet.isEmpty() && position.equals(positionDepart));

	}

	/*
	 * permet d'afficher les informations du joueur
	 */
	public String toString(){
		return "Joueur n° "+(numJoueur+1)+" ; Nom : "+nom+" ; Couleur : "+coul+" ; Position : "+position+" ; Taille du paquet : "+paquet.size()+"\n";
	}

	/*
	 * 	retourne le plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**********************************METHODES AJOUTEES**********************/
	
	public Point lirePosition() {
	// retourne la position du joueur
		return position;
	}
	
	public void ajouterTresor(Tresor t) {
	// ajoute un tresor a la liste des tresors du joueur
		this.paquet.add(t);
	}
	
	public void afficherPaquet() {
	// affiche le paquet de tresors d'un joueur
		System.out.println("Votre paquet de cartes :");
		for (int i = 0 ; i < paquet.size() ; i++) {
			System.out.println("\tCarte n°" + (i+1) + ": " + paquet.get(i).getNom()+"\t");
		}
		System.out.println();
	}
	
	public String getNom() {
		return nom;
	}

	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public int getTaillePaquet() {
		return paquet.size();
	}
	
	public char getCouleur() {
		return coul;
	}
	
	public Tresor getTresorATrouver(){
		Tresor t = null;
		if (!paquet.isEmpty()) {
			t = paquet.get(0);
		}
		return t;
	}
	
	
	public void aTrouveTresor() {
	// retire le premier tresor de la liste si le paquet n'est pas vide
		if (!paquet.isEmpty()) {
			paquet.remove(0);
		}
	}
	
	
}
