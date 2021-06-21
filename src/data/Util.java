package data;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Util 
{
	public static final String nomArchiPartidasDisp = "partidasDisponibles.dat"; // nombre donde se guardan todas las partidas disponibles para cargar.
	
	public Util(){}
	
	public ArrayList<Evento> leerEventos(String nombreArchivo) // devuelve un array list con todos los eventos cargados en el archivo.
	{
		ArrayList<Evento> arrayEventos = new ArrayList<Evento>();

		try 
		{
			FileInputStream fis = new FileInputStream(nombreArchivo);
			
			try
			{
				ObjectInputStream ois = new ObjectInputStream(fis);
				Integer eventCount = ois.readInt();
				
				ois.close();
				fis.close();
				
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
	
	public boolean guardarPartida(Partida partida) // devuelve true si se guardo correctamente el archivo o false si no.
	{
		boolean guardado = false;
		
		try 
		{
			String nombreArchivo = partida.getEmpresa().getNombre().replaceAll("\\s", "") + ".dat";
			FileOutputStream fosPartida = new FileOutputStream(nombreArchivo); // fos de la partida a guardar.
			
			try
			{
				ObjectOutputStream oos = new ObjectOutputStream(fosPartida);
				oos.writeObject(partida); // se guarda la partida
				guardado = true;
				
				oos.close();
				fosPartida.close();
				
				ArrayList<String> partidasDisp = partidasDisponibles(); // traemos el arreglo de las partidas que ya estan guardadas 
				int i=0;
				
				if (!partidasDisp.isEmpty()) // si hay partidas ya guardadas
				{
					for (int p=0; p < partidasDisp.size(); p++)
					{
						if (partidasDisp.get(p).equals(partida.getEmpresa().getNombre()))
						{
							i = p; // guardo la pos donde ya esta esa partida guardada.
						}
					}
				}
				
				if (partidasDisp.isEmpty() || !partidasDisp.get(i).equals(partida.getEmpresa().getNombre())) // si no se encuentra la partida
				{
					FileOutputStream fosDisp = new FileOutputStream(nomArchiPartidasDisp, true);
					
					try (ObjectOutputStream oosDisp = new ObjectOutputStream(fosDisp)) // la agrego a al archivo que guarda las partidas disp.
					{
						oosDisp.writeObject(partida.getEmpresa().getNombre());
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
			catch (IOException e) 
			{
				System.out.println("ERROR: No se guardo la partida correctamente");
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e1) 
		{
			System.out.println("ERROR: No se guardo la partida correctamente");
			System.out.println("No se encontro en archivo indicado");
		}
		
		return guardado;
	}

	public ArrayList<String> partidasDisponibles() // devuelve un array list con los nombres de las partidas que estan disponibles para cargar.
	{
		ArrayList<String> partidas =  new ArrayList<String>();
		boolean encontrado = true;

		try 
		{
			FileInputStream fis = new FileInputStream(nomArchiPartidasDisp);
			
			// no se cierran los fis y ois porque sino no funciona con el while; SE DEBE CERRAR AL TERMINAR EL PROGRAMA.
			
			while (encontrado)
			{
				try
				{
					ObjectInputStream ois = new ObjectInputStream(fis);
					partidas.add((String) ois.readObject());
					
				}
				catch (EOFException e)
				{
					encontrado = false;
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
		} 
		catch (FileNotFoundException e1) 
		{
			//System.out.println("No se encontro el archivo " + nomArchiPartidasDisp);
		}
		
		return partidas;
	}
	
	public Partida cargarPartida(String nombreEmpresa) // devuelve la partida correspondeinte al nombre de empresa dado, si no la encuentra devuelve null.
	{
		Partida partida = null;
		boolean encontrado = true;

		try 
		{
			String nombreArchivo = nombreEmpresa.replaceAll("\\s", "") + ".dat";
			FileInputStream fis = new FileInputStream(nombreArchivo);
			
			while (encontrado)
			{
				try
				{
					ObjectInputStream ois = new ObjectInputStream(fis);
					partida = (Partida) ois.readObject();
					
					if (partida.getEmpresa().getNombre().equals(nombreEmpresa))
					{
						encontrado = false;
						
						try 
						{
							ois.close();
							fis.close();
						} 
						catch (IOException e1) 
						{
							e1.printStackTrace();
						}
					}
					else
					{
						partida = null;
					}
				}
				catch (EOFException e)
				{
					encontrado = false;
					
					try 
					{
						fis.close();
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					
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
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("No se encontro el archivo de partida");
		}
		
		return partida;
	}
	
	
}
