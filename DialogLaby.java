import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DialogLaby extends JDialog implements ActionListener {

	private JRadioButton j = new JRadioButton("Jaune");
	private JRadioButton b = new JRadioButton("Bleu");
	private JRadioButton v = new JRadioButton("Vert");
	private JRadioButton r = new JRadioButton("Rouge");
	private ArrayList<JRadioButton> listeCoul = new ArrayList<JRadioButton>();
	
	private JTextField nom = new JTextField(20);
	private JLabel nomLabel = new JLabel();
	
	private JPanel panelCouleur = new JPanel();
	
	public DialogLaby() {
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // la croix en haut a gauche devient inactive
		
		// le panel pour le nom
		JPanel panelNom = new JPanel();
		panelNom.add(nomLabel);
		panelNom.add(nom);
		
		// le panel pour la couleur
		j.setSelected(true);
		ButtonGroup bgCoul = new ButtonGroup();
		bgCoul.add(j);
		bgCoul.add(b);
		bgCoul.add(v);
		bgCoul.add(r);
		listeCoul.add(j);
		listeCoul.add(b);
		listeCoul.add(v);
		listeCoul.add(r);
		
		//  le panel pour lles boutons
		JPanel panelBoutons = new JPanel();
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Quitter");
		valider.addActionListener(this);
		annuler.addActionListener(this);
		panelBoutons.add(valider);
		panelBoutons.add(annuler);
		
		// le panel principal, qui contiendra les deux autres
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(panelNom);
		p.add(panelCouleur);
		p.add(panelBoutons);
		
		this.setContentPane(p);
	}
	
	/*
	* Retourne le nom saisie dans cette boite de dialogue
	*/	
	public String getNom() {
		return nom.getText();
	}
	
	/*
	* Retourne la premiere lettre de la couleur choisie
	*/
	public char getCoul() {
		char coul = 'x';
		
		// on teste chaque bouton radio s'il est selectionne
		// s'il l'est, on le supprime de la liste des boutons radio
		if (j.isSelected()) {
			coul = 'j';
			listeCoul.remove(j);
		}
		else if (b.isSelected()) {
			coul = 'b';
			listeCoul.remove(b);
		}
		else if (v.isSelected()) {
			coul = 'v';
			listeCoul.remove(v);
		}
		else if (r.isSelected()) {
			coul = 'r';
			listeCoul.remove(r);
		}
		// s'il reste des boutons radio, on selectionne le premier bouton
		if (!listeCoul.isEmpty()) {
			listeCoul.get(0).setSelected(true);
		}
		return coul;		
	}
	
	/*
	* Demande le nombre de joueurs et renvoie ce nombre
	*/
	public Integer askNbJoueurs() {
		
		Integer[] tabNbJoueurs = {1,2,3,4};
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
		// affichage de la boite de dialogue et affection du choix a la variable nb
		Integer nb = (Integer)jop.showInputDialog(null,"Combien de joueurs ?","Nombre de joueurs",
		JOptionPane.QUESTION_MESSAGE,
		null,
		tabNbJoueurs,
		tabNbJoueurs[0]);
		
		return nb;
	}

	/*
	* Demande le nom et la couleur d'un joueur
	*/
	public void askNomCoul(int num) {
		
		nomLabel.setText("Nom et couleur du joueur " + (num+1) + " :");
		nom.setText("Joueur " +(num+1)); // valeur par default du champs de texte
		nom.selectAll(); // on selectionne le contenu du champs de texte
		nom.requestFocus(); // on peut saisir un nom sans cliquer dans le champs
		
		// ajout des boutons radios au panelCouleur
		panelCouleur.removeAll();
		for (int i = 0 ; i < listeCoul.size() ; i++) {
			panelCouleur.add(listeCoul.get(i));
		}
		
		setSize(300,250);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
	
	public static boolean question(String message, String titre) {
		JOptionPane jop = new JOptionPane();
		int option = jop.showConfirmDialog(null, message, titre, JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE);
			
		if(option == JOptionPane.OK_OPTION)
			return true;
		else
			return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Valider") {
			setVisible(false);
		}
		else if (e.getActionCommand() == "Quitter")  {
			System.exit(0);
		}
	}
	
}
