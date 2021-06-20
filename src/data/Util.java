package data;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Util 
{
	public Util(){}
	
	public ArrayList<Evento> leerEventos(String nombreArchivo)
	{
		ArrayList<Evento> arrayEventos = new ArrayList<Evento>();

		try 
		{
			FileInputStream fis = new FileInputStream(nombreArchivo);
			
			try (ObjectInputStream ois = new ObjectInputStream(fis))
			{
				Integer eventCount = ois.readInt();
				
				for (int i=0; i < eventCount; i++)
				{
					arrayEventos.add((Evento) ois.readObject());
				}
			}
			catch (EOFException e)
			{
				System.out.println("<<< Fin del Archivo >>>");
			}
			catch (IOException e)
			{
				System.out.println("IOEXCEPTION:" + e.getMessage());
			}
			catch (ClassNotFoundException e)
			{
				System.out.println("CLASSNOTFOUND:" + e.getMessage());
			}
		} 
		catch (FileNotFoundException e1) 
		{
			System.out.println("No se encontro el archivo");
		}
		
		return arrayEventos;
	}
}
