package fr.spaceforfun.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SimpleWriter {

	private PrintWriter pw;
	
	public void open(FileOutputStream file)
	{

			pw = new PrintWriter(file);
	
	}
	
	public void open(OutputStreamWriter file)
	{
		pw  = new PrintWriter(file);
	}
	
	public void open(File file)
	{
		try {
			pw = new PrintWriter(file, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		pw.close();
	}
	
	public void write(String text)
	{
		pw.println(text);
	}
}
