package data;


import java.util.Random;

public class EmpresaEnemiga extends Empresa
{
	private static Random rand = new Random();

	
	public EmpresaEnemiga(int dificultad, String nombre, String ceo)
	{
		setNombre(nombre);
		setCEO(ceo);

		if(dificultad == 1)
		{
			setPatrimonio(10000 + rand.nextInt(50000));
		}
		else if (dificultad == 2)
		{
			setPatrimonio(50000 + rand.nextInt(50000));
		}
		else
		{
			setPatrimonio(100000 + rand.nextInt(50000));
		}
		
		
	}
	
	
	
	
}
