package data;

import java.util.ArrayList;

public class Partida implements IProbabilidad
{
	private ArrayList<Evento> eventos;
	private ArrayList<EmpresaEnemiga> competencia;
	private EmpresaUsuario empresa;
	
	
	
	
	public Partida(ArrayList<Evento> eventos, ArrayList<EmpresaEnemiga> competencia, EmpresaUsuario empresa)  //constructor para partida cargada.
	{
		this.eventos = new ArrayList<Evento>();
		this.competencia = new ArrayList<EmpresaEnemiga>();
		this.eventos = eventos;
		this.competencia = competencia;
		this.empresa = empresa;
	}
	
	public Partida() //constructor para nueva partida.
	{
		this.eventos = new ArrayList<Evento>();
		this.competencia = new ArrayList<EmpresaEnemiga>();
		this.empresa = new EmpresaUsuario();
	}
	
	
	
	
	
	
	
	
	
	
}


