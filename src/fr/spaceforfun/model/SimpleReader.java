package fr.spaceforfun.model;

import java.io.FileInputStream;
import java.util.Scanner;

public class SimpleReader {

	private Scanner sc;

	public void open(FileInputStream file)
	{
		sc = new Scanner(file, "UTF-8");
	}

	public void close()
	{
		sc.close();
	}

	public boolean myHasNext()
	{
		return sc.hasNext();
	}
	public String read()
	{
		return sc.hasNext() ? sc.nextLine() : "";

	}

}
