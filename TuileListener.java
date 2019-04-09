import java.awt.event.*; 


class TuileListener implements MouseListener {

	private Tuile tuile;
	private JeuDuLabyrinthe jdl;
	private int x;
	private int y;
	private Point p;

	public TuileListener(Tuile tuile, JeuDuLabyrinthe jdl,int i, int j) {
		this.tuile = tuile;
		this.jdl = jdl;
		//coordonnes de la tuile cliquee
		 x = i;
		 y = j;
		 p = new Point(y,x);
	}
	
	public void mouseClicked(MouseEvent e) {
		// si le joueur a le droit de se deplacer, ca veut dire qu'il a insere sa tuile
		if (jdl.peutDeplacer) {
			Joueur j = jdl.getJoueurActif();
			// si le joueur est deplace
			if (jdl.getPlateau().deplacerPion(j.getPosition(),p,jdl.getindJoueurActif())) {
				j.setPosition(p);
				// si cette tuile contient le tresor que le joueur cherche, on le retire de la tuile et du paquet du joueur
				if (tuile.retirerTresor(j.getTresorATrouver())) {
					j.aTrouveTresor();
				}
				// le joueur a ete deplace, on actualise le plateau
				jdl.getVuePlateau().actualiser();
				// on passe au joueur suivant, qui a le droit d'inserer la tuile, mais pas de se deplacer
				jdl.joueurSuivant();
				jdl.peutDeplacer = false;
				jdl.peutInserer = true;
				// message : tourner et inserer la tuile
				jdl.z.afficher(ZoneMessage.TOURNER_INSERER);
			}
			// le joueur ne peut pas se deplacer sur la tuile cliquee pour cause d'obstacles, on le lui dit
			else {
				jdl.z.afficher(ZoneMessage.OBSTACLES);
			}
		}
		// le joueur n'a pas le droit de se deplacer, on le lui dit
		else {
			jdl.z.afficher(ZoneMessage.PAS_BOUGER);
		}
	}
	
	public  void mouseEntered(MouseEvent e) {
	}
	 public void mouseExited(MouseEvent e) {
	}
	 public void mousePressed(MouseEvent e)  {
	}
	 public void mouseReleased(MouseEvent e) {
	}

}
