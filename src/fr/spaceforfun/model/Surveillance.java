package fr.spaceforfun.model;


import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;





import fr.spaceforfun.view.Fenetre;

/**
 * Classe qui sert à surveiller un dossier
 * @author Sébastien Bernard
 *
 */
public class Surveillance extends TimerTask {

	private File dossierSurveille = null; //dossier qui sera surveillé initialisé à null
	private long dateModif1;
	private long dateModif2;
	
	private String ModifTexte="";
	private Panneau pan = new Panneau();
	
	private Timer timer;
	private Date heure;
	private Thread t;

	/**
	 * Constructeur de la classe
	 */
	public Surveillance(){

	}


	/**
	 * défini le dossier à surveiller
	 * @param path le chemin du dossier à surveiller
	 */
	public void setDir(String path){
		dossierSurveille = new File(path);
	}

	public String getDir(){
		return dossierSurveille.getPath();
	}

	/**
	 * initialise la surveillance
	 * Affecte la date de la derière modification à dateModif1
	 */
	public void init(){
		dateModif1 = dossierSurveille.lastModified();
	}

	/**
	 * Compare la date actuelle avec la date de modification.
	 */
	public void comparaison(){
		dateModif2 = dossierSurveille.lastModified();
		if(dateModif1<dateModif2){
			heure  = new Date();
			t = new Thread(new JouerSon());
			t.start();
			ModifTexte+="Modification le "+Affichage.convertDate(heure)+" à "+Affichage.convertHeure(heure)+"<br/>";
			Fenetre.setModifTexte("<html>"+ModifTexte+"</html>");
			pan.repaint();
			dateModif1=dateModif2;
			
			
			
		}
	}

	/**
	 * méthode qui démarre un timer pour surveiller en permancence la modification du dossier
	 */
	public void activation(){

		timer = new Timer();
		timer.schedule (new TimerTask() {
			public void run()
			{
				//System.out.printf ("%tR\n", new Date());
				comparaison();
			}
		}, 0, 1000);
	}

	/**
	 * Méthode qui permet d'arrêter le timer
	 */
	public void arret(){
		timer.cancel();
	}


	@Override
	public void run() {
		
	}


public Panneau getPan(){
	return pan;
}

}
