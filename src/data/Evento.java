package data;

import java.io.Serializable;

public class Evento implements Serializable
{
	private static final long serialVersionUID = 3L;
	private String nombre;
	private String descripcion;
	private boolean eventoActivo; //(true: activo; false: inactivo) al existir eventos que pueden mantenerse activos por cierto tiempo, no pueden aparecer nuevamente mientras estén activos.
	private double valor = 0;
	
	public Evento(String nombre, String descripcion, boolean activo, double valor)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.eventoActivo=activo;
		setValor(valor);
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setValor(double valor)
	{
		this.valor = valor;
	}
	
	public double getValor()
	{
		return this.valor;
	}
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	public boolean getActivo()
	{
		return this.eventoActivo;
	}
	
	public void setEventoActivo() {
		this.eventoActivo = true;
	}
}
