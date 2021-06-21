package app;

import java.util.ArrayList;
import java.util.Scanner;
import data.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
		
	static Scanner scan = new Scanner(System.in);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) {
		
		int opc = 0;
		Partida partida = null;
		//------- variables -------
		
		do {
			opc = menuPrincipal();	//Muestra las opciones del menu principal y retorna la opcion elegida
			
			switch(opc) {
				case 1:	//Nueva partida
					partida = nuevaPartida();	//Se configura la empresa y la dificultad del juego. Retorna la partida actual
					explicacionJuego(partida.getEmpresa().getCEO());
					break;
					
				case 2: //Cargar Partida
					partida = menuCargarPartida();
					break;
				case 3:
					//verMejoresPartidas();	
					break;
				
				default:	//0 o default = salir
					opc = 0;
					break;
			}
			
			if (partida != null)
			{
				loopJuego(partida);
			}
			
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
	
	public static Partida menuCargarPartida() // muestra todas las partidas disponibles, pide cual se quiere cargar y se devuelve esa partida.
	{
		int opc = -1;
		int opc2 = 0;
		ArrayList<String> arrayNomPartidas = new Util().partidasDisponibles();
		Partida partida = null;
		
		if (arrayNomPartidas.size() > 0)
		{
			while (opc < 0 || opc > arrayNomPartidas.size())
			{
				System.out.println("<<< Cargar Partida >>>");
				int i=0;
				
				for (String nombreE : arrayNomPartidas)
				{
					System.out.println(" - Ingrese (" + i + ") para cargar: " + nombreE);
					i++;
				}
				System.out.println(" - Ingrese (" + arrayNomPartidas.size() + ") para cancelar");
				
				opc = seleccionaOpcion();
			}
			
			if (opc != arrayNomPartidas.size())
			{
				partida = new Util().cargarPartida(arrayNomPartidas.get(opc));
			}
		}
		else
		{	
			while(opc2 != 1 && opc2 != 2)
			{
				System.out.println("No hay partidas para cargar");
				System.out.println("Desea crear una partida nueva?");
				
				System.out.println(" - 1 para SI");
				System.out.println(" - 2 para NO");
				
				opc2 = seleccionaOpcion();
				
			}
			
			if (opc2 == 1)
			{
				partida = nuevaPartida();
			}
		}
		
		return partida;
	}
	
	public static void loopJuego(Partida partida)
	{
		int opc = 3;
		int ganancia = 0;
		do
		{
			if(opc == 3) {
				ganancia =  ganancia + novedadesEventos(partida);
			}
			
			opc = menuJuego();
			switch(opc)
			{
				case 0:
					break;
				
				case 1:	//
					mostrarFinanzas(partida.getEmpresa());
					break;
				
				case 2:
					//verEventos();
					break;
					
				case 3:
					//partida.avanzarMes();
					break;
				
				default:
					opc = -1;
					System.out.println("Ingrese una opcion valida!");
					break;
			}		
			
			if(opc != 0 && opc != -1) {
				pausarEjecucion();
			}
			
		}while(opc != 0);
		
		if (new Util().guardarPartida(partida))
		{
			System.out.println("Se guardo correctamente la partida!");
		}
	}
	
	public static int menuJuego(){
		System.out.println("\nMenu:");
		System.out.println("1-> Finanzas."); 
		System.out.println("2-> Ver eventos activos y sus modificadores.");
		System.out.println("3-> Avanzar mes.");
		System.out.println("0-> Salir y Guardar.");
		
		int opc = seleccionaOpcion();
		
		return opc;
	}
	
	public static int novedadesEventos(Partida partida)
	{
		int ganancia = 0;
		
		for (int i=0; i < 5; i++)
		{
			Evento evento = partida.getArrayEventos().get(i);
			printEvento(evento);
			
			if (evento instanceof EventoConOpciones)
			{
				EventoConOpciones eventoConOpc = (EventoConOpciones) evento;
				
				int opc = -1;
				
				do
				{
					if (opc < 0 || opc > eventoConOpc.getArrayOpciones().size())
					{
						ganancia = ganancia + eventoConOpc.getArrayDatos().get(opc);
					}
					else
					{
						System.out.println("Ingrese una opcion valida!");
					}
				}
				while (opc < 0 || opc > eventoConOpc.getArrayOpciones().size());
			}
		}
		return ganancia;
	}
	
	public static void printEvento(Evento eventoAMostrar)
	{
		if (eventoAMostrar instanceof EventoConOpciones)
		{
			EventoConOpciones evento = (EventoConOpciones) eventoAMostrar;
			
			System.out.println("<<<Evento>>>\n");
			System.out.println(evento.getNombre() + "\n");
			System.out.println(evento.getDescripcion() + "\n");
			
			System.out.println("Opciones: \n");
			
			ArrayList<String> arrayOpciones = evento.getArrayOpciones();
			for (int i=0; i < arrayOpciones.size(); i++)
			{
				System.out.println(i + " -> " + arrayOpciones.get(i) + "\n");
			}
			
			System.out.println("Elija una opcion \n");
		}
		else
		{
			System.out.println("\n<<<Evento>>>\n");
			System.out.println(eventoAMostrar.getNombre() + "\n");
			System.out.println(eventoAMostrar.getDescripcion() + "\n");
		}
	}
	
	public static void mostrarFinanzas(EmpresaUsuario empresa){
		int n = empresa.getHistorialPatrimonio().size() ;
		System.out.println("===============================");
		System.out.println(" Finanzas mes" + n + " -> " + empresa.getNombre() + " <- \n");
		System.out.println(" Patrimonio: " + empresa.getPatrimonio());
	
		System.out.println(" Patrimonio ultimos tres meses:");
		//Si los meses cargados en el historial son inferiores a 3, se muestran los cargados hasta la fecha (2,1 o 0)
		for(int i = n-3; i < n; i++) {
			if(i > 0) {
				System.out.println("  > Mes " + i + ": $" + empresa.getHistorialPatrimonio().get(i));
			}
		}
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
		String ceo = scan.nextLine();
		scan.nextLine(); // clean buffer.
		System.out.println("Nombre de la empresa: ");
		String nombre = scan.nextLine();
		System.out.println("Seleccione una dificultad: ");
		int dificultad = seleccionarDificultad();
		
		ArrayList<EmpresaEnemiga> competencia = generarCompetencia(dificultad);

		EmpresaUsuario empresa = new EmpresaUsuario(dificultad, nombre, ceo);
		Partida partida = new Partida(empresa, competencia, dificultad);
		
		return partida;
	}
	
	public static ArrayList<EmpresaEnemiga> generarCompetencia(int dificultad) //TEMPORAL
	{
		ArrayList<EmpresaEnemiga> competencia = new ArrayList<EmpresaEnemiga>();
		int i = 0;
		int max = 10;
		
		for(i=0;i<max;i++)
		{
			String aux = new String();
			aux = "empresa n°" +i;
			EmpresaEnemiga empresa = new EmpresaEnemiga(i,aux,aux);
			
			competencia.add(empresa);
		}
		
		return competencia;
	}
	
	public static void pausarEjecucion() {
		System.out.println("Presione cualquier tecla para continuar");
		scan.nextLine();
	}
	
	public static void explicacionJuego(String nombre)
	{
		System.out.println("\n\n");
		System.out.println("Hola "+ nombre + " bienvenido a CryptoCo. Tycoon!\n");
		System.out.println("A continuación te explico rápidamente el juego: \n");
		System.out.println("\n  Una vez oprima continuar, se mostrarán 4 eventos correspondientes al primer mes\n");
		System.out.println("del curso de la empresa, esos 4 eventos pueden ser tanto positivos como negativos, y\n");
		System.out.println("pueden o no tener opciones sobre las que tendrás que tomar una decisión. En caso de\n");
		System.out.println("que salga uno de esos, tendrás que ingresar una opción antes de continuar.\n");
		System.out.println("  Luego de ver los eventos y tomar tus decisiones, llega la parte donde revisas tus\n");
		System.out.println("finanzas y los eventos y modificadores activos o también puedes saltar al siguiente mes.\n");
		System.out.println("  Estos eventos activos de los que te comenté, se tratan de los eventos que pueden, una\n");
		System.out.println("vez aparecen, mantenerse a lo largo de algunos meses y actuar sobre tus ganancias finales del mes.\n");
		System.out.println("  El objetivo del juego es muy simple, aumenta tus ganancias, sobrevive a los eventos, y por supuesto...");
		System.out.println("                                ¡APLASTA A TU COMPETENCIA!");
		System.out.print("\nAhora sí, comencemos. Presiona la tecla Enter para continuar!-\n\n");
		String sTexto = new String();
		try
	    {
			sTexto = br.readLine();
	    }
	    catch(Exception e)
	    {}
	}

}
