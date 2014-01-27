package fr.spaceforfun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.spaceforfun.view.Fenetre;

public class StoppeListener implements ActionListener {

	Fenetre fen;
	
	public StoppeListener(Fenetre fen)
	{
		this.fen = fen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			fen.getEncours().SetString("fin de la surveillance");
			fen.getEncours().repaint();
			fen.getRecup().arret();
		}catch(java.lang.NullPointerException ex){
			fen.getEncours().SetString("Aucun fichier n'est choisi");
			fen.getEncours().repaint();
		}
	}

}
