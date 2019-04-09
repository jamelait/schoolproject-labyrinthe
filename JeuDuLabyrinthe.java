import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JeuDuLabyrinthe {

	// constante qui definit la liste des noms des tresors. Il y doit y avoir 24 noms.
	private final static String[] nomDesTresors={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x"};
	
	// la liste des joueurs
	private Joueur[] joueurs;

	// l'indice dans la liste precedente du joueur dont c'est le tour.
	private int indJoueurActif;	

	// le plateau de jeu (labyrinthe + tuile sortie)
	private Plateau plateau; 
	
	// couleur de fond de la fenetre
	//final static Color couleurFond = new Color(251,213,132);
	final static Color couleurFond = new Color(64,128,128);
	//final static Color couleurFond = new Color(157,225,219);
	//final static Color couleurFond = new Color(197,226,226);
	//final static Color couleurFond = Color.lightGray;
	//final static Color couleurFond = null;

	private JFrame fenetre;
	private JPanel panelPrincipal;
	private VuePlateau vp;
	private PInfo panelInfo;
	
	// zone de messages de la fenetre, modifiable par toutes les classes
	public ZoneMessage z = new ZoneMessage();
	
	public boolean peutDeplacer = false; // indique si le joueur actif peut se deplacer ou pas
	public boolean peutInserer = true; // indique si le joueur actif peut inserer la tuile ou pas
	
	private String dossierTuiles = "./images/tuiles/herbe/"; // repertoire ou se trouvent les images des tuiles
	

	public JeuDuLabyrinthe() {
		
		// creation de la fenetre
		fenetre = new JFrame("Jeu Du Labyrinthe");
		// creation du plateau
		PaquetTresors tresors = new PaquetTresors(nomDesTresors);		
		plateau = new Plateau(tresors);
		vp = new VuePlateau(this);
		// creation du menu
        fenetre.setJMenuBar(new MenuLaby(this));
		// configuration de la fenetre
		fenetre.setVisible(true);
		fenetre.setSize(855,720);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// creation des joueurs
		creerJoueurs();
		
		// le premier joueur doit tourner et inserer sa tuile
		z.afficher(ZoneMessage.TOURNER_INSERER);
		
		// informations du joueur actif		
		panelInfo = new PInfo(this);
		
		// ajout des differentes parties au panelPrincipal
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.add(vp,BorderLayout.CENTER);
		panelPrincipal.add(z,BorderLayout.NORTH);
		panelPrincipal.add(panelInfo, BorderLayout.WEST);
		panelPrincipal.setBackground(couleurFond);
		fenetre.setContentPane(panelPrincipal);
		
		tresors.distribueTresors(joueurs);
		indJoueurActif=0;
		// actualisation du panelInfo avec les informations du joueur actif
		panelInfo.actualiser();
		
	}

	public void creerJoueurs(){
	// cette methode doit afficher des JOptionPane
		DialogLaby dialog = new DialogLaby();
		try {
			int n = dialog.askNbJoueurs();
			joueurs = new Joueur[n];
			for (int i = 0 ; i < n ; i++) {
				dialog.askNomCoul(i);
				String nom = dialog.getNom();
				if (nom.equals("")) {
					nom = "Joueur " + (i+1);
				}
				joueurs[i] = new Joueur(nom,i,dialog.getCoul(),plateau);
			}
		}
		catch(NullPointerException e) {
		// si la creation de joueurs n'est pas terminee correctement on quitte la partie
			System.exit(1);
		}
		vp.actualiser();
	}
		
	/*
	 * fait passer numJoueurActif a l'indice du joueur suivant.
	 */
	public void joueurSuivant(){
		if (joueurs[indJoueurActif].aGagne()) {
			terminerPartie();
		}
		else {
			indJoueurActif++;
			if (indJoueurActif >= joueurs.length) {
				// si indJoueurActif superieur au nombre de joueurs , indJoueurActif devient 0
				indJoueurActif = 0;
			}
			panelInfo.actualiser();
		}
	}
	
	public void majPositions(Integer numJoueur, Point position) {
	// met a jour l'attribut position d'un joueur lorsque celui ci change de place sur le plateau
		joueurs[numJoueur].setPosition(position);
	}

	/*
	 * affiche un classement des joueurs
	 * on a besoin de conserver l'indice du joueur dans le tableau "joueurs" ainsi que la taille de son paquet
	 * ces deux valeurs ne doivent pas etre dissociees
	 */
	public void afficherClassement() {
		int[][] taillePaquet = new int[joueurs.length][2];
		int i;
		int[] tmp;
		
		for (i = 0 ; i < joueurs.length ; i++) {
			taillePaquet[i][0] = i; // indice du joueur dans le tableau joueurs
			taillePaquet[i][1] = joueurs[i].getTaillePaquet(); // taille du paquet du joueur
		}
		
		// tri des taille des paquets des joueurs dans l'ordre croissant
		for (i = 0 ; i < joueurs.length; i++) {
			for (int j = 0 ; j < joueurs.length ; j++) {
				if (taillePaquet[i][1] < taillePaquet[j][1]) {
					tmp = taillePaquet[i];
					taillePaquet[i] = taillePaquet[j];
					taillePaquet[j] = tmp;
				}
			}
		}
		
		// boite de dialogue qui affiche le classement
		JOptionPane jop = new JOptionPane();
		String score = "                            SCORES\n";
		for (i = 0 ; i < joueurs.length ; i++) {
			score += "Numero " + (i+1) + "    " + joueurs[taillePaquet[i][0]].getNom()+"\n";
			score += "              Cartes Restantes : " + joueurs[taillePaquet[i][0]].getTaillePaquet() + "\n";
		}
		jop.showMessageDialog(null, score, "Scores", JOptionPane.PLAIN_MESSAGE);
		
	}

	/* 
	 * Retourne la couleur coorespondante d'un joueur
	 */
	public Color couleurAssociee(Integer numJoueur) {
		char coul = joueurs[numJoueur].getCouleur();
		Color couleur = null;
		switch (coul) {
			case 'j' :
				couleur = Color.YELLOW;
				break;
			case 'v' :
				couleur = Color.GREEN;
				break;
			case 'b' :
				couleur = Color.BLUE;
				break;
			case 'r' :
				couleur = Color.RED;
		}
		return couleur;
	}

	public VuePlateau getVuePlateau() {
		return vp;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public Joueur getJoueur(int i){
		return joueurs[i];
	}

	public Joueur getJoueurActif(){
		return joueurs[indJoueurActif] ;
	}

	public int getindJoueurActif (){
		return indJoueurActif;
	}
   
	public String getDossierTuiles() {
		return dossierTuiles;
	}
	
	public void setDossierTuiles(String rep) {
		dossierTuiles = rep;
	}
	
	/*
	 * Affiche le classement et informe que la partie est terminee, puis quitte le jeu
	 */
	public void terminerPartie() {
		afficherClassement();
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, "La partie est terminée !", "Partie Terminee", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		JeuDuLabyrinthe jeu = new JeuDuLabyrinthe();
	}
	
}
