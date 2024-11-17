package ar.edu.unju.escmi.tpfinal.main;

import java.util.Scanner;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ClienteDaoImp;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static IClienteDao clienteDao = new ClienteDaoImp();

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			menu();
			opcion = ingresarNumeroEntero("Ingresar opcion: ");
			switch (opcion) {
			case 1:
				registrarCliente();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				System.out.println("*** CERRANDO PROGRAMA ***");
				scanner.close();
				break;
			default:
				System.out.println("Esta no es una opcion\n");
				break;
			}
		} while (opcion != 10);
		
	}
	
	static public void menu() {
		System.out.println("\n----\tMENU PRINCIPAL\t----"
				+ "\n1 – Alta de cliente.\n"
				+ "2 – Consultar Clientes.\n"
				+ "3 - Modificar Cliente.\n"
				+ "4 – Realizar Pago.\n"
				+ "5 – Realizar Reserva.\n"
				+ "6 - Consultar todas las Reservas.\n"
				+ "7 - Consultar una Reserva(se ingresa id).\n"
				+ "8 - Consultar Salones.\n"
				+ "9 - Consultar Servicios Adicionales.\n"
				+ "10 - CERRAR PROGRAMA\n");
	}
	
	public static String ingresarString(String texto) {
		boolean opcionValida=false;
		String string = null;
		while(!opcionValida) {
			try {
				System.out.print(texto);
		        string = scanner.next();
				opcionValida=true;
			} catch (Exception e) {
				System.out.println("Fallo durante el ingreso de datos");
			}
		}
		return string;
	}
	
	public static String ingresarStringConEspacios(String texto) {
		boolean opcionValida=false;
		String string = null;
		scanner.nextLine();
		while(!opcionValida) {
			try {
				System.out.print(texto);
		        string = scanner.nextLine();
				opcionValida=true;
			} catch (Exception e) {
				System.out.println("Fallo durante el ingreso de datos");
				scanner.nextLine();
			}
		}
		return string;
	}
	
	public static int ingresarNumeroEntero(String texto) {
		boolean opcionValida=false;
		int numeroInt = 0;
		while(!opcionValida) {
			try {
				System.out.print(texto);
		        numeroInt = scanner.nextInt();
				opcionValida=true;
			} catch (Exception e) {
				System.out.println("Fallo durante el ingreso de datos");
				scanner.nextLine();
			}
		}
		return numeroInt;
	}
	
	public static Long ingresarNumeroEnteroLargo(String texto) {
		boolean opcionValida=false;
		Long numeroLong = (long) 0;
		while(!opcionValida) {
			try {
				System.out.print(texto);
		        numeroLong = scanner.nextLong();
				opcionValida=true;
			} catch (Exception e) {
				System.out.println("Fallo durante el ingreso de datos");
				scanner.nextLine();
			}
		}
		return numeroLong;
	}
	
	public static double ingresarNumeroDouble(String texto) {

		boolean opcionValida=false;
		double numero = 0;
		while(!opcionValida) {
			try {
				System.out.print(texto);
				numero = scanner.nextDouble();
				opcionValida=true;
			
			} catch (Exception e) {
				System.out.println("Fallo durante el ingreso de datos");
				scanner.nextLine();
			}
		}

		return numero;

	}
	
	public static void registrarCliente() {
		
		String nombre = ingresarString( "Registrar el nombre del Cliente: ");
		String apellido = ingresarString( "Registrar el apellido del Cliente: ");
		String domicilio = ingresarStringConEspacios( "Registrar el domicilio del Cliente: ");
		int dni = ingresarNumeroEntero( "Registrar el dni del Cliente: " );
		
		Cliente cliente = new Cliente(nombre, apellido, domicilio, dni, true);
		
		clienteDao.guardarCliente(cliente);
		
	}
}
