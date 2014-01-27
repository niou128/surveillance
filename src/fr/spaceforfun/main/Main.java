package fr.spaceforfun.main;

import java.awt.EventQueue;
import fr.spaceforfun.view.Fenetre;

/**
 *@author Sebastien Bernard
 * Programme surveillant la modification d'un dossier
 * version 4.0
 */


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Fenetre();
		/**
		 * Launch the application.
		 */
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

}
