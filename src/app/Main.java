package app;

import java.util.Scanner;
import data.*;

public class Main {
		
	static Scanner scan;
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		
		//Crear una nueva partida
		// Partida partida = new Partida(crearEmpresa(), competencia);
		
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
	
	public static int seleccionarDificultad() {
		int dif = 0;
		do {
			System.out.println(" > Seleccione donde se encontrara la empresa: ");
			System.out.println(" 	 ----- Facil -----");
			System.out.println(" 	1 -> Estados Unidos");
			System.out.println(" 	 ----- Medio -----");
			System.out.println(" 	2 -> España");
			System.out.println(" 	 ----- Dificil -----");
			System.out.println(" 	3 -> Argentina");
			
			System.out.print("Seleccion: ");
			dif = scan.nextInt();
			if(dif < 1 || dif > 7){
				System.out.println("Porfavor ingrese una opcion valida!");
			}
		}while(dif < 1 || dif > 7);
		
		return dif;
	}
	

}
