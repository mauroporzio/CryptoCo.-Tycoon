package data;

import java.io.Serializable;

public abstract class Empresa implements Serializable
{
	private static final long serialVersionUID = 2L;
	private String nombre; //nombre de la empresa
	private String CEO; //nombre del jugador
	private double patrimonio; //patrimonio de la empresa
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCEO() {
		return CEO;
	}
	
	public void setCEO(String cEO) {
		CEO = cEO;
	}

	public double getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(double patrimonio) {
		this.patrimonio = patrimonio;
	}

	public boolean equals(Empresa empresa) {
		boolean a = false;
		if(empresa.nombre == this.nombre)
		{
			if(empresa.patrimonio == this.patrimonio)
			{
				a = true;
			}
		}
		return a;
	}
	
	
}
