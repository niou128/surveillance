package fr.spaceforfun.model;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;

import java.util.Date;

/**
 * Class qui gère le panneau principal du logiciel
 * @author Sébastien Bernard
 *
 */
public class Panneau extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actif = "";
	private String date1="";
	private String modif="";
	private Color c;

	/**
	 * redéfini l'affichage
	 * @param s
	 */
	public void SetString(String s){
		Date actuelle = new Date();
		this.actif=s;
		this.date1 = Affichage.convertDate(actuelle)+" "+Affichage.convertHeure(actuelle);
	}

	public void setModif(String s){
		this.modif=s;
	}

	public void setColor(Color c){
		this.c = c;
	}

	public void paintComponent(Graphics g){
		g.setColor(c);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		try {
			Font font = new Font(Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(
					new File("Ressources/ethnocen.ttf"))).getFamily(), Font.BOLD, 14);
			g.setFont(font);
		} catch (FontFormatException | IOException e) {
			Font font = new Font("Arial", Font.BOLD, 14);
			g.setFont(font);
			e.printStackTrace();
		}

		g.setColor(Color.red);
		g.drawString(actif, 50, 50);
		g.drawString(date1, 50, 70);

		Font font2 = new Font("Ethnocentric", Font.BOLD, 18);
		g.setFont(font2);

		g.drawString(modif, 50, 100);
	}

}
