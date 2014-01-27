package fr.spaceforfun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.spaceforfun.view.Fenetre;

public class SurveillanceListener implements ActionListener {

	Fenetre fen;
	
	public SurveillanceListener(Fenetre fen)
	{
		this.fen = fen;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			fen.getEncours().SetString("Surveillance en cours");
			fen.getEncours().repaint();
			fen.getRecup().init();
			fen.getRecup().activation();
		}
		catch(java.lang.NullPointerException ex){
			fen.getEncours().SetString("Aucun fichier n'est choisi");
			fen.getEncours().repaint();
		}
	}	

}
