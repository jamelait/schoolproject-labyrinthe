import java.util.ArrayList;

class Tuile {

	// les pions sont les indices des joueurs qui sont poses sur la tuile.
	private ArrayList<Integer> pions;

	// tresor est le trsor eventuel de la tuile. S'il n'y a pas de tresor, 
	// ce champ a comme valeur ' ' 
	Tresor tresor;

	// tabPortes est un tableau de booleens N,S,E,W representant les portes de la tuile.
	boolean [] tabPortes;

	private int type;

	public Tuile(int t,Tresor tresor){
		this.tresor=tresor;
		this.initPortes(t);
		this.pions=new ArrayList<Integer>();
	}
	
	/*
	 * initialises les portes en fonction du type t 
	 * (1 => coude, 2=>droit, 3=>carrefour)
	 */
	public void initPortes(int type){
		this.type = type;
		tabPortes=new boolean[4];
		switch(type){
		case 0 : 
			for(int k=0;k<4;k++)tabPortes[k]=false;
			break;
		case 1 : {
			tabPortes[0]=false;tabPortes[1]=true;tabPortes[2]=true;tabPortes[3]=false;
			break;
		}
		case 2 : 
			tabPortes[0]=false;tabPortes[1]=true;tabPortes[2]=false;tabPortes[3]=true;
			break;

		case 3 : {
			tabPortes[0]=false;tabPortes[1]=true;tabPortes[2]=true;tabPortes[3]=true;
			break;
		}
		}	
	}

	public boolean avecTresor(){
		return this.tresor != null;
	}

	public Tresor getTresor() {
		return tresor;
	}
	
	public ArrayList<Integer> getPions() {
		return pions;
	}

	public boolean getPorteNord(){
		return tabPortes[0];
	}

	public boolean getPorteEst(){
		return tabPortes[1];
	}

	public boolean getPorteSud(){
		return tabPortes[2];
	}

	public boolean getPorteWest(){
		return tabPortes[3];
	}

	/*
	 * Permet l'affichage d'une tuile.
	 * Les pions s'affichent aux coins de la tuile, le tresor au milieu.
	 * les passages sont representes par des '-' , les murs par des '*'.
	 */
	public String toString(){
		return this.toString1()+"\n"+ this.toString2()+"\n" + this.toString3();
	}

	
	/*
	 * String correspondant a la premiere ligne de la tuile. 
	 */
	public String  toString1(){
		String s ;
		if(pions.contains(0)){
			s="1";
		}
		else{
			s="*";
		}
		if(this.getPorteNord()){
			s=s+"-"; 
		}
		else {
			s=s+"*";
		}
		if(pions.contains(1)){
			s=s+"2";
		}
		else{
			s=s+"*";
		}
		return s;
	}


	/*
	 * String correspondant a la deuxieme ligne de la tuile. 
	 */
	public String  toString2(){
		String s ="";
		if(this.getPorteWest()){
			s="-";
		}
		else {
			s="*";
		}
		if(this.avecTresor()){

			s=s+this.getTresor().getNomCourt();
		}
		else{
			s=s+"-";
		}
		if(this.getPorteEst()) {
			s=s+'-';
		}
		else{
			s=s+"*";
		}
		return s;
	}

	/*
	 * String correspondant a la troisieme ligne de la tuile. 
	 */
	public String  toString3(){
		String s ;
		if(pions.contains(2)){
			s="3";
		}
		else{
			s="*";
		}
		if(this.getPorteSud()){
			s=s+"-";
		}
		else {
			s=s+"*";
		}
		if(pions.contains(3)){
			s=s+"4";
		}
		else{
			s=s+"*";
		}
		return s;
	}

	
	
	/*
	 * Cette methode appelle selon la valeur du parametre l'une des 3 methodes precedentes.
	 * Elle est utilisee par la classe Pleteau pour l'affichage ligne a ligne du labyrinthe. 
	 */

	public String toString(int i){
		if(i==0) return toString1();
		if (i==1) return toString2();
		if(i==2) return toString3();
		return ("erreur dans appel de Tuile.toString(i) : " + i + "\n");
	}


	/*methode permet de faire tourner une tuile du nombre de quarts
	 *  de tours donne dans le sens des aiguilles d'une montre.
	 *  Il s'agit de mettre a jour les portes de la tuile.
	 *  Par exemple tourne(1) sur une tuile dont les portes sont 
	 *  [true,false,true, false] produit [false,true,false,true].
	 */
	public void tourne(int nbTour){
		for (int i=0 ; i < nbTour ; i++) {
			boolean[] tabTemp = new boolean[4];
			tabTemp[0] = tabPortes[3];
			tabTemp[1] = tabPortes[0];
			tabTemp[2] = tabPortes[1];
			tabTemp[3] = tabPortes[2];
			tabPortes = tabTemp;
		}
	}

	/**********************************METHODES AJOUTEES***********************/
	
	public void poserPion(int numJoueur) {
	 // ajoute un pion a la liste des pions qui sont sur la tuile
		pions.add(numJoueur);
	}
	
	public void retirerPion(Integer numJoueur){
	// le numJoueur doit etre de type Integer pour ne pas etre confondu avec l'indice de type int
		pions.remove(numJoueur); //enleve le pion de la liste des pions qui sont sur la tuile.
	}

	public boolean retirerTresor(Tresor t) {
	// supprime le tresor de cette tuile si le tresor recu en parametre est le meme
	// retourne true si la suppression est effectuee, retourne false si le tresor n'est pas le meme
		boolean tresorSupprime = false;
		if (t != null && t.equals(tresor)) {
			tresor = null;
			tresorSupprime = true;
		}
		return tresorSupprime;
	}


	public boolean contientPions(){
	/* retourne vrai s'il y a des pions sur cette tuile */
		return !pions.isEmpty();
	}
	
	/*
	 * methode qui renvoit la liste des pions actuels d'une tuile, 
	 * puis efface cette liste de la tuile
	 */
	@SuppressWarnings("unchecked") // warning a propos du cast
	public ArrayList<Integer> clonerPions() {
		ArrayList<Integer> p = new ArrayList<Integer>();
		// pions.clone retourne un Object, on le caste en ArrayList d'Integer
		p = (ArrayList<Integer>) pions.clone();
		// on efface la liste de pions
		pions.clear();
		return p;
	}
	
	/*********************************INTERFACE GRAPHIQUE***********************/

	public int getType() {
		return type;
	}
	
	public int getNbFoisTournee() {
	// retourne le nombre de quart de tours que la tuile a subit a partir de la configuration initiale des portes
		int nbFois = -1;
		switch (type) {
			case 1 :
				nbFois = nbToursCoude();
				break;
			case 2 :
				nbFois = nbToursDroit();
				break;
			case 3 :
				nbFois = nbToursCarrefour();
				break;
		}
		return nbFois;
	}
	
	public int nbToursCoude() {
	// retourne le nombre de tours qu'a subit une tuile de type coude depuis son initialisation
		int nbFois = -1;
		
		if (!getPorteNord() && getPorteEst() && getPorteSud() && !getPorteWest()) {
			nbFois = 0;
		}
		else if (!getPorteNord() && !getPorteEst() && getPorteSud() && getPorteWest()) {
			nbFois = 1;
		}
		else if (getPorteNord() && !getPorteEst() && !getPorteSud() && getPorteWest()) {
			nbFois = 2;
		}
		else if (getPorteNord() && getPorteEst() && !getPorteSud() && !getPorteWest()) {
			nbFois = 3;
		}
		
		return nbFois;
	}
	
	public int nbToursDroit() {
	// retourne le nombre de tours qu'a subit une tuile de type droite depuis son initialisation
		int nbFois = -1;
		
		if (!getPorteNord()) {
			nbFois = 0;
		}
		else {
			nbFois = 1;
		}
		
		return nbFois;
	}
	
	public int nbToursCarrefour() {
	// retourne le nombre de tours qu'a subit une tuile de type carrefour depuis son initialisation
		int nbFois = -1;
		
		// une tuile coude n'a qu'une seule porte fermee
		if (!getPorteNord()) {
			nbFois = 0;
		}
		else if (!getPorteEst()) {
			nbFois = 1;
		}
		else if (!getPorteSud()) {
			nbFois = 2;
		}
		else if (!getPorteWest()) {
			nbFois = 3;
		}
		
		return nbFois;
	}

	
}


