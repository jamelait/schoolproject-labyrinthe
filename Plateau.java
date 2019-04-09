import java.util.ArrayList;

public class Plateau {

	// le labyrinthe = tableau a deux dimensions de tuiles
	private Tuile[][] labyrinthe;

	// la tuile a inserer dans le labyrinthe
	private Tuile tuileSortie;

	private ArrayList<Point> tuileInserable;
	
	/*
	 * initialisation du plateau avec une configuration fixe, ce qui est plus pratique
	 * pour tester le programme. Il faudra penser plus tard a rendre cette 
	 * configuration aleatoire.
	 */
	public Plateau(PaquetTresors tresors){
		labyrinthe= new Tuile[7][7];
		placerTuileFixe(tresors);
		ArrayList<Tuile> tuileMobiles=creerTuileMobiles(tresors);
		tuileSortie=placerTuilesMobiles(tuileMobiles);
		
		tuileInserable = genererListeTuilesInserables();
	}

	/*
	 *  creation et placement des tuiles fixes sur le plateau
	 */
	public void placerTuileFixe(PaquetTresors tresors){
			Tuile t;

			// creation et placement des coins
			t=new Tuile(1,null);labyrinthe[0][0]=t;
			t=new Tuile(1,null);t.tourne(1);labyrinthe[6][0]=t;
			t=new Tuile(1,null);t.tourne(2);labyrinthe[6][6]=t;
			t=new Tuile(1,null);t.tourne(3);labyrinthe[0][6]=t;

		/*on cree les 12 tuiles carrefour (type 3) fixes elles ont toutes un tresor
		 *  et on les place sur le labyrinthe*/
			t=new Tuile(3,tresors.getTresor(0));t.tourne(0);labyrinthe[2][0]=t;
			t=new Tuile(3,tresors.getTresor(1));t.tourne(0);labyrinthe[4][0]=t;
			t=new Tuile(3,tresors.getTresor(2));t.tourne(3);labyrinthe[0][2]=t;
			t=new Tuile(3,tresors.getTresor(3));t.tourne(3);labyrinthe[2][2]=t;
			t=new Tuile(3,tresors.getTresor(4));t.tourne(0);labyrinthe[4][2]=t;
			t=new Tuile(3,tresors.getTresor(5));t.tourne(1);labyrinthe[6][2]=t;
			t=new Tuile(3,tresors.getTresor(6));t.tourne(3);labyrinthe[0][4]=t;
			t=new Tuile(3,tresors.getTresor(7));t.tourne(2);labyrinthe[2][4]=t;
			t=new Tuile(3,tresors.getTresor(8));t.tourne(1);labyrinthe[4][4]=t;
			t=new Tuile(3,tresors.getTresor(9));t.tourne(1);labyrinthe[6][4]=t;
			t=new Tuile(3,tresors.getTresor(10));t.tourne(2);labyrinthe[2][6]=t;
			t=new Tuile(3,tresors.getTresor(11));t.tourne(2);labyrinthe[4][6]=t;
	}

	/*
	 * creation de la liste des tuiles mobiles
	 */
	private ArrayList<Tuile> creerTuileMobiles(PaquetTresors tresors) {
	
		ArrayList<Tuile> l=new ArrayList<Tuile>();
		int nb; // nombre aleatoire pour tourner la tuile
		
		/*on cree les 13 tuiles droites qui sont toutes mobiles 
		 * et sans tresor et on les met dans l*/
		
		for(int i=0;i<13;i++){
			Tuile t=new Tuile(2,null);
			nb = (int)(Math.random()*4);
			t.tourne(nb);
			l.add(t);
		}
		/*on cree les 9 tuiles coude mobiles sans tresor*/
		for(int i=0;i<9;i++){
			Tuile t=new Tuile(1,null);
			nb = (int)(Math.random()*4);
			t.tourne(nb);			
			l.add(t);
		}
		/*on cree les 6 tuiles coudes mobiles avec tresor*/
		for(int i=12;i<18;i++){
			Tuile t=new Tuile(1,tresors.getTresor(i));
			nb = (int)(Math.random()*4);
			t.tourne(nb);	
			l.add(t);
		}
		/*on cree les 6 tuiles carrefour(3) mobiles avec tresor*/
		for(int i=18;i<24;i++){
			Tuile t=new Tuile(3,tresors.getTresor(i));
			nb = (int)(Math.random()*4);
			t.tourne(nb);	
			l.add(t);
		}

		return l;
	}

	/*
	 * Placement des tuiles mobiles sur le plateau 
	 */
	public Tuile placerTuilesMobiles(ArrayList<Tuile> tm){
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				if(i%2==1||j%2==1){
					int indChoisi = (int)(Math.random()*tm.size());
					labyrinthe[i][j]=tm.get(indChoisi);
					tm.remove(indChoisi);
				}
			}
		}
		return tm.get(0);
	}


	/*
	 * retourne la tuile du labyrinthe d'indice ligne, colonne
	 */
	public Tuile getTuile(int ligne,int col){
		return labyrinthe[ligne][col];
	}

	/*
	 * la meme que la precedente mais où les indices sont donnes par un point.
	 */
	public Tuile getTuile(Point P){
		return getTuile(P.getX(),P.getY());
	}

	/*
	 *  retourne la tuile sortie
	 */
	public Tuile getTuileSortie(){
		return tuileSortie;
	}

	/*
	 * retourne le labyrinthe
	 */
	public Tuile[][] getLabyrinthe(){
		return labyrinthe;
	}


	/*
	 *  Permet d'afficher le labyrinthe et la tuile sortie
	 */

	public String toString(){
		return labyrintheToString() + getTuileSortie().toString();
	}

	/*
	 * String representant les 8 rangees de tuiles du labyrinthe. 
	 */
	public String labyrintheToString(){
		String s = "      A   B   C   D   E   F   G \n";
		s += "         vvv     vvv     vvv    \n\n";   // un "v" au dessus d'une colonne indique que cette colonne peut etre poussee.
		for(int i=0;i<7;i++){
			s += ligneToString(i);
		}
		s += "         ^^^     ^^^     ^^^    \n";
		s += "      A   B   C   D   E   F   G \n\n";
		return s;
		
	}

	/*
	 * String representant une rangee du labyrinthe. 
	 * Chaque tuile etant affichee en 3 lignes, la methode 
	 * affiche la premiere ligne des tuiles de la rangee, 
	 * puis la deuxieme, puis la troisieme. 
	 */
	public String ligneToString(int l){
		String s = "";
		for(int i=0;i<3;i++){
			// affichage du numero de gauche si c'est la deuxieme ligne de la tuile qui est affichee
			if (i == 1) {
				s += " "+(l+1)+" ";
			}
			else {
				s += "   ";
			}
			if(l%2==1) {
				s += "> ";  // un ">" au debut d'une ligne indique que cette ligne peut etre poussee.
			}
			else {
				s += "  ";
			}
			//
			for(int j=0;j<7;j++){
				s += labyrinthe[j][l].toString(i)+ " ";
			}
			if(l%2==1) {
				s += "< ";  // un "<" a la fin d'une ligne indique que cette ligne peut etre poussee.
			}
			else {
				s += "  ";
			}
			if (i == 1) {
				s += (l+1);
			}
			s += "\n";
		}
		s += "\n";
		return s;
	}
	
	/**********************************METHODES AJOUTEES**********************/
	
	public boolean cheminPossible(Point depart, Point arrivee) {
	/*
	 * teste si un chemin existe entre le point depart et le point arrivee
	 */
	
		ArrayList<Point> pointsAccessibles = new ArrayList<Point>(); // liste des points accessibles a partir du point de depart
		ArrayList<Point> pointsAccessiblesNonTraites = new ArrayList<Point>(); // idem, dont il faut encore trouver les voisins
		Point origine; // point dont on va tester les directions possibles
		Point tuileD; // // coordonnees de la tuile situee a droite
		Point tuileG; // a gauche
		Point tuileH; // en haut
		Point tuileB; // en bas  de origine, vaut null si non accessible a partir de origine
		boolean possible = false; // vaut vrai s'il existe un chemin

		pointsAccessiblesNonTraites.add(depart);
		// tant que la liste bleu ne contient pas le point d'arrivee et tant qu'elle n'est pas vide...
		while (!pointsAccessiblesNonTraites.contains(arrivee) && !pointsAccessiblesNonTraites.isEmpty()) {
			origine = pointsAccessiblesNonTraites.get(0);
			// test droite
			tuileD = this.goD(origine);
			if (tuileD != null) { // si on peut aller a droite
				if (!pointsAccessibles.contains(tuileD) && !pointsAccessiblesNonTraites.contains(tuileD)) {
					pointsAccessiblesNonTraites.add(tuileD);
				}
			}
			// test gauche
			tuileG = this.goG(origine);
			if (tuileG != null) { // si on peut aller a gauche
				if (!pointsAccessibles.contains(tuileG) && !pointsAccessiblesNonTraites.contains(tuileG)) {
					pointsAccessiblesNonTraites.add(tuileG);
				}
			}
			// test haut
			tuileH = this.goH(origine);
			if (tuileH != null) { // si on peut aller en haut
				if (!pointsAccessibles.contains(tuileH) && !pointsAccessiblesNonTraites.contains(tuileH)) {
					pointsAccessiblesNonTraites.add(tuileH);
				}
			}
			// test bas
			tuileB = this.goB(origine);
			if (tuileB != null) { // si on peut aller en bas
				if (!pointsAccessibles.contains(tuileB) && !pointsAccessiblesNonTraites.contains(tuileB)) {
					pointsAccessiblesNonTraites.add(tuileB);
				}
			}
			// le point origine est traite on le met dans la liste des pointsAccessibles
			pointsAccessibles.add(origine);
			pointsAccessiblesNonTraites.remove(origine);
		}
		// on sort du while si le point arrivee est dans la liste pointsAccessiblesNonTraites ou si cette liste est vide
		// si le point arrivee est dans pointsAccessiblesNonTraites c'est qu'il existe un chemin
		if (pointsAccessiblesNonTraites.contains(arrivee)) {
			possible = true;
		}
		return possible;
	}
	
	public boolean deplacerPion(Point depart, Point arrivee, int numJoueur){
		boolean deplacer=false;
		Tuile tuileDepart;
		Tuile tuileArrivee;		

		if(!depart.equals(arrivee)){
			if (cheminPossible(depart, arrivee)){
				tuileDepart = labyrinthe[depart.getX()][depart.getY()];
				tuileArrivee = labyrinthe[arrivee.getX()][arrivee.getY()];
				tuileDepart.retirerPion(numJoueur);
				tuileArrivee.poserPion(numJoueur);
				deplacer=true;
			}else{
				System.out.println("\tVotre chemin n'est pas possible.");
			}
		}else{
			System.out.println("\tVous souhaitez rester a la meme place.\n");
			deplacer=true;
		}
		return deplacer;
	}
	
	public Point goD(Point origine) {
	// Retourne un point si on peut acceder a la tuile de droite a partir de origine
	// Sinon retourne null

		Point point=null;
		
		int x=origine.getX();
		int y=origine.getY();
		
		if ( x < 6 &&  (labyrinthe[x][y].getPorteEst() && labyrinthe[x+1][y].getPorteWest()) ){
			point= new Point (x+1, y);               
		}

		return point;
	}

	public Point goB(Point origine) {

		Point point=null;
		 
		int x=origine.getX();
		int y=origine.getY();
		
		if ( y < 6 &&  (labyrinthe[x][y].getPorteSud() && labyrinthe[x][y+1].getPorteNord()) ){
			point= new Point (x, y+1);
		}

		return point;
	}

	public Point goG(Point origine) {
	
 		Point point=null;
		
		int x=origine.getX();
		int y=origine.getY();
		
		if ( x > 0 &&  (labyrinthe[x][y].getPorteWest() && labyrinthe[x-1][y].getPorteEst()) ){
			point= new Point (x-1, y);
		}
		return point;
	}

	public Point goH(Point origine) {
	
 		Point point=null;
		
		int x=origine.getX();
		int y=origine.getY();
		
		if ( y > 0 &&  (labyrinthe[x][y].getPorteNord() && labyrinthe[x][y-1].getPorteSud()) ){
			point= new Point (x, y-1);
		}
		return point;
	}
	
	public boolean insererTuile(int x, int y, JeuDuLabyrinthe jdl){
		Tuile t;
		Tuile tempo; // Variable qui stockerai temporairement la future tuile sortie. Verifier le type!!!
		int i;
		boolean succes = false;
		ArrayList<Integer> pions;

		if(insertionPossible(x,y)){
//si on insere dans (x,0) la tuile qui sortira sera celle qui ce trouve en (x,6). 
			if(y==0){
				tempo=labyrinthe[x][6]; //je stock la futur tuile sortie dans une variable temporaire. 

				for(i=6;i>0;i--){ // On s'arrete a 0 pour que les indices ne sortent pas du Plateau.
					labyrinthe[x][i]=labyrinthe[x][i-1];
					// si la tuile qui bouge contient des pions, on les enleve et on les mets sur la tuile d'en bas
					if (labyrinthe[x][i-1].contientPions()) {
						pions = labyrinthe[x][i-1].clonerPions();
						bougerPions(pions,jdl,x,i);
					}
				}
				
				labyrinthe[x][y]=tuileSortie;
				tuileSortie=tempo;
				// si la future tuile sortie contient des pions, on les enleve et on les mets sur la tuile a l'oppose
				if (tempo.contientPions()) {
					pions = tempo.clonerPions();
					bougerPions(pions, jdl, x, y);
				}
			}
//si on insere dans (0,y) la tuile qui sortira sera celle qui ce trouve en (6,y).
			if(x==0){
				tempo=labyrinthe[6][y];

				for(i=6;i>0;i--){
					labyrinthe[i][y]=labyrinthe[i-1][y];
					// si la tuile qui bouge contient des pions, on les enleve et on les mets sur la tuile d'a gauche
					if (labyrinthe[i-1][y].contientPions()) {
						pions = labyrinthe[i-1][y].clonerPions();
						bougerPions(pions,jdl,i,y);
					}
				}
				
				labyrinthe[x][y]=tuileSortie;
				tuileSortie=tempo;
				
				if (tempo.contientPions()) {
					pions = tempo.clonerPions();
					bougerPions(pions, jdl, x, y);
				}
			}
//si on insere dans (6,y) la tuile qui sortira sera celle qui ce trouve en (0,y).
			if(x==6){
				tempo=labyrinthe[0][y];

				for(i=0;i<6;i++){
					labyrinthe[i][y]=labyrinthe[i+1][y];
					if (labyrinthe[i+1][y].contientPions()) {
						pions = labyrinthe[i+1][y].clonerPions();
						bougerPions(pions,jdl,i,y);
					}
				}
				
				labyrinthe[x][y]=tuileSortie;
				tuileSortie=tempo;
				
				if (tempo.contientPions()) {
					pions = tempo.clonerPions();
					bougerPions(pions, jdl, x, y);
				}
			}
//si on insere dans (x,6) la tuile qui sortira sera celle qui ce trouve en (x,0).
			if(y==6){
				tempo=labyrinthe[x][0];

				for(i=0;i<6;i++){
					labyrinthe[x][i]=labyrinthe[x][i+1];
					if (labyrinthe[x][i+1].contientPions()) {
						pions = labyrinthe[x][i+1].clonerPions();
						bougerPions(pions,jdl,x,i);
					}
				}
				
				labyrinthe[x][y]=tuileSortie;
				tuileSortie=tempo;
				
				if (tempo.contientPions()) {
					pions = tempo.clonerPions();
					bougerPions(pions, jdl, x, y);
				}
			}
				succes=true;
		}
		else {
			System.out.println("\tImpossible d'inserer la tuile a cet endroit.");
		}
		return succes;
	}
	
	public boolean insertionPossible (int x, int y){
		Point p = new Point(x,y);
		return tuileInserable.contains(p);
	}

	private ArrayList<Point> genererListeTuilesInserables() {
		ArrayList<Point> tuileInserable = new ArrayList<Point>();
		// On cree une liste de tuiles inserable.   
		//Modification le 09/05/2009 a 13:20. Creation d'une liste de tuiles insesable au lieu de tuiles immobile.
		tuileInserable.add(new Point(0,1));
		tuileInserable.add(new Point(0,3)); // Lorsque l'on voulais inserer une tuile en (5,1) par exemple
		tuileInserable.add(new Point(0,5));	//le programme ne faisait rien et passe automatiquement au joueur suivant	
		tuileInserable.add(new Point(1,0));
		tuileInserable.add(new Point(3,0));
		tuileInserable.add(new Point(5,0));
		tuileInserable.add(new Point(1,6));
		tuileInserable.add(new Point(3,6));
		tuileInserable.add(new Point(5,6));
		tuileInserable.add(new Point(6,1));
		tuileInserable.add(new Point(6,3));
		tuileInserable.add(new Point(6,5));
		
		return tuileInserable;
	}
		
	
	public void bougerPions(ArrayList<Integer> pions, JeuDuLabyrinthe jdl, int x, int y) {
	// pose tout les pions de la liste sur une autre tuile
		
		// la nouvelle position du joueurs
		Point newPosition = new Point(x,y);
		
		// pour chaque pion, le poser sur sa nouvelle tuile
		for (int i = 0 ; i < pions.size() ; i++) {
			labyrinthe[x][y].poserPion(pions.get(i));
			// on met a jour l'attribut position de chaque joueurs
			jdl.majPositions(pions.get(i),newPosition);
		}
	}
}
