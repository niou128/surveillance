package fr.spaceforfun.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.spaceforfun.model.Affichage;
import fr.spaceforfun.model.FichSelect;
import fr.spaceforfun.model.JouerSon;
import fr.spaceforfun.model.Updater;
import fr.spaceforfun.view.*;

/**
 * 
 * @author Sébastien Bernard
 *
 */

public class Controller implements ActionListener{

	Fenetre fen;

	public Controller(Fenetre fen)
	{
		this.fen = fen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ouvrir":
			try
			{
				fen.getRecup().setDir(((new FichSelect()).ouvrir()).getPath());
				fen.getLabel().setText(Affichage.chemin(fen.getRecup().getDir()));
			}
			catch(java.lang.NullPointerException ex){}
			fen.getAffiche().repaint();
			break;
			
		case "ouvrep":
			try{
				fen.getRecup().setDir(((new FichSelect()).ouvrirRep()).getPath());
				fen.getLabel().setText(Affichage.chemin(fen.getRecup().getDir()));
			}
			catch(java.lang.NullPointerException ex){}
			fen.getAffiche().repaint();
			break;
			
		case "quitter":
			System.exit(0);
			break;
			
		case "changer":
			try{
				JouerSon.setSon(((new FichSelect()).ouvrir()).getPath());
			}
			catch(java.lang.NullPointerException ex){}
			break;
			
		case "aPropos":
			new APropos();
			break;
			
		case "chooseevt":
			try
			{
				fen.getEdevt().setEvt(((new FichSelect()).ouvrevt()).getPath());
				fen.getEdevt().lecture();
			}
			catch(java.lang.NullPointerException ex)
			{
				System.out.println(ex.getMessage());
			}
			fen.getContenuevt().revalidate();
			fen.getOnglet().setSelectedIndex(1);
			fen.getApp().setEnabled(true);
			break;
			
		case "app":
			fen.getEdevt().ecriture();
			break;
			
		case "maj":
			new Updater();
			break;
		}

	}

}
