
public class Point {

	// un point dans un plan est caracterise par son abscisse et son ordonnee.
	// Cette classe pourra etre utilisee pour gerer la position des joueurs ou des tuiles sur le
	// labyrinthe.
	private int abscisse,ordonnee;

	public Point(int i,int j){
		abscisse=i;ordonnee=j;
	}

	public int getX(){
		return abscisse;		
	}

	public int getY(){
		return ordonnee;
	}

	public void setCoord(int i,int j){
		abscisse=i;
		ordonnee=j;
	}

	public String toString() {
		return Coordonnees.indiceEnCoordonnees(abscisse+""+ordonnee);
	}


	/*
	 * 2 points sont equals s'ils ont les memes abscisses et ordonnees. 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Point other = (Point) obj;
		if (abscisse != other.abscisse)
			return false;
		if (ordonnee != other.ordonnee)
			return false;
		return true;
	}
}
