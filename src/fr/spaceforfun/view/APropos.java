package fr.spaceforfun.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.spaceforfun.ressources.Constante;

public class APropos{

	private String mess;

	public APropos(){
		System.out.println(getClass().getResource(Constante.son));
		System.out.println(getClass().getResource(Constante.avatar));
		ImageIcon img = new ImageIcon(getClass().getResource(Constante.avatar));
		mess = "Programme surveillant la modification\nd'un fichier ou d'un dossier\n";
		mess += "version "+Constante.version+"\n";
		mess += "Auteur : "+Constante.auteur;
		JOptionPane.showMessageDialog(null, mess, "A propos", JOptionPane.INFORMATION_MESSAGE,img);
	}

}
