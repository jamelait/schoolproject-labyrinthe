import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ReglesDuJeu extends JFrame implements ActionListener {
	
	final static String REP = "./images/regles/";
	private JPanel pb;
	private JScrollPane pl;
	private JLabel l;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	

	public ReglesDuJeu() {
		l = new JLabel();
		pb = new JPanel();
		pl = new JScrollPane(l);

		b1 = new JButton("Page 1");
		b2 = new JButton("Page 2");
		b3 = new JButton("Page 3");
		b4 = new JButton("Page 4");
		
		pb.add(b1);
		pb.add(b2);
		pb.add(b3);
		pb.add(b4);

		add(pb,BorderLayout.NORTH);
		add(pl,BorderLayout.CENTER);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		l.setIcon(new ImageIcon(REP + "page1.gif"));

		setTitle("Regles Du Jeu");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(461,702);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Page 1") {
			l.setIcon(new ImageIcon(REP + "page1.gif"));
		}
		else if (e.getActionCommand() == "Page 2") {
			l.setIcon(new ImageIcon(REP + "page2.gif"));
		}
		else if (e.getActionCommand() == "Page 3") {
			l.setIcon(new ImageIcon(REP + "page3.gif"));
		}
		else if (e.getActionCommand() == "Page 4") {
			l.setIcon(new ImageIcon(REP + "page4.gif"));
		}
	}
}