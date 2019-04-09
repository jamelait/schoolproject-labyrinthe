import java.awt.event.*; 


class TuileSortieListener implements MouseListener {
/*
 * tourne la tuile lorsqu'on clique dessus
*/

	private Tuile tuile;
	private JeuDuLabyrinthe jdl;
	private VuePlateau vp;

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
		else {
			jdl.z.afficher(ZoneMessage.PLUS_TOURNER);
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