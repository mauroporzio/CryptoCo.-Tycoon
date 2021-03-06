package data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util
{
	public static final String nomArchiPartidasDisp = "partidasDisponibles.dat"; // nombre donde se guardan todas las partidas disponibles para cargar.
	public static final String nombreArchiEmpresasEn = "EmpresasEnemigas.json";
	public static final String nomArchiTop10Empresas = "top10Empresas.dat"; // nombre donde se almacena el top 10 historico de empresas.
	public static final String nomArchiEventos = "eventos.dat";
	public Util(){}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Evento> leerEventos() // devuelve un array list con todos los eventos cargados en el archivo.
	{
		ArrayList<Evento> arrayEventos = new ArrayList<Evento>();

		try 
		{
			FileInputStream fis = new FileInputStream(nomArchiEventos);
			
			try
			{
				ObjectInputStream ois = new ObjectInputStream(fis);
				arrayEventos = (ArrayList<Evento>) ois.readObject();
				
				ois.close();
				fis.close();
			
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
					FileOutputStream fosDisp = new FileOutputStream(nomArchiPartidasDisp);
					
					partidasDisp.add(partida.getEmpresa().getNombre());
					
					try (ObjectOutputStream oosDisp = new ObjectOutputStream(fosDisp)) // la agrego a al archivo que guarda las partidas disp.
					{
						oosDisp.writeObject(partidasDisp);
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

	@SuppressWarnings("unchecked") // por un casteo pero esta todo checkeado en las otras funciones.
	public ArrayList<String> partidasDisponibles() // devuelve un array list con los nombres de las partidas que estan disponibles para cargar.
	{
		ArrayList<String> partidas =  new ArrayList<String>();
		
		try (FileInputStream fis = new FileInputStream(nomArchiPartidasDisp))
		{
			try (ObjectInputStream ois = new ObjectInputStream(fis))
			{
				partidas = (ArrayList<String>) ois.readObject();
			}
		}
		catch (FileNotFoundException e)
		{
			
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
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
	
	public boolean borrarPartida(String nombreEmpresa) // devuelve true si borro la partida con el nombre empresa dado, sino false.
	{
		boolean borrado = false;
		int i=0;
		
		ArrayList<String> partidasDisp = partidasDisponibles();	
		
		while (i < partidasDisp.size() && !partidasDisp.get(i).equals(nombreEmpresa))
		{
			i++;
		}
		
		if (partidasDisp.get(i).equals(nombreEmpresa))
		{
			File file = new File(getCurrentWorkingDirectory() + "\\" + nombreEmpresa + ".dat");
			
			if (file.delete())
			{
				partidasDisp.remove(i);
				
				try (FileOutputStream fosDisp = new FileOutputStream(nomArchiPartidasDisp))
				{
					try (ObjectOutputStream oosDisp = new ObjectOutputStream(fosDisp)) // la agrego a al archivo que guarda las partidas disp.
					{
						oosDisp.writeObject(partidasDisp);
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				} 
				catch (FileNotFoundException e1) 
				{
					System.out.println("ERROR: No se encontro el archivo: " + nomArchiPartidasDisp);
				} 
				catch (IOException e2) 
				{
					System.out.println(e2.getLocalizedMessage());
				}
				finally
				{
					borrado = true;
				}
			}
		}
		return borrado;
	}
	
	private static String getCurrentWorkingDirectory() 
	 {
		 String userDirectory = System.getProperty("user.dir");
	     return userDirectory;
	 }
	
	public static ArrayList<EmpresaEnemiga> cargarYOrdenarEmpresasEnemigas(int dificultad)
	{
		ArrayList<EmpresaEnemiga> empresas = new ArrayList<EmpresaEnemiga>();
		Comparator<Empresa> comparatorEmpresas = new ComparatorTop10();
		
		String contenido = "";

		try 
		{
			
			contenido = new String(Files.readAllBytes(Paths.get(nombreArchiEmpresasEn)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		JSONArray jsondata = new JSONArray(contenido);
		
		for(int i=0;i<10;i++)
		{
			JSONObject dato = new JSONObject();
			
			dato = jsondata.getJSONObject(i);
			
			
			EmpresaEnemiga empresa = new EmpresaEnemiga(dificultad, dato.getString("Nombre de la empresa"), dato.getString("CEO"));
			
			empresas.add(empresa);
		}
		
		
		
		Collections.sort(empresas, comparatorEmpresas);
		
		
		return empresas;
	}
	
	public void setTop10Empresas(Empresa empresa)
	{
		ArrayList<Empresa> top10 = getTop10Empresas();
		Comparator<Empresa> comparatorTop10 = new ComparatorTop10();
		
		if (top10.size() < 10 && !top10.contains(empresa))
		{
			top10.add(empresa);
		}
		else
		{
			int p=0;
			
			for (int i=0; i < top10.size(); i++)
			{
				if (empresa.getPatrimonio() <= top10.get(i).getPatrimonio())
				{
					p = i;
				}	
			}
				
			if (empresa.getPatrimonio() >= top10.get(p).getPatrimonio())
			{
				top10.set(p, empresa);
			}
		}
		
		Collections.sort(top10, comparatorTop10);
		
		try (FileOutputStream fos = new FileOutputStream(nomArchiTop10Empresas))
		{
			try (ObjectOutputStream oos = new ObjectOutputStream(fos))
			{
				oos.flush();
				oos.writeObject(top10);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e)
		{
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Empresa> getTop10Empresas()
	{
		ArrayList<Empresa> top10 = new ArrayList<Empresa>();
		
		try (FileInputStream fis = new FileInputStream(nomArchiTop10Empresas))
		{
			try (ObjectInputStream ois = new ObjectInputStream(fis))
			{
				top10 = (ArrayList<Empresa>) ois.readObject();
			}
		}
		catch (FileNotFoundException e)
		{
			
		}
		catch(EOFException e)
		{
			
		}
		catch (Exception e) 
		{
			//System.out.println(e.getMessage());
			
			e.printStackTrace();
		}
		
		return top10;
	}
}

	
	
	
