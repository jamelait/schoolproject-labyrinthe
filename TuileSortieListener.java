import java.awt.event.*; 


class TuileSortieListener implements MouseListener {
/*
 * tourne la tuile lorsqu'on clique dessus
*/

	private Tuile tuile;
	private JeuDuLabyrinthe jdl;
	private VuePlateau vp;
	
	int messageCourant = -1;

	public TuileSortieListener(Tuile tuile,JeuDuLabyrinthe jdl) {
		 this.tuile = tuile;
		 this.jdl = jdl;
		 this.vp = jdl.getVuePlateau();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (jdl.peutInserer) {
			tuile.tourne(1);
			vp.dessinerPanelTuileSortie();
		}
	}
	
	public  void mouseEntered(MouseEvent e) {
		if (!jdl.peutInserer) {
			messageCourant = jdl.z.getMessageCourant();
			jdl.z.afficher(ZoneMessage.PLUS_TOURNER);
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