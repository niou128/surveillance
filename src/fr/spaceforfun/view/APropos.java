package fr.spaceforfun.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.spaceforfun.ressources.Constante;

public class APropos{

	private String mess;

	public APropos(){
		ImageIcon img = null;
		try {
			System.out.println(getClass().getResource(Constante.avatar));
			img = new ImageIcon(getClass().getResource(Constante.avatar));
		}
		catch(java.lang.NullPointerException e)
		{
			System.out.println("Erreur avec l'image "+e.getMessage());
		}
		mess = "Programme surveillant la modification\nd'un fichier ou d'un dossier\n";
		mess += "version "+Constante.version+"\n";
		mess += "Auteur : "+Constante.auteur;
		JOptionPane.showMessageDialog(null, mess, "A propos", JOptionPane.INFORMATION_MESSAGE,img);
	}

}
