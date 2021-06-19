package data;

import java.util.ArrayList;

public class Partida implements IProbabilidad
{
	private ArrayList<Evento> eventos;  //se utiliza para almacenar todos los eventos del juego, se carga desde archivo.
	private ArrayList<EmpresaEnemiga> competencia;  //se utiliza para almacenar las empresas de la competencia, se carga desde archivo o se generan nuevas al empezar una nueva partida.
	private EmpresaUsuario empresa;  //se utiliza para almacenar los datos de la empresa para el guardado o la carga de la partida.
	private int dificultad;  //se almacena el nivel de dificultad elegido. 1=facil 2=medio 3=dificil
	
	
	
	
	public Partida(ArrayList<EmpresaEnemiga> competencia, EmpresaUsuario empresa, int dificultad)  //constructor para partida cargada.
	{
		this.eventos = new ArrayList<Evento>();
		this.competencia = new ArrayList<EmpresaEnemiga>();
		cargarEventos();           //falta hacer la funcion de carga de los eventos desde el archivo.
		this.competencia = competencia;
		this.empresa = empresa;
		setDificultad(dificultad);
	}
	
	public Partida(EmpresaUsuario empresa, ArrayList<EmpresaEnemiga> competencia, int dificultad) //constructor para nueva partida.
	{
		this.eventos = new ArrayList<Evento>();
		cargarEventos();             //falta hacer la funcion de carga de los eventos desde el archivo.
		this.competencia = new ArrayList<EmpresaEnemiga>();
		this.competencia = competencia;
		this.empresa = empresa;
		setDificultad(dificultad);
	}
	
	public void cargarEventos()
	{
		
	}
	
	public int getDificultad() { return this.dificultad; }
	
	public void setDificultad(int dificultad)
	{
		this.dificultad = dificultad;
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

