package fr.spaceforfun.model;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class Affichage {


/**
 * Constructeur vide
 */
	public Affichage(){}

	private static String test(File dossierSurveille){
		if(dossierSurveille.isDirectory()){
			return "dossier";
		}
		else return "fichier";
	}
	
	/**
	 * Méthode qui affiche l'heure et la date de modigication
	 * @param dossierSurveille Type File
	 * @param dossier le chemin vers le fichier ou dossier surveillé
	 * @param d la date
	 * @return un string
	 */
	public static String modification(File dossierSurveille, String dossier, Date d){
		String file = test(dossierSurveille);
		
		String modif = file+" "+dossier+" modifié le "+convertDate(d)+" à "+convertHeure(d);
		JOptionPane.showMessageDialog(null, modif, "Modification de fichier", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(modif);
		return modif;
	}

	
	
	/**
	 * Affiche le chemin du fichier ou dossier
	 * @param chemin le chemin en question
	 */
	public static String chemin(String chemin){		
		return "Surveillance : "+chemin;
		
	}

	//Choix de la langue française
	Locale locale = Locale.getDefault();

	/**
	 * convertit une date au format jj-MM-aaaa
	 * @param d une date
	 * @return la date convertit
	 */
	public static String convertDate(Date d){
		//Definition du format utilise pour les dates
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dat = dateFormat.format(d);
		return dat;
	}

	/**
	 * Convertit une date en heures et minutes
	 * @param d une date
	 * @return la date convertit
	 */
	public static String convertHeure(Date d){
		//Definition du format utilise pour les dates
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String dat = dateFormat.format(d);
		return dat;
	}

}
