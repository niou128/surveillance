package fr.spaceforfun.model;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import fr.spaceforfun.ressources.Constante;

public class Updater {

	private String xmlPath = "http://www.spaceforfun.fr/surveillance/maj.xml";

	private Document xmlDocument = null;

	public Updater()
	{
		update();
	}
	
	public void update() {
		ArrayList<String> versions = new ArrayList<String>();

		try
		{
			URL xmlUrl = new URL (xmlPath);
			URLConnection urlConnection = xmlUrl.openConnection();
			urlConnection.setUseCaches(false);

			urlConnection.connect();

			InputStream stream = urlConnection.getInputStream();

			SAXBuilder sxb = new SAXBuilder();

			try
			{
				xmlDocument = sxb.build(stream);
			}
			catch (JDOMException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}

			Element racine = xmlDocument.getRootElement();

			List listVersions = racine.getChildren("version");
			Iterator iteratorVersions = listVersions.iterator();

			while (iteratorVersions.hasNext()) {
				Element version = (Element)iteratorVersions.next();
				Element elementNom = version.getChild("nom");

				int choice;
				Object[] options = {"oui", "non"};
				System.out.println("version locale : "+Constante.version);
				System.out.println("version serveur : "+elementNom.getText());
				if(!elementNom.getText().equals(Constante.version))
				{
					choice = JOptionPane.showOptionDialog(null, "Une nouvelle version est disponible ("+elementNom.getText()+"), voulez-vous la télécharger ?", "Mise à jour", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					System.out.println("choice : "+choice);
					if(choice == 0)
					{
						URI uri = URI.create("http://www.spaceforfun.fr/index.php?module=creations&action=creations");
						Desktop.getDesktop().browse(uri);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Vous avez la dernière version du logiciel");
				versions.add(elementNom.getText());
			}

			Collections.sort(versions);
		}
		catch(MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
