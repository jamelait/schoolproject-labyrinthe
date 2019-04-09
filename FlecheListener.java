import java.awt.event.*;

class FlecheListener implements MouseListener{
	
	private int x;
	private int y;
	private Plateau plateau;
	private JeuDuLabyrinthe jdl;
	private VuePlateau vp;
	
	int messageCourant = -1;
	
	public FlecheListener(int x, int y, JeuDuLabyrinthe jdl) {
		//coordonnes de la fleche cliquee
		this.x=x;
		this.y=y;
		this.jdl = jdl;
		this.plateau = jdl.getPlateau();
		this.vp = jdl.getVuePlateau();
	}
	public void mouseClicked(MouseEvent e){
		// si le joueur a le droit d'inserer la tuile on l'insere et on actualise le plateau
		if (jdl.peutInserer) {
			plateau.insererTuile(x,y,jdl);
			vp.actualiser();
			// le joueur a le droit de se deplacer mais n'a plus le droit d'inserer la tuile
			jdl.peutDeplacer = true;
			jdl.peutInserer = false;
			// si le paquet du joueur est vide, on le lui dit
			if (jdl.getJoueurActif().getTaillePaquet() == 0) {
				jdl.z.afficher(ZoneMessage.VICTOIRE);
			}
			else {
			// sinon on lui dit de se deplacer
				jdl.z.afficher(ZoneMessage.DEPLACER);
			}
		}
	}
	
	public  void mouseEntered(MouseEvent e) {
		if (!jdl.peutInserer) {
			messageCourant = jdl.z.getMessageCourant();
			jdl.z.afficher(ZoneMessage.PLUS_INSERER);
		}
	}
	 public void mouseExited(MouseEvent e) {
		if (!jdl.peutInserer && messageCourant >= 0) {
			jdl.z.afficher(messageCourant);
			messageCourant = -1;
		}
	}
	 public void mousePressed(MouseEvent e)  {
	}
	 public void mouseReleased(MouseEvent e) {
	}
}

