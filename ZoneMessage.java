import java.awt.*;
import javax.swing.*;

class ZoneMessage extends JPanel {

	final static String rep = "./images/icones/";
	
	// on ne peut pas encore se deplacer
	final static int PAS_BOUGER = 1;
	final static ImageIcon IMAGE_PAS_BOUGER = new ImageIcon(rep+"1.png");
	final static String TEXTE_PAS_BOUGER = "Vous ne pouvez pas encore vous deplacer.\n Inserez d'abord la tuile en cliquant sur une fleche.";
	
	// il faut se deplacer
	final static int DEPLACER = 2;
	final static ImageIcon IMAGE_DEPLACER = new ImageIcon(rep+"5.png");
	final static String TEXTE_DEPLACER = "Cliquez sur une tuile pour vous deplacer.\n Cliquez sur la tuile ou vous etes pour rester a la meme place";
	
	// on ne peut plus tourner la tuile
	final static int PLUS_TOURNER = 0;
	final static ImageIcon IMAGE_PLUS_TOURNER = new ImageIcon(rep+"0.png");
	final static String TEXTE_PLUS_TOURNER = "Vous avez deja tourne la tuile. \n" + TEXTE_DEPLACER;
	
	// il faut tourner et inserer la tuile
	final static int TOURNER_INSERER = 3;
	final static ImageIcon IMAGE_TOURNER_INSERER = new ImageIcon(rep+"3.png");
	final static String TEXTE_TOURNER_INSERER = "Tournez la tuile en cliquant dessus.\n Puis cliquez sur une fleche pour l'inserer.";
	
	// chemin impossible
	final static int OBSTACLES = 4;
	final static ImageIcon IMAGE_OBSTACLES = new ImageIcon(rep+"4.png");
	final static String TEXTE_OBSTACLES = "Impossible d'aller la, il y a des obstacles.";
	
	// on ne peut pas inserer
	final static int PLUS_INSERER = 5;
	final static ImageIcon IMAGE_PLUS_INSERER = new ImageIcon(rep+"5.png");
	final static String TEXTE_PLUS_INSERER = "Vous avez deja insere votre tuile.\n " + TEXTE_DEPLACER;
	
	// la victoire est proche
	final static int VICTOIRE = 6;
	final static ImageIcon IMAGE_VICTOIRE = new ImageIcon(rep+"6.png");
	final static String TEXTE_VICTOIRE = "Vous avez trouve tous vos tresors.\n Retournez a votre position de depart pour gagner la partie !";
	
	// message de bienvenue
	final static int WELCOME = 8;
	final static ImageIcon IMAGE_WELCOME = new ImageIcon(rep + "welcome.jpg");
	final static String TEXTE_WELCOME = "Welcome !!";
	
	private JLabel label; // label illustrant le message
	
	public ZoneMessage() {
		setLayout(new BorderLayout());
		setBackground(JeuDuLabyrinthe.couleurFond);
		label = new JLabel();
		add(label);
	}
	
	public void afficher(int m) {
		switch(m) {
			case PLUS_TOURNER :
				label.setIcon(IMAGE_PLUS_TOURNER);
				label.setText(TEXTE_PLUS_TOURNER);
			break;
			case PAS_BOUGER :
				label.setIcon(IMAGE_PAS_BOUGER);
				label.setText(TEXTE_PAS_BOUGER);
			break;
			case DEPLACER :
				label.setIcon(IMAGE_DEPLACER);
				label.setText(TEXTE_DEPLACER);
			break;
			case TOURNER_INSERER :
				label.setIcon(IMAGE_TOURNER_INSERER);
				label.setText(TEXTE_TOURNER_INSERER);
			break;
			case OBSTACLES :
				label.setIcon(IMAGE_OBSTACLES);
				label.setText(TEXTE_OBSTACLES);
			break;
			case PLUS_INSERER :
				label.setIcon(IMAGE_PLUS_INSERER);
				label.setText(TEXTE_PLUS_INSERER);
			break;
			case VICTOIRE :
				label.setIcon(IMAGE_VICTOIRE);
				label.setText(TEXTE_VICTOIRE);
			break;
			case WELCOME :
				label.setIcon(IMAGE_WELCOME);
				label.setText(TEXTE_WELCOME);
			break;
		}
	}
	
}
