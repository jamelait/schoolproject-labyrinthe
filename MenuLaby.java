import javax.swing.*;
import java.awt.event.*;

class MenuLaby extends JMenuBar implements ActionListener {

	private JeuDuLabyrinthe jdl;
	private VuePlateau vp;

	public MenuLaby(JeuDuLabyrinthe j) {
		jdl = j;
		vp = jdl.getVuePlateau();
		
		// construction du menu et des items
		JMenu jeu = new JMenu("Jeu");
		JMenu edition = new JMenu("Edition");
		JMenu skins = new JMenu("Skins");
		
		JMenuItem nouvellePartie = new JMenuItem ("Nouvelle Partie");
		JMenuItem regleJeu = new JMenuItem ("Regles du jeu");
		JMenuItem scores = new JMenuItem ("Score");
		JMenuItem quitter = new JMenuItem ("Quitter");
		
		JMenuItem herbe = new JMenuItem("Herbe Briques");
		JMenuItem route = new JMenuItem("Route");
		JMenuItem classique = new JMenuItem("Classique");
		
		// ajout des items aux menus
		jeu.add(nouvellePartie);
		jeu.add(scores);
		jeu.add(quitter);
		edition.add(regleJeu);
		skins.add(herbe);
		skins.add(route);
		skins.add(classique);
		
		// ajout des listener au items exemple : item.addActionListener(this);
		
		nouvellePartie.addActionListener(this);
		regleJeu.addActionListener(this);
		scores.addActionListener(this);
		quitter.addActionListener(this);
		classique.addActionListener(this);
		herbe.addActionListener(this);
		route.addActionListener(this);
		
		//ajout des menus a cette barre de menu
		this.add(jeu);
		this.add(edition);
		this.add(skins);
    }

	
	/*
	* Listener du menu
	*/
	public void actionPerformed(ActionEvent e) {
		
		String commande = e.getActionCommand();
		
		if (commande == "Nouvelle Partie") {
		}
		else if (commande == "Regles du jeu") {
			new ReglesDuJeu();
		}
		else if (commande == "Score") {
			jdl.afficherClassement();
		}
		else if (commande == "Quitter") {
			if (DialogLaby.question("Quitter la partie en cours ?","Quitter"))
				System.exit(0);
		}
		else if (commande == "Classique") {
			jdl.setDossierTuiles("./images/tuiles/classique/");
			vp.actualiser();
		}
		else if (commande == "Herbe Briques") {
			jdl.setDossierTuiles("./images/tuiles/herbe/");
			vp.actualiser();
		}
		else if (commande == "Route") {
			jdl.setDossierTuiles("./images/tuiles/route/");
			vp.actualiser();
		}
	}
}
