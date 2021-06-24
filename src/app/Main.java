package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import data.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
		
	static Scanner scan = new Scanner(System.in);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) 
	{
	
		int opc = 0;
		Partida partida = null;
		//------- variables -------
		
		do {
			opc = menuPrincipal();	//Muestra las opciones del menu principal y retorna la opcion elegida
			
			switch(opc) {
				case 1:	//Nueva partida
					partida = nuevaPartida();	//Se configura la empresa y la dificultad del juego. Retorna la partida actual
					new Util().guardarPartida(partida); //guarda la partida recién creada
					explicacionJuego(partida.getEmpresa().getCEO()); //muestra una explicacion básica del funcionamiento del juego
					break;
					
				case 2: //Cargar Partida
					partida = menuCargarPartida();
					break;
				case 3:
					if (menuBorrarPartida())
					{
						partida = nuevaPartida();	//Se configura la empresa y la dificultad del juego. Retorna la partida actual
						new Util().guardarPartida(partida); //guarda la partida recién creada
						explicacionJuego(partida.getEmpresa().getCEO()); //muestra una explicacion básica del funcionamiento del juego
					}
					else
					{
						partida = null;
					}
					break;
				case 4:
					verMejoresPartidas();	
					break;
				
				default:	//0 o default = salir
					System.out.println("Juego terminado. Nos vemos gil!");
					opc = 0;
					break;
			}
			
			if (partida != null && opc != 0)
			{
				loopJuego(partida);
			}
			
		}while(opc != 0);
	}
	
	public static int menuPrincipal() {
		System.out.println("------- CryptoCo. Tycoon -------\n");
		System.out.println("1-> Nueva partida");
		System.out.println("2-> Cargar partida");
		System.out.println("3-> Borrar partida");
		System.out.println("4-> Ver mejores partidas");
		System.out.println("0-> Salir");
		
		int opc = seleccionaOpcion();
		
		return opc;
	}
	
	public static void verMejoresPartidas()
	{
		ArrayList<Empresa> top10 = new Util().getTop10Empresas();
		int opc=0;
		
		System.out.println("<<< Mejores Partidas >>>");
		
		if (!top10.isEmpty())
		{
			for (int i=top10.size()-1; i > -1 ; i--)
			{
				System.out.println("-----------------------------------------");
				System.out.println("Empresa: " + top10.get(i).getNombre());
				System.out.println("CEO: " + top10.get(i).getCEO());
				System.out.println("Patrimonio: " + top10.get(i).getPatrimonio());
				System.out.println("-----------------------------------------");
			}
			
		}
		else
		{
			System.out.println("Aun no hay partidas cargadas en este apartado\n");
		}
		
		do 
		{
			System.out.println("Presione 1 para Salir");
			opc = seleccionaOpcion();
		}
		while (opc != 1);
	}
	
	public static int seleccionaOpcion(){
		scan = new Scanner(System.in);
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
	
	public static boolean menuBorrarPartida()
	{
		int opc = -1;
		int opc2 = 0;
		boolean crearNuevaPartida = false;
		ArrayList<String> arrayNomPartidas = new Util().partidasDisponibles();
		
		if (arrayNomPartidas.size() > 0)
		{
			while (opc < 0 || opc > arrayNomPartidas.size())
			{
				System.out.println("<<< Borrar Partida >>>");
				int i=0;
				
				for (String nombreE : arrayNomPartidas)
				{
					System.out.println(" - Ingrese (" + i + ") para Borrar: " + nombreE);
					i++;
				}
				System.out.println(" - Ingrese (" + arrayNomPartidas.size() + ") para cancelar");
				
				opc = seleccionaOpcion();
			}
			
			if (opc != arrayNomPartidas.size())
			{
				int opc3 = 0;
				
				while (opc3 != 1 && opc3 != 2)
				{
					System.out.println("Esta seguro que desea borrar la partida que contiene ["+ arrayNomPartidas.get(opc) +"]");
					System.out.println(" - Presione 1 para borra la partida");
					System.out.println(" - Presione 2 para cancelar");
					
					opc3 = seleccionaOpcion();
				}
				 
				
				if (opc3 == 1)
				{
					if (new Util().borrarPartida(arrayNomPartidas.get(opc)))
					{
						System.out.println("Se borro exitosamente la partida!");
					}
					else
					{
						System.out.println("El borrado de la partida fallo");
					}
				}
			}
		}
		else
		{	
			while(opc2 != 1 && opc2 != 2)
			{
				System.out.println("No hay partidas para borrar");
				System.out.println("Desea crear una partida nueva?");
				
				System.out.println(" - 1 para SI");
				System.out.println(" - 2 para NO");
				
				opc2 = seleccionaOpcion();
				
			}
			
			if (opc2 == 1)
			{
				crearNuevaPartida = true;
			}
		}
		
		return crearNuevaPartida;
	}
	
	public static void loopJuego(Partida partida) //loop general del juego
	{
		int opc = 3;
		do
		{
			/*if(opc == 3){
				partida = novedadesEventos(partida);
			}*/
			opc = menuJuego();
			switch(opc)
			{
				case 0:
					break;
				
				case 1:	//
					menuFinanzas(partida.getEmpresa());
					break;
				
				case 2:
					verEventos(partida);
					break;
					
				case 3:
					partida = novedadesEventos(partida);
					partida.actualizacionFinDeMes(); //Actualiza el historial de patrimonios y aumenta la cantidad de clientes
					if (new Util().guardarPartida(partida))
					{
						new Util().setTop10Empresas(partida.getEmpresa());
					}
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
			new Util().setTop10Empresas(partida.getEmpresa());
			System.out.println("Se guardo correctamente la partida!");
		}
	}
	
	public static int menuJuego(){
		System.out.println("\nMenu:");
		System.out.println("1-> Finanzas."); 
		System.out.println("2-> Ver eventos activos.");
		System.out.println("3-> Avanzar mes.");
		System.out.println("0-> Guardar y salir.");
		int opc = seleccionaOpcion();
		
		return opc;
	}
	
	public static Partida novedadesEventos(Partida partida)
	{
		double ganancia = 0;
		int opc = 0;
		
		for (int i=1; i < 5; i++)
		{
			Random rand = new Random();//Selecciono un numero randon entre 0 y la cantidad de eventos
			int aux = rand.nextInt(partida.getArrayEventos().size());
			Evento evento = partida.getArrayEventos().get(aux);
			
			ArrayList<Evento> activos = partida.getEmpresa().getArrayEventosActivos();
			
			while(activos.contains(evento))
			{
				aux = rand.nextInt(1+partida.getArrayEventos().size())-1;
				evento = partida.getArrayEventos().get(aux);
			}
			
			if(evento.getActivo())
			{
				partida.getEmpresa().agregarEventoActivo(evento);
			}

			opc = printEvento(evento);
			
			if (evento instanceof EventoConOpciones)
			{
				EventoConOpciones eventoConOpc = (EventoConOpciones) evento;
				
				do
				{
					if (opc > 0 && opc <= eventoConOpc.getArrayOpciones().size())
					{
						ganancia = ganancia + eventoConOpc.getArrayDatos().get(opc);
					}
					else
					{
						System.out.println("Ingrese una opcion valida!");
						opc = seleccionaOpcion();
					}
				}
				while (opc < 0 || opc > eventoConOpc.getArrayOpciones().size());
			}
			else
			{
				ganancia = ganancia + evento.getValor();
			}
	
		}
		
		double totalEventosActivos = resultadoEventosActivos(partida.getEmpresa());
		
		System.out.println("===============================");
		System.out.println("Total eventos: $"+ganancia);
		System.out.println("Total clientes: $"+partida.getEmpresa().calcularGananciaClientes());
		System.out.println("Total eventos activos: $"+totalEventosActivos);
		System.out.println("Clientes nuevos: "+partida.getEmpresa().calcularNuevosClientes(partida.getDificultad()));
		double totalFinal = (totalEventosActivos) + (ganancia) + (partida.getEmpresa().calcularGananciaClientes());
		System.out.println("===============================");
		System.out.println("Clientes totales: "+partida.getEmpresa().getNClientes());
		System.out.println("Resultado final: $" + totalFinal);
		System.out.println("===============================");
		
		partida.actualizacionInicioMes(ganancia + (totalEventosActivos)); //Actualiza el patrimonio y aumenta en 1 el mes
		
		return partida;
	}
	
	public static double resultadoEventosActivos(EmpresaUsuario empresa)
	{
		int i = 0;
		double result = 0;
		
		for(i=0; i<empresa.getArrayEventosActivos().size();i++)
		{
			result += empresa.getArrayEventosActivos().get(i).getValor();
		}
		
		
		
		return result;
	}
	
	public static int printEvento(Evento eventoAMostrar) //se utiliza para mostrar los eventos 
	{
		int opc = 0;
		if (eventoAMostrar instanceof EventoConOpciones)
		{
			EventoConOpciones evento = (EventoConOpciones) eventoAMostrar;
			
			System.out.println("<<<Evento>>>\n");
			System.out.println(evento.getNombre() + "\n");
			System.out.println(evento.getDescripcion() + "\n");
			System.out.println("Valor: $"+evento.getValor() + "\n");
			System.out.println("Opciones: \n");
			
			ArrayList<String> arrayOpciones = evento.getArrayOpciones();
			for (int i=0; i < arrayOpciones.size(); i++)
			{
				System.out.println(i + " -> " + arrayOpciones.get(i) + "\n");
			}
			
			System.out.println("Elija una opcion \n");
			opc = seleccionaOpcion();
		}
		else
		{
			System.out.println("\n<<<Evento>>>\n");
			System.out.println(eventoAMostrar.getNombre() + "\n");
			System.out.println(eventoAMostrar.getDescripcion() + "\n");
			System.out.println("Valor: $"+eventoAMostrar.getValor() + "\n");
		}
		return opc;
	}
	
	public static void verEventos(Partida partida) {
		System.out.println("\n! ! !  Eventos Activos ! ! ! \n");
		for(int i = 0; i < partida.getEmpresa().contarEventosActivos(); i++) 
		{
			Evento evento = partida.getEmpresa().getEventoActivo(i); 
			printEvento(evento);
		}	
	}
	
	public static void menuFinanzas(EmpresaUsuario empresa){
		int opc = 0;
		do{
			System.out.println("===============================");
			System.out.println(" Menu Finanzas:");
			System.out.println(" <1> Ver patrimonio");
			System.out.println(" <2> Historial completo");
			System.out.println(" <0> Atras");
			System.out.println("===============================");
			opc = seleccionaOpcion();
			if(opc < 0 || opc > 2){
				System.out.println("Ingrese una opcion valida!");
			}
			
			if(opc > 0 && opc < 3){
				int n = empresa.getHistorialPatrimonio().size();
				
				System.out.println("\n*******************************");
				switch(opc){
					case 1:
						System.out.println(" > Finanzas mes " + n +"\n");
						System.out.println(" > Patrimonio: $" + empresa.getPatrimonio());
						break;
						
					case 2:	//Muestra el historial de patrimonios desde que se creo la empresa hasta la fecha actual
						if(n > 0) {	//Verifica que haya algo que mostrar
							for(int i = 1; i < empresa.getHistorialPatrimonio().size()+1 ; i++){ 
								System.out.println("   Mes <" + i + ">: $" + empresa.mesEspecificoPatrimonio(i+1));
							}
						}else {
							System.out.println("Historial vacio, a partir del segundo mes se habilitara!");
						}
						break;
				}
				System.out.println("*******************************");
				System.out.println("\n");
				pausarEjecucion();
			}
			
		}while(opc != 0);
	}
	
	public static int seleccionarDificultad() { //seleccion de dificultad del juego
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
	
	public static Partida nuevaPartida() //creación de una partida desde cero
	{
		
		System.out.println("¡Bienvenido al menú de nueva partida!");
		
		int i = 0;
		ArrayList<String> pDisponibles = new ArrayList<String>();
		pDisponibles = new Util().partidasDisponibles();
		boolean flag = false;
		boolean existe = false;
		String nombre = new String();
		String aux = new String();
		
		while(!flag)
		{
			System.out.println("Nombre de la empresa: ");
			scan.nextLine();
			nombre = scan.nextLine();
			aux = nombre.replaceAll("\\s", "");
			
			while(i<pDisponibles.size() && !existe)
			{
				if(pDisponibles.get(i).equals(aux))
				{
					
					existe = true;
				}
				if(!existe)
				{
					i++;
				}
				
			}
			
			if(existe)
			{
				System.out.println("\nYa existe una partida guardada con ese nombre de empresa.\n");
				System.out.println("Si desea sobreescribir esa partida ingrese 1.\n");
				System.out.println("En caso contrario presione 0 y modifique el nombre de la empresa.\n\n");
				int opc = seleccionaOpcion();
				switch(opc)
				{
				case 1:
					flag = true;
					break;
				case 0:
					flag = false;
					existe = false;
				}
			}
			else
			{
				flag = true;
			}
		}
		
		scan = new Scanner(System.in);
		System.out.println("Nombre del CEO (usuario): ");
		String ceo = scan.nextLine();
		
		System.out.println("Seleccione una dificultad: ");
		int dificultad = seleccionarDificultad();
		
		
		ArrayList<EmpresaEnemiga> competencia = (ArrayList<EmpresaEnemiga>)Util.cargarYOrdenarEmpresasEnemigas(dificultad);

		EmpresaUsuario empresa = new EmpresaUsuario(dificultad, nombre, ceo);
		Partida partida = new Partida(empresa, competencia, dificultad);
		
		return partida;
	}
	
	public static void pausarEjecucion() {
		System.out.println("Presione ENTER para continuar.");

		try 
		{
			br.readLine();
		}catch(IOException e)
		{
			
		}
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
		
		pausarEjecucion();
	}

}
