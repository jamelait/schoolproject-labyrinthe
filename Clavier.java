import java.io.*;

class Clavier {
	public static String lireString ()   // lecture d'une chaine
	{ String ligne_lue = null ;
	try
	{ InputStreamReader lecteur = new InputStreamReader (System.in) ;
	BufferedReader entree = new BufferedReader (lecteur) ;
	ligne_lue = entree.readLine() ;
	}
	catch (IOException err)
	{ System.exit(0) ;
	}
	return ligne_lue ;
	}
	public static float lireFloat ()   // lecture d'un float
	{ float x=0 ;   // valeur a lire
	try
	{ String ligne_lue = lireString() ;
	x = Float.parseFloat(ligne_lue) ;
	}
	catch (NumberFormatException err)
	{ System.out.println ("*** Erreur de donnee ***") ;
	System.exit(0) ;
	}
	return x ;
	}
	public static double lireDouble ()   // lecture d'un double
	{ double x=0 ;   // valeur a lire
	try
	{ String ligne_lue = lireString() ;
	x = Double.parseDouble(ligne_lue) ;
	}
	catch (NumberFormatException err)
	{ System.out.println ("*** Erreur de donnee ***") ;
	System.exit(0) ;
	}
	return x ;
	}
	public static int lireInt ()         // lecture d'un int
	{ int n=0 ;   // valeur a lire
	try
	{ String ligne_lue = lireString() ;
	n = Integer.parseInt(ligne_lue) ;
	}
	catch (NumberFormatException err)
	{ System.out.println ("*** Erreur de donnee ***") ;
	//System.exit(0) ;
	}
	return n ;
	}
	public static int lireEntierDansIntervalle(String msg, int inf, int sup){
		boolean correct=false;
		int nbre=0;
		while (! correct){
			System.out.print(msg);
			nbre=Clavier.lireInt();
			correct=(nbre>=inf && nbre<=sup);
		}
		return nbre;
	}

}

