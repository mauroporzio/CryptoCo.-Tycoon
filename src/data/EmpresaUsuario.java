package data;


import java.util.ArrayList;
import java.util.HashMap;

public class EmpresaUsuario extends Empresa
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final double patInicFac = 10000;  //--
	static final double patInicMed = 5000;  //conjunto de constantes que se utilizan para, según la dificultad, establecer un patrimonio inicial de la empresa.
	static final double patInicDif = 2000;   //--
	static final int clientesInicFac = 500;  //--
	static final int clientesInicMed = 200;  //conjunto de constantes que se utilizan para, segun la dificultad, establecer la cantidad de clientes con la que comienza la empresa.
	static final int clientesInicDif = 100;  //--
	static final double comInicialFac = 7;  //--
	static final double comInicialMed = 5;  //monto que cobra la empresa por cada transaccion.
	static final double comInicialDif = 3;  //--
	static final double aumentoClientesFac = 0.15;
	static final double aumentoClientesMed = 0.10;
	static final double aumentoClientesDif = 0.05;
	
	
	private ArrayList<Evento> eventosActivos; //Se almacenan los eventos del mes anterior para evitar que aparezcan dos veces seguidas, y los eventos que se encuentran activos modificando los ingresos.
	//private ArrayList<Modificador> modificadores; //Se utiliza para almacenar los modificadores y los eventos activos que posee la empresa.
	private int nClientes; //Variable donde se almacena el numero de clientes que posee la empresa.
	private double comision; //Comision que cobra la empresa a cada cliente por cada transaccion. Fuente principal de ingresos.
	HashMap<Integer, Double> historialPatrimonio; //Key = mes ; Value= patrimonio en dicho mes. Se utiliza para almacenar el patrimonio que tuvo la empresa en cada mes. Se muestra al final del juego.
	
	
	public EmpresaUsuario(int dificultad, String nombre, String CEO)
	{
		eventosActivos = new ArrayList<Evento>();
		//modificadores = new ArrayList<Modificador>();
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
				break;
			}
			case 2:  //Medio
			{
				setPatrimonio(patInicMed);
				setNClientes(clientesInicMed);
				setComision(comInicialMed);
				break;
			}
			case 3:  //Dificil
			{
				setPatrimonio(patInicDif);
				setNClientes(clientesInicDif);
				setComision(comInicialDif);
				break;
			}
		}
		
	}
	
	public void setNClientes(int nClientes)
	{
		this.nClientes = nClientes;
	}
	
	public int getNClientes()
	{
		return this.nClientes;
	}
	
	public void setComision(double comision)
	{
		this.comision = comision;
	}
	
	public double getComision()
	{
		return this.comision;
	}
	
	public double calcularGananciaClientes()
	{
		return getComision()*getNClientes();
	}
	
	public HashMap<Integer, Double> getHistorialPatrimonio(){
		return this.historialPatrimonio;
	}
	
	public void setHistorialPatrimonio(int mes, Double value) {
		this.historialPatrimonio.put(mes, value);
	}
	
	public Double mesEspecificoPatrimonio(int n){
		return historialPatrimonio.get(n);
	}
	
	public void agregarEventoActivo(Evento evento) {
		this.eventosActivos.add(evento);
	}
	
	public Evento getEventoActivo(int pos){
		return eventosActivos.get(pos);
	}
	
	public int contarEventosActivos() {
		return eventosActivos.size();
	}
	
	public void aumentarClientes(int dificultad)
	{
		if(dificultad == 1)
		{
			this.nClientes = this.nClientes + (int)(this.nClientes * aumentoClientesFac);
		}
		else if(dificultad == 2)
		{
			this.nClientes = this.nClientes + (int)(this.nClientes * aumentoClientesMed);
		}
		else if(dificultad == 3)
		{
			this.nClientes = this.nClientes + (int)(this.nClientes * aumentoClientesDif);
		}
	}
	
	public ArrayList<Evento> getArrayEventosActivos()
	{
		return this.eventosActivos;
	}
	
	public int calcularNuevosClientes(int dificultad){
		int aumento = 0;
		switch(dificultad){
			case 1:
				aumento =  (int)(this.nClientes * aumentoClientesFac);
			case 2:
				aumento = (int)(this.nClientes * aumentoClientesMed);
			case 3:
				aumento = (int)(this.nClientes * aumentoClientesDif);
		}
		return aumento;
	}
	
}
