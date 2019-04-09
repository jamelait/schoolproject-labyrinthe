import java.awt.*;
import javax.swing.*;

public class PInfo extends JPanel{
	
	final static String REP = "./images/tresors/big/";
	private JeuDuLabyrinthe jdl;
	private JLabel nomJoueur;
	private JLabel tresorRestant;
	private JLabel tresorATrouver;

	public PInfo (JeuDuLabyrinthe jdl){
		this.jdl = jdl;
		nomJoueur = new JLabel();
		tresorATrouver = new JLabel();
		tresorRestant = new JLabel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));// pour que les elements soit en colonne.
		
		Dimension espace = new Dimension(0,20);
			add(Box.createRigidArea(espace)); // espace entre les differents label
		add(new JLabel ("NOM DU JOUEUR : "));
			add(Box.createRigidArea(espace));
		add(nomJoueur);
			add(Box.createRigidArea(espace));
		add(new JLabel ("TRESOR A TROUVER : "));
			add(Box.createRigidArea(espace));
		add(tresorATrouver);
			add(Box.createRigidArea(espace));
		add(new JLabel ("NOMBRE DE TRESOR RESTANT : "));
			add(Box.createRigidArea(espace));
		add(tresorRestant);
		setForeground(Color.RED);
		setBackground(JeuDuLabyrinthe.couleurFond);
	}
	

	public void actualiser(){
	// actualise le panneau d'information pour le joueur actif	
		Joueur joueurActif = jdl.getJoueurActif();
		nomJoueur.setForeground(jdl.couleurAssociee(jdl.getindJoueurActif()) );
		nomJoueur.setText(joueurActif.getNom());
		tresorRestant.setText(""+joueurActif.getTaillePaquet());
		// si le paquet n'est pas vide
		if (joueurActif.getTaillePaquet() != 0) {
			ImageIcon tresor = new ImageIcon(REP + joueurActif.getTresorATrouver().getNomCourt()+".png");
			tresorATrouver.setIcon(tresor);
			tresorATrouver.setText("");
		}
		else {
			// sinon aucune image, mais un texte qui dit ou se trouve la position initiale du joueur
			tresorATrouver.setIcon(null);
			tresorATrouver.setText(joueurActif.textePositionDepart);
		}
	}


}
