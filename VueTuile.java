import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

class VueTuile extends JLabel {
/*
 * cette classe dessine l'image de la tuile, le tresor et les pions
**/

	final static String rep_TRESORS = "./images/tresors/";
	
	
	private Tuile tuile;
	private JeuDuLabyrinthe jdl;
	
	private ImageIcon image; // l'image de la tuile
	private String tresor = "";
	
	private String rep; // dossier ou se trouvent les tuiles
	
    /**
     * @param vp sera passe en parametre aux ecouteurs pour rafraichir l'affichage
	 * @param jdl sera utilise par paint pour connaitre la couleur associee au joueur
	 * @param x,y seront passes en parametre aux ecouteurs pour avoir les coordonnes de la tuile sur le plateau
	 * @param estTuileSortie vaut vrai si la tuile qui va etre construite est la tuile sortie, son ecouteur sera different des autres tuiles
     */
	public VueTuile(Tuile tuile,JeuDuLabyrinthe jdl,int y, int x,boolean estTuileSortie) {
		this.jdl = jdl;
		this.tuile = tuile;
		if (tuile.getTresor() != null) {
			tresor = tuile.getTresor().getNomCourt();
		}
		this.rep = jdl.getDossierTuiles();
		
		// choix de l'image et application au label
		genererImage();
		setIcon(image); 
		
		// ajout d'un ecouteur different si c'est la tuile sortie ou pas
		if (estTuileSortie) {
			addMouseListener(new TuileSortieListener(tuile,jdl));
		}
		else {
			addMouseListener(new TuileListener(tuile,jdl,y,x));
		}
	}

	public void genererImage() {
	// cree l'image de la tuile
		int type = tuile.getType();
		int nbFoisTournee = tuile.getNbFoisTournee();
		// creation de l'image en fonction du type et du nombre de fois tournee
		switch (type) {
			case 1 : // coude
				image = new ImageIcon(rep + "coude" + nbFoisTournee + ".gif");
				break;
			case 2 : // droit
				image = new ImageIcon(rep + "droit" + nbFoisTournee + ".gif");
				break;
			case 3 : // carrefour
				image = new ImageIcon(rep + "carrefour" + nbFoisTournee + ".gif");
				break;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		// coordonnees pour dessiner les pions
		int x = 0; 
		int y = 0;
		
		// dessin des pions
		for (int i = 0 ; i < tuile.getPions().size() ; i++) {
			// on fixe la couleur du dessin et dessin du pion de couleur
			g.setColor(jdl.couleurAssociee(tuile.getPions().get(i)));
			g.fillOval(x,y,20,20);
			// on fixe la bordure du pion en noir et dessin de la bordure
			g.setColor(Color.BLACK);
			g.drawOval(x,y,20,20);
		
			// on modifie l'emplacement ou seront dessines les nouveaux pions s'il y en a
			x += 10;
			y += 12;
		}
		// dessin du tresor
		//g.setColor(Color.WHITE);
		//g.drawString(tresor,20,26);
		Image img = new ImageIcon(rep_TRESORS +tresor+".png").getImage();
		g.drawImage(img, 17,17,this);
	}
}
