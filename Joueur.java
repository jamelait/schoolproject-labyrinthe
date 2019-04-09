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
	public void joue(JeuDuLabyrinthe jdl){}
	
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
