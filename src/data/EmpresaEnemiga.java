package data;

import java.util.ArrayList;
import java.util.Random;

public class EmpresaEnemiga extends Empresa implements IProbabilidad
{
	private static Random rand = new Random();
	
	@Override
	public int calcularProbabilidad() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public EmpresaEnemiga(int dificultad, String nombre, String ceo)
	{
		setNombre(nombre);
		setCEO(ceo);
		
		if(dificultad == 1)
		{
			setPatrimonio(10000 + rand.nextDouble());
		}
		else if (dificultad == 2)
		{
			setPatrimonio(50000 + rand.nextDouble());
		}
		else
		{
			setPatrimonio(100000 + rand.nextDouble());
		}
	}
	
	
	/*
	public ArrayList<EmpresaEnemiga> generarCompetencia(int dificultad)
	{
		ArrayList<EmpresaEnemiga> competencia = new ArrayList<EmpresaEnemiga>();
		int i = 0;
		int max = 10;
		competencia = leerEmpresaEnemigaDeArchivo();
		
		return competencia;
	}
	
	public EmpresaEnemiga leerEmpresasEnemigasDeArchivo()
	{
		
		
		
	}
	*/
	
}
