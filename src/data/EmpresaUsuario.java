package data;

import java.util.ArrayList;
import java.util.HashMap;

public class EmpresaUsuario extends Empresa
{
	static final int patInicFac = 200000;  //--
	static final int patInicMed = 100000;  //conjunto de constantes que se utilizan para, según la dificultad, establecer un patrimonio inicial de la empresa.
	static final int patInicDif = 50000;   //--
	static final int clientesInicFac = 500;  //--
	static final int clientesInicMed = 200;  //conjunto de constantes que se utilizan para, segun la dificultad, establecer la cantidad de clientes con la que comienza la empresa.
	static final int clientesInicDif = 100;  //--
	static final double comInicialFac = 7;  //--
	static final double comInicialMed = 5;  //monto que cobra la empresa por cada transaccion.
	static final double comInicialDif = 3;  //--
	
	
	
	private ArrayList<Evento> histEventos; //se utiliza para almacenar un historial de los eventos por los que pasó la empresa a lo largo del juego.
	private ArrayList<Modificador> modificadores; //Se utiliza para almacenar los modificadores y los eventos activos que posee la empresa.
	private int nClientes; //Variable donde se almacena el numero de clientes que posee la empresa.
	private double comision; //Comision que cobra la empresa a cada cliente por cada transaccion. Fuente principal de ingresos.
	HashMap<Integer, Double> historialPatrimonio; //Key = mes ; Value= patrimonio en dicho mes. Se utiliza para almacenar el patrimonio que tuvo la empresa en cada mes. Se muestra al final del juego.
	
	public EmpresaUsuario(int dificultad, String nombre, String CEO)
	{
		histEventos = new ArrayList<Evento>();
		modificadores = new ArrayList<Modificador>();
		historialPatrimonio = new HashMap<Integer, Double>();
		setNombre(nombre);
		setCEO(CEO);
		
		switch(dificultad)
		{
			case 1:  //Facil
			{
				setPatrimonio(patInicFac);
				setNClientes(clientesInicFac);
				setComision(comInicialFac);
			}
			case 2:  //Medio
			{
				setPatrimonio(patInicMed);
				setNClientes(clientesInicMed);
				setComision(comInicialMed);
			}
			case 3:  //Dificil
			{
				setPatrimonio(patInicDif);
				setNClientes(clientesInicDif);
				setComision(comInicialDif);
			}
		}
		
	}
	
	public void setNClientes(int nClientes)
	{
		this.nClientes = nClientes;
	}
	
	public void setComision(double comision)
	{
		this.comision = comision;
	}
	
	public HashMap<Integer, Double> getHistorialPatrimonio(){
		return this.historialPatrimonio;
	}
	
	public Double mesEspecificoPatrimonio(int n){
		return historialPatrimonio.get(n);
	}
	
	
	
	
	
	
	
	
	
}
