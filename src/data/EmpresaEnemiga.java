package data;

public class EmpresaEnemiga extends Empresa implements IProbabilidad
{
	private int dificultad; //valor que se utiliza para conocer la dificultad de la empresa enemiga. 1 f�cil, 2 medio, 3 dif�cil. Afecta el patrimonio.

	@Override
	public int calcularProbabilidad() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
