package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Partida implements IProbabilidad
{
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
		this.eventos = generarEventos();           //falta hacer la funcion de carga de los eventos desde el archivo. TEMPORAL
		this.competencia = competencia;
		this.empresa = empresa;
		setDificultad(dificultad);
		this.mes = 1;
	}
	
	public Partida(EmpresaUsuario empresa, ArrayList<EmpresaEnemiga> competencia, int dificultad) //constructor para nueva partida.
	{
		this.eventos = new ArrayList<Evento>();
		this.eventos = generarEventos();              //falta hacer la funcion de carga de los eventos desde el archivo. TEMPORAL
		this.competencia = new ArrayList<EmpresaEnemiga>();
		this.competencia = competencia;
		this.empresa = empresa;
		setDificultad(dificultad);
		this.mes = 1;
	}
	
	public void cargarEventos() // guarda el arreglo de eventos que vienen del archivo correspondiente
	{
		this.eventos = new Util().leerEventos(nombreArchivoEventos);
	}
	
	public int getDificultad() { return this.dificultad; }
	
	public void setDificultad(int dificultad)
	{
		this.dificultad = dificultad;
	}

	public EmpresaUsuario getEmpresa(){ return this.empresa; }

	public int getMes(){ return this.mes; }
	
	public static ArrayList<Evento> generarEventos() //TEMPORAL
	{
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		int i = 0;
		int max = 10;
		
		for(i=0;i<max;i++)
		{
			String aux = new String();
			aux = "evento n°"+i;
			
			Evento evento1 = new Evento(aux,aux,false);
			
			eventos.add(evento1);
		}
		
		return eventos;
	}
	
	public ArrayList<Evento> getArrayEventos()
	{
		return this.eventos;
	}

	public void actualizarHistorialPatrimonio(Double valor){
		this.empresa.getHistorialPatrimonio().put(getMes(), valor);
	}

	@Override
	public int calcularProbabilidad() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Partida actual [Nombre de la empresa: "+empresa.getNombre()+" | CEO: "+empresa.getCEO()+" | Dificultad: "+getDificultad()+" ]";
	}
}


