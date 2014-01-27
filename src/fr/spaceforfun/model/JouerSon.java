package fr.spaceforfun.model;

import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class JouerSon implements Runnable{
	
	private static File son = null;
	
	public static void setSon(String path){
		son = new File(path);
	}
	
	public void run(){
		File f = new File("Ressources/alarm.WAV");
		if(son != null){
			Sound player = new Sound(son.getPath());
			InputStream stream = new ByteArrayInputStream(player.getSamples());
			player.play(stream);
		}
		
		if(son == null && f.exists()){	
			Sound player = new Sound("Ressources/alarm.WAV");
			InputStream stream = new ByteArrayInputStream(player.getSamples());
			player.play(stream);
		}
		else
			Toolkit.getDefaultToolkit().beep(); //joue le son d'alerte standard
	}
}
