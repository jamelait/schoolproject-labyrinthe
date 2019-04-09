import java.awt.*;
import javax.swing.*;

class ZoneMessage extends JPanel {

	private int messageCourant;
	final static String rep = "./images/icones/";
	
	// on ne peut pas encore se deplacer
	final static int PAS_BOUGER = 1;
	final static ImageIcon IMAGE_PAS_BOUGER = new ImageIcon(rep+"1.png");
	final static String TEXTE_PAS_BOUGER = "Vous ne pouvez pas encore vous deplacer.\nInserez d'abord la tuile en cliquant sur une fleche.";
	
	// il faut se deplacer
	final static int DEPLACER = 2;
	final static ImageIcon IMAGE_DEPLACER = new ImageIcon(rep+"5.png");
	final static String TEXTE_DEPLACER = "Cliquez sur une tuile pour vous deplacer.\nCliquez sur la tuile ou vous etes pour rester a la meme place";
	
	// on ne peut plus tourner la tuile
	final static int PLUS_TOURNER = 0;
	final static ImageIcon IMAGE_PLUS_TOURNER = new ImageIcon(rep+"0.png");
	final static String TEXTE_PLUS_TOURNER = "Vous avez deja tourne la tuile.";
	
	// il faut tourner et inserer la tuile
	final static int TOURNER_INSERER = 3;
	final static ImageIcon IMAGE_TOURNER_INSERER = new ImageIcon(rep+"3.png");
	final static String TEXTE_TOURNER_INSERER = "Tournez la tuile en cliquant dessus.\nPuis cliquez sur une fleche pour l'inserer.";
	
	// chemin impossible
	final static int OBSTACLES = 4;
	final static ImageIcon IMAGE_OBSTACLES = new ImageIcon(rep+"4.png");
	final static String TEXTE_OBSTACLES = "Impossible d'aller la, il y a des obstacles.";
	
	// on ne peut pas inserer
	final static int PLUS_INSERER = 5;
	final static ImageIcon IMAGE_PLUS_INSERER = new ImageIcon(rep+"9.png");
	final static String TEXTE_PLUS_INSERER = "Vous avez deja insere votre tuile.";
	
	// la victoire est proche
	final static int VICTOIRE = 6;
	final static ImageIcon IMAGE_VICTOIRE = new ImageIcon(rep+"6.png");
	final static String TEXTE_VICTOIRE = "Vous avez trouve tous vos tresors.\nRetournez a votre position de depart pour gagner la partie !";
	
	// message de bienvenue
	final static int WELCOME = 8;
	final static ImageIcon IMAGE_WELCOME = new ImageIcon(rep + "welcome.jpg");
	final static String TEXTE_WELCOME = "Welcome !!";
	
	private JLabel image; // label illustrant le message
	private JTextPane texte; // le message
	
	public ZoneMessage() {
		setLayout(new BorderLayout());
		setBackground(JeuDuLabyrinthe.couleurFond);
		
		image = new JLabel();
		texte = new JTextPane();
		
		image.setPreferredSize(new Dimension(90,150));
		texte.setPreferredSize(new Dimension(160,150));
		
		texte.setEditable(false);
		texte.setFont(new Font("Gras",Font.BOLD,14));
		texte.setBackground(JeuDuLabyrinthe.couleurFond);
		
		add(image,BorderLayout.NORTH);
		add(texte,BorderLayout.CENTER);
	/*	Dimension ecart = new Dimension(0,2);
		Component espace = Box.createRigidArea(ecart);
		espace.setPreferredSize(ecart);
		espace.setForeground(Color.BLACK);
		add(espace,BorderLayout.SOUTH);*/
		
	}
	
	public void afficher(int m) {
		switch(m) {
			case PLUS_TOURNER :
				image.setIcon(IMAGE_PLUS_TOURNER);
				texte.setText(TEXTE_PLUS_TOURNER);
			break;
			case PAS_BOUGER :
				image.setIcon(IMAGE_PAS_BOUGER);
				texte.setText(TEXTE_PAS_BOUGER);
			break;
			case DEPLACER :
				image.setIcon(IMAGE_DEPLACER);
				texte.setText(TEXTE_DEPLACER);
			break;
			case TOURNER_INSERER :
				image.setIcon(IMAGE_TOURNER_INSERER);
				texte.setText(TEXTE_TOURNER_INSERER);
			break;
			case OBSTACLES :
				image.setIcon(IMAGE_OBSTACLES);
				texte.setText(TEXTE_OBSTACLES);
			break;
			case PLUS_INSERER :
				image.setIcon(IMAGE_PLUS_INSERER);
				texte.setText(TEXTE_PLUS_INSERER);
			break;
			case VICTOIRE :
				image.setIcon(IMAGE_VICTOIRE);
				texte.setText(TEXTE_VICTOIRE);
			break;
			case WELCOME :
				image.setIcon(IMAGE_WELCOME);
				texte.setText(TEXTE_WELCOME);
			break;
		}
		messageCourant = m;
	}
	
	/*
	 * Retourne l'identifiant du message dernierement affiche
	 */
	public int getMessageCourant() {
		return messageCourant;
	}
	
}
