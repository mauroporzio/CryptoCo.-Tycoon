package data;

import java.io.Serializable;

public class Evento implements IProbabilidad, Serializable
{
	private static final long serialVersionUID = 3L;
	private String nombre;
	private String descripcion;
	private boolean eventoActivo; //(true: activo; false: inactivo) al existir eventos que pueden mantenerse activos por cierto tiempo, no pueden aparecer nuevamente mientras estén activos.
	
	
	
	public Evento(String nombre, String descripcion, boolean activo)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.eventoActivo=activo;
	}
	
	
	@Override
	public int calcularProbabilidad() { return (int)Math.floor(Math.random()*(10-0+1)+0)*10; }	//Devuelve un numero entre 0 y 100 en saltos de 10
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	
	public boolean getActivo()
	{
		return this.eventoActivo;
	}
}
