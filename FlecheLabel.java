import java.awt.*;
import javax.swing.*;

class FlecheLabel extends JLabel {
/*
 * Une fleche cliquable
*/ 
	final static String REP = "./images/fleches/";
	
	private ImageIcon imageFlecheHaut; 
	private ImageIcon imageFlecheBas; 
	private ImageIcon imageFlecheDroite;
	private ImageIcon imageFlecheGauche;	
	private Plateau plateau;
	private JeuDuLabyrinthe jdl;
	private VuePlateau vp;
	
	public FlecheLabel(int x, int y, JeuDuLabyrinthe jdl) {
		
		this.jdl = jdl;
		this.plateau = jdl.getPlateau();
		this.vp = jdl.getVuePlateau();
		
		imageFlecheHaut = new ImageIcon(REP + "haut.png");
		imageFlecheBas = new ImageIcon(REP + "bas.png");
		imageFlecheDroite = new ImageIcon(REP + "droite.png");
		imageFlecheGauche = new ImageIcon(REP + "gauche.png");
		
		Curseur c = new Curseur();
		if(y == 0 && (x==2 || x==4 || x==6 )) {
			
			setIcon(imageFlecheBas);
			addMouseListener(new FlecheListener(x-1, y, jdl));
			setCursor(c.getCurseur(Curseur.MAIN));
		}
		else if(y == 8 && (x==2 || x==4 || x==6 )) {
				
			setIcon(imageFlecheHaut);
			addMouseListener(new FlecheListener(x-1, y-2, jdl));
			setCursor(c.getCurseur(Curseur.MAIN));
		}
		else if(x == 0 && (y==2 || y==4 || y==6 )) {
			
			setIcon(imageFlecheDroite);
			addMouseListener(new FlecheListener(x, y-1, jdl));
			setCursor(c.getCurseur(Curseur.MAIN));
		}
		else if(x == 8 && (y==2 || y==4 || y==6 )) {
			
			setIcon(imageFlecheGauche);
			addMouseListener(new FlecheListener(x-2, y-1, jdl));
			setCursor(c.getCurseur(Curseur.MAIN));
		}

	}
}
