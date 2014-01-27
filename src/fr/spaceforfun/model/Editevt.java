package fr.spaceforfun.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import fr.spaceforfun.view.Fenetre;

public class Editevt {

	private File evt;
	private Fenetre fen;
	private SimpleReader sr = new SimpleReader();
	private SimpleWriter sw = new SimpleWriter();
	private FileWriter fw;
	private BufferedWriter bw;

	public Editevt(Fenetre fen)
	{
		this.fen = fen;
	}

	public void setEvt(String path)
	{
		evt = new File(path);
	}

	public String getEvt()
	{
		return evt.getPath();
	}

	public void lecture()
	{
		String line = "";
		String str = "";
		try {
			sr.open(new FileInputStream(evt));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		while((str = sr.read()) != "")
		{
			if (sr.myHasNext())
			{
				line += str + "\n";
				//sw.write(str);
			}
			else
			{
				line += str;
				//sw.write(str);
			}
		}
		fen.getContenuevt().setText(line);
	}

	public void ecriture()
	{
		//sw.open(new FileOutputStream(evt));
		sw.open(evt);
		sw.write(fen.getContenuevt().getText());
		sw.close();
		System.out.println(fen.getContenuevt().getText());
	}

}
