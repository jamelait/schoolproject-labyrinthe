import java.awt.*;

class Curseur {
/*
 * classe de curseurs personnalises
*/

	final static int TOURNER = 0;
	final static int MAIN = 1;
	final static int MAIN2 = 2;
	final static java.awt.Point HTOURNER = new java.awt.Point(7,10);
	final static java.awt.Point HMAIN = new java.awt.Point(13,6);
	final static java.awt.Point HMAIN2 = new java.awt.Point(13,6);
		
	final static String REP = "./images/curseurs/";
	
	private Toolkit tk = Toolkit.getDefaultToolkit();

	
	public Cursor getCurseur(int t) {
	
		Cursor c = null;
		
		switch(t) {
			case TOURNER :
				c = tk.createCustomCursor(tk.getImage(REP + "tourner.gif"), HTOURNER, "tourner");
				break;
			case MAIN :
				c = tk.createCustomCursor(tk.getImage(REP + "main.gif"), HMAIN, "main");
				break;
			case MAIN2 :
				c = tk.createCustomCursor(tk.getImage(REP + "main2.gif"), HMAIN2, "main2");
				break;
		}
		return c;
	}
}