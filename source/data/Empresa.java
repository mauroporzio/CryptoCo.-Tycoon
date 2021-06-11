package data;

public abstract class Empresa
{
	private String nombre; //nombre de la empresa
	private String CEO; //nombre del jugador
	private double patrimonio; //patrimonio de la empresa
	
	
	
	
	public Empresa(String nombre, String CEO, double patrimonio)
	{
		setNombre(nombre);
		setCEO(CEO);
		setPatrimonio(patrimonio);
	}

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
	
	
}
