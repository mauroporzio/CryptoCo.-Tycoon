package app;

import java.util.ArrayList;
import java.util.Scanner;
import data.*;

public class Main {
		
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//Partida partida = nuevaPartida();
		
	}
	
	public static EmpresaUsuario crearEmpresa() {
		System.out.println("<<<Menu creacion de empresa>>>");
		EmpresaUsuario empresa = new EmpresaUsuario(seleccionarDificultad(), seleccionarNombre("Nombre de la empresa"), seleccionarNombre("CEO"));
		return empresa;
	}
	
	public static String seleccionarNombre(String tipo) { //Verifica que sea un nombre que contenga vocales y lo devuelve 
		//Ingresar por parametros que es lo que pide
		String nombre = "";
		do {
			System.out.print(" > "+tipo+": ");
			nombre = scan.nextLine();
			
			if(!(nombre.contains("a") || nombre.contains("e") || nombre.contains("i") || nombre.contains("o") || nombre.contains("u"))) {
				System.out.println("Porfavor ingrese un nombre con alguna vocal!");
			}
		}while(!(nombre.contains("a") || nombre.contains("e") || nombre.contains("i") || nombre.contains("o") || nombre.contains("u")));
		
		return nombre;
	}
	
	public static int seleccionaOpcion(){
		int opc = 0;
		System.out.println("Por favor, ingrese un número dentro de las opciones y presione ENTER.");
		opc = scan.nextInt();
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
	
	/*
	public static Partida nuevaPartida()
	{
		
		System.out.println("¡Bienvenido al menú de nueva partida!");
		System.out.println("Nombre del CEO (usuario): ");
		String ceo = scan.next();
		System.out.println("Nombre de la empresa: ");
		String nombre = scan.next();
		System.out.println("Seleccione una dificultad: ");
		int dificultad = seleccionarDificultad();
		
		ArrayList<EmpresaEnemiga> competencia = competencia.generarCompetencia();
		
		
		EmpresaUsuario empresa = new EmpresaUsuario(dificultad, nombre, ceo);
		Partida partida = new Partida(empresa,competencia, dificultad);
		
		
		
		return partida;
	}
	*/
}
