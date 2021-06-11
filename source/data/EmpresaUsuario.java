package data;

import java.util.ArrayList;
import java.util.HashMap;

public class EmpresaUsuario extends Empresa
{
	static final int patInicDif = 50000;   //--
	static final int patInicMed = 100000;  //conjunto de constantes que se utilizan para, seg�n la dificultad, establecer un patrimonio inicial de la empresa.
	static final int patInicFac = 200000;  //--
	static final int clientesInicDif = 100;  //--
	static final int clientesInicMed = 200;  //conjunto de constantes que se utilizan para, segun la dificultad, establecer la cantidad de clientes con la que comienza la empresa.
	static final int clientesInicFac = 500;  //--
	
	private ArrayList<Evento> histEventos; //se utiliza para almacenar un historial de los eventos por los que pas� la empresa a lo largo del juego.
	private ArrayList<Modificador> modificadores; //Se utiliza para almacenar los modificadores y los eventos activos que posee la empresa.
	private int nClientes; //Variable donde se almacena el numero de clientes que posee la empresa.
	private double comision; //Comision que cobra la empresa a cada cliente. Fuente principal de ingresos.
	HashMap<Integer, Double> historialPatrimonio; //Key = mes ; Value= patrimonio en dicho mes. Se utiliza para almacenar el patrimonio que tuvo la empresa en cada mes. Se muestra al final del juego.
}
