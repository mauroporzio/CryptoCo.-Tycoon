package app;

import java.util.ArrayList;
import java.util.Scanner;
import data.*;

public class Main {
		
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int opc = 0;
		Partida partida = null;
		//------- variables -------
		
		do {
			opc = menuPrincipal();	//Muestra las opciones del menu principal y retorna la opcion elegida
			
			switch(opc) {
				case 1:	//Nueva partida
					partida = nuevaPartida();	//Se configura la empresa y la dificultad del juego. Retorna la partida actual
					break;
					
				case 2:
					//Partida partida = cargarPartida();
					break;
				
				default:	//0 o default = salir
					opc = 0;
					break;
			}
			
			//loopJuego(partida);
			
		}while(opc != 0);
	}
	
	public static int menuPrincipal() {
		System.out.println("------- CryptoCo. Tycoon -------\n");
		System.out.println("1-> Nueva partida");
		System.out.println("2-> Cargar partida");
		System.out.println("3-> Ver mejores partidas");
		System.out.println("0-> Salir");
		
		int opc = seleccionaOpcion();
		
		return opc;
	}
	
	public static int seleccionaOpcion(){
		System.out.println("Por favor, ingrese un número dentro de las opciones y presione ENTER.");
		int opc = scan.nextInt();
		return opc;
	}
	
	public static int seleccionarDificultad() {
		int dif = 0;
		System.out.println(" > Seleccione donde se encontrara la empresa: ");
		System.out.println(" 	 ----- Facil -----");
		System.out.println(" 	1 -> Estados Unidos");
		System.out.println(" 	 ----- Medio -----");
		System.out.println(" 	2 -> España");
		System.out.println(" 	 ----- Dificil -----");
		System.out.println(" 	3 -> Argentina");

		do{
			dif = seleccionaOpcion();

			if(dif < 1 || dif > 7){
				System.out.println("Porfavor ingrese una opcion valida!");
			}
		}while(dif < 1 || dif > 7);

		return dif;
	}
	
	public static Partida nuevaPartida()
	{
		
		System.out.println("¡Bienvenido al menú de nueva partida!");
		System.out.println("Nombre del CEO (usuario): ");
		String ceo = scan.next();
		System.out.println("Nombre de la empresa: ");
		String nombre = scan.next();
		System.out.println("Seleccione una dificultad: ");
		int dificultad = seleccionarDificultad();
		
		ArrayList<EmpresaEnemiga> competencia = new ArrayList<EmpresaEnemiga>();
		//competencia.generarCompetencia();
		
		EmpresaUsuario empresa = new EmpresaUsuario(dificultad, nombre, ceo);
		Partida partida = new Partida(empresa,competencia, dificultad);
		
		return partida;
	}
	
}
