class Coordonnees {
/*
 * Gere la saisie des coordonnees et leur affichage sous forme lisible
*/
	public static int[] coordonneesEnIndice(String coor) {
	/*
	 * Convertit des coordonnees en indices et retourne un tableau de ces indices
	*/
		int[] tabCoor = new int[2];
		
		coor = coor.toUpperCase();
		tabCoor[0] = ((int)coor.charAt(0)) - 65;
		tabCoor[1] = ((int)coor.charAt(1)) - 49;

		return tabCoor;
	}
	
	public static boolean contientErreurs(String coorSaisies, String coorMin, String coorMax) {
	/*
	 * Retourne vrai si les coordonnees contiennent des erreurs
	*/
		
		boolean erreurs = false;
		
		// indice correspondant aux coordonnees
		int[] tabCoorMin;
		int[] tabCoorMax;
		int[] tabCoorSaisies;
		
		// on met les coordonnees recus en majuscule
		coorSaisies = coorSaisies.toUpperCase();
		coorMin = coorMin.toUpperCase();
		coorMax = coorMax.toUpperCase();
		
// si coor ne fait pas deux caracteres de long
		if (coorSaisies.length() != 2) {
			System.out.println("\tErreur : Les coordonnees doivent etre constitues de deux caracteres.");
			erreurs = true;
		}
		else {
// si le premier caractere de coor n'est pas une lettre ou le deuxieme, un chiffre ...
			if (!coorSaisies.substring(0,1).matches("[A-Z]") || !coorSaisies.substring(1,2).matches("[0-9]")) {
				System.out.println("\tErreur : Les coordonnees doivent etre de ce format : " + coorMin);
				erreurs = true;
			}
			else {
// si les coordonnees sont en dehors de l'intervalle
				tabCoorMin = coordonneesEnIndice(coorMin);
				tabCoorMax = coordonneesEnIndice(coorMax);
				tabCoorSaisies = coordonneesEnIndice(coorSaisies);
				
				if (	tabCoorSaisies[0] < tabCoorMin[0] ||			tabCoorSaisies[1] < tabCoorMin[1] ||			tabCoorSaisies[0] > tabCoorMax[0] ||			tabCoorSaisies[1] > tabCoorMax[1]	) {
					System.out.println("\tErreur: Les coordonnees doivent se situer entre " + coorMin + " et " + coorMax);
					erreurs = true;
				}
			}
		}

		return erreurs;	
	}
	
	public static String indiceEnCoordonnees(String ind) {
	/* 
	 * Retourne un String representant deux indices sous forme lisible 
	 * exemple : recoit 11
	 * retourne B2
	 */
		/*
		String coor = "";
		int ascii;
		ascii = (int)ind.charAt(0) + 17;
		coor += (char)ascii;
		ascii = (int)ind.charAt(1) + 1;
		coor += (char)ascii;
		return coor;
		*/
		return ((char)((int)ind.charAt(0) + 17)) +""+ ((char)((int)ind.charAt(1) + 1));
	}
}
