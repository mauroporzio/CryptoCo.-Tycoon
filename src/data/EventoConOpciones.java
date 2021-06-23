package data;

import java.util.ArrayList;

public class EventoConOpciones extends Evento
{
	private static final long serialVersionUID = 3L;
	private ArrayList<String> opciones; //almacén para los strings de las opciones del evento, se utilizan arreglos paralelos para almacenar las opcion y los datos correspondientes
	private ArrayList<Integer> datos; //almacén para los datos correspondientes con las opciones, se utilizan arreglos paralelos para almacenar las opcion y los datos correspondientes
	
	public EventoConOpciones(String nombre, String descripcion, boolean activo, ArrayList<String> opciones, ArrayList<Integer> datos) 
	{
		super(nombre, descripcion, activo);
		this.opciones = opciones;
		this.datos = datos;
	}
	
	public ArrayList<String> getArrayOpciones()
	{
		return this.opciones;
	}
	
	public ArrayList<Integer> getArrayDatos()
	{
		return this.datos;
	}
	
	public void setValor(double valor) {
		super.setValor(valor);
	}
}
