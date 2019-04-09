import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

class VuePlateau extends JLayeredPane {
/*
 * cette classe cree un panel contenant deux autres panel, celui du plateau et celui de la tuile sortie
*/
	final static int LARGEUR = 60; // largeur d'une image
	final static int HAUTEUR = 60; // hauteur d'une image
	
	private JPanel panelPlateau; // panel qui contiendra toutes les tuiles du labyrinthe
	private JPanel panelTuileSortie; // panel qui contiendra la tuile sortie
	private JPanel panelFleche; // panel qui contiendra les boutons Fleche qui nous permetrons d'inserer une tuile. 
	
	private Plateau plateau; // permettra de recuperer les tuiles
	private JeuDuLabyrinthe jdl; // sera envoye en parametre pour la construction de la tuile
	
	public VuePlateau(JeuDuLabyrinthe jdl) {
		
		this.jdl = jdl;
		plateau = jdl.getPlateau();
		
		panelPlateau = new JPanel(new GridLayout(7,7,0,0));	
		panelTuileSortie = new JPanel();
		panelFleche = new JPanel(new GridLayout(9,9,0,0)); 
		
		// couleur de fond pour les panels
		panelTuileSortie.setBackground(jdl.couleurFond);
		panelFleche.setBackground(jdl.couleurFond);
		
		// configuration de la position et de la taille des panels
		panelFleche.setBounds(0,0,LARGEUR*9, HAUTEUR*9);
		panelPlateau.setBounds(LARGEUR,HAUTEUR,LARGEUR*7,HAUTEUR*7);
		panelTuileSortie.setBounds(LARGEUR*9,HAUTEUR*4,LARGEUR+10,HAUTEUR+10);
		
		// rassemblement dans ce JLayeredPane
		add(panelPlateau,JLayeredPane.MODAL_LAYER);
		add(panelTuileSortie);
		add(panelFleche,JLayeredPane.PALETTE_LAYER);
		
		// ajout des composants aux JPanel
		dessinerPanelFleche();
		dessinerPanelPlateau();
		dessinerPanelTuileSortie();
		
		// configuration du cuseur pour les JPanel
		Curseur c = new Curseur();
		panelPlateau.setCursor(c.getCurseur(Curseur.MAIN2));
		panelTuileSortie.setCursor(c.getCurseur(Curseur.TOURNER));
		
	}
	
	/*
	* creation de toute les tuiles du plateau et ajout des tuiles au panelPlateau
	*/
	public void dessinerPanelPlateau() {
		
		panelPlateau.removeAll();
		
		Tuile tuile; // la tuile qui sera dessinee
		
		// dessin des tuiles du panelPlateau
		for (int y = 0 ; y < 7 ; y += 1) {
			for (int x = 0 ; x < 7 ; x+= 1) {
				tuile = plateau.getTuile(x,y);
				panelPlateau.add(new VueTuile(tuile,jdl,y,x,false));
			}
		}
		panelPlateau.validate();
	}
	
	/*
	* creation de la tuile sortie et ajout au panelPlateau
	*/
	public void dessinerPanelTuileSortie() {
		panelTuileSortie.removeAll();
		
		Tuile tuile; // la tuile qui sera dessinee
		
		// dessin de la tuile sortie
		tuile = plateau.getTuileSortie();
		// ajout de la tuileSortie au panelTuileSortie
		panelTuileSortie.add(new VueTuile(tuile,jdl,0,0,true));
		panelTuileSortie.validate();
	}

	/*
	* creation des fleches et ajout au panelFleche
	*/
	public void dessinerPanelFleche() {
		panelFleche.removeAll();    // ca efface tous les composants du panel
		for (int y = 0 ; y < 9 ; y += 1){
			for(int x = 0; x < 9; x += 1){
				panelFleche.add(new FlecheLabel(x,y,jdl));
			}
		}
		panelFleche.validate();
	}
	
	//public void reDessinerPanelFleche() {}
	/*
	* methode appelee automatiquement lorsqu'on redimensionne la fenetre
	* ou qu'on la reduit et l'affiche a nouveau
	*/
	public void paintComponent(Graphics g) {
		// dessinerPanelPlateau();
		// dessinerPanelTuileSortie();
		// dessinerPanelFleche();
	}
	
	public void actualiser() {
		dessinerPanelPlateau();
		dessinerPanelTuileSortie();
		dessinerPanelFleche();
	}

}
