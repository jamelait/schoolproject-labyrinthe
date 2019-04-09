
public class Tresor {

	// Le nom du tresor 
	private String nom;
	
	public Tresor (String nom){
		this.nom = nom;
	}
	
	public String getNom (){
		return nom;
	}
	
	
	/*
	 * Retourne un raccourcis du nom du tresor sous la forme d'un String de longueur 1 
	 * (utile pour l'affichage du plateau où le tresor doit etre represente par un seul caractere).
	 * On se contentera ici de retourner le premier caractere du nom.
	 */
	public String getNomCourt() {
		return nom.substring(0, 1);
	}
	
	public String toString() {
		return nom;
	}
	
}
