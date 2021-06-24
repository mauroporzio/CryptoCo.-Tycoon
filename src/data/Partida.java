package data;

import java.io.Serializable;
import java.util.ArrayList;


public class Partida implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static final String nombreArchivoEventos = "eventos.dat";
	
	private ArrayList<Evento> eventos;  //se utiliza para almacenar todos los eventos del juego, se carga desde archivo.
	private ArrayList<EmpresaEnemiga> competencia;  //se utiliza para almacenar las empresas de la competencia, se carga desde archivo o se generan nuevas al empezar una nueva partida.
	private EmpresaUsuario empresa;  //se utiliza para almacenar los datos de la empresa para el guardado o la carga de la partida.
	private int dificultad;  //se almacena el nivel de dificultad elegido. 1=facil 2=medio 3=dificil
	private int mes;
	
	
	
	public Partida(ArrayList<EmpresaEnemiga> competencia, EmpresaUsuario empresa, int dificultad)  //constructor para partida cargada.
	{
		this.eventos = new ArrayList<Evento>();
		this.competencia = new ArrayList<EmpresaEnemiga>();
		cargarEventos();          //falta hacer la funcion de carga de los eventos desde el archivo. TEMPORAL
		this.competencia = competencia;
		this.empresa = empresa;
		setDificultad(dificultad);
		this.mes = 1;
	}
	
	public Partida(EmpresaUsuario empresa, ArrayList<EmpresaEnemiga> competencia, int dificultad) //constructor para nueva partida.
	{
		this.eventos = new ArrayList<Evento>();
		cargarEventos(); 
		this.competencia = new ArrayList<EmpresaEnemiga>();
		this.competencia = competencia;
		this.empresa = empresa;
		setDificultad(dificultad);
		this.mes = 1;
	}
	
	public void cargarEventos() // guarda el arreglo de eventos que vienen del archivo correspondiente
	{
		this.eventos = new Util().leerEventos();
	}
	
	public int getDificultad() { return this.dificultad; }
	
	public void setDificultad(int dificultad)
	{
		this.dificultad = dificultad;
	}

	public EmpresaUsuario getEmpresa(){ return this.empresa; }

	public int getMes(){ return this.mes; }
	
	
	public ArrayList<Evento> getArrayEventos()
	{
		return this.eventos;
	}

	public void actualizacionInicioMes(double ganancia) {
		this.empresa.setPatrimonio(this.empresa.getPatrimonio() + (ganancia) + (this.empresa.calcularGananciaClientes()));
		this.mes = this.mes + 1;
	}
	
	public void actualizacionFinDeMes() {
		this.empresa.aumentarClientes(dificultad);
		this.empresa.setHistorialPatrimonio(getMes(), this.empresa.getPatrimonio());
	}

	@Override
	public String toString() {
		return "Partida actual [Nombre de la empresa: "+empresa.getNombre()+" | CEO: "+empresa.getCEO()+" | Dificultad: "+getDificultad()+" ]";
	}
}


