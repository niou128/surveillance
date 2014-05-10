package fr.spaceforfun.model;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe qui permet de choisir un fichier ou un dossier
 * @author Sébastien BERNARD
 *
 */
public class FichSelect implements FileFilter{
	JFileChooser chooser = null;

	/**
	 * Constructeur de la classe. Initilalise un JFileChooser
	 */
	public FichSelect() {
		try {
			chooser = new JFileChooser();
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf); //permet d'adapter le style de la fenêtre à l'OS
			chooser.updateUI();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(FichSelect.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(FichSelect.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(FichSelect.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(FichSelect.class.getName()).log(Level.SEVERE, null, ex);
		}  
	}

	/**
	 * m��thode qui permet de choisir un fichier
	 * @return le chemin vers le fichier
	 */
	public File ouvrir(){
		File f = null;
		do{
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				f = chooser.getSelectedFile();
				chooser.setCurrentDirectory(f);

			}
		} while(f.isDirectory());
		
		return f;
	}

	/**
	 * Méthode qui permet de choisir un répertoire
	 * @return le chemin vers le répertoire
	 */
	public File ouvrirRep(){
		File f = null;
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			f = chooser.getSelectedFile();
			chooser.setCurrentDirectory(f);

		}
		return f;
	}
	
	public File ouvrevt(){
		File f = null;
		do{
			FileNameExtensionFilter imagesFilter = new FileNameExtensionFilter("evt", "evt");	
			chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
			chooser.addChoosableFileFilter(imagesFilter);
			chooser.setFileFilter(imagesFilter);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				f = chooser.getSelectedFile();
				chooser.setCurrentDirectory(f);

			}
		} while(f.isDirectory());
		
		return f;
	}

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return false;
	}


}
