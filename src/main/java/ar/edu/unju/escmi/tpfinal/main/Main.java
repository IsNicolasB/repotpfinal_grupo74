package ar.edu.unju.escmi.tpfinal.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;
import ar.edu.unju.escmi.tpfinal.entities.Salon;
import ar.edu.unju.escmi.tpfinal.utils.FechaUtil;
import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.dao.IReservaDao;
import ar.edu.unju.escmi.tpfinal.dao.ISalonDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.SalonDaoImp;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static IClienteDao clienteDao = new ClienteDaoImp();
	public static ISalonDao salonDao = new SalonDaoImp();
	public static IReservaDao reservaDao = new ReservaDaoImp();
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
				mostrarClientes();
				break;
			case 3:
				modificarCliente();
				break;
			case 4:
				realizarPagoDeReserva();
				break;
			case 5:
				realizarReserva();
				break;
			case 6:
				consultarReservas();
				break;
			case 7:
				consultarReserva();
				break;
			case 8:
				consultarSalones();
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
		String telefono = ingresarStringConEspacios( "Registrar el telefono del cliente");
		Long dni = ingresarNumeroEnteroLargo( "Registrar el dni del Cliente: " );
		
		Cliente cliente = new Cliente(dni, nombre, apellido, domicilio, telefono, true);
		
		clienteDao.guardarCliente(cliente);
		
	}
	
	public static void mostrarClientes() {
		List<Cliente> clientes = clienteDao.obtenerClientes();
		clientes.forEach(System.out::println);
	}
	
	public static void modificarCliente() {
		
		if(clienteDao.obtenerClientes().stream().filter(c -> c.isEstado() == true).count() == 0) {
			System.out.println("No hay clientes disponibles");
			return;
		}
		
		Cliente cliente = seleccionarCliente("Ingresar el id del cliente que desea modificar: ");
		
		System.out.println("El cliente encontrado es: \n" + cliente.toString());
		
		int opcion = 0;
		do {
			System.out.println("\n1 - Modificar nombre.");
			System.out.println("2 - Modificar apellido.");
			System.out.println("3 - Modificar domicilio.");
			System.out.println("4 - Modificar telefono.");
			System.out.println("5 - Salir");
			opcion = ingresarNumeroEntero( "Ingresar opcion: ");
			switch (opcion) {
			case 1 :
				String nombre = ingresarString("Registrar el nuevo nombre del Cliente: ");
				cliente.setNombre(nombre);
				break;
			case 2 :
				String apellido = ingresarString("Registrar el nuevo apellido del Cliente: ");
				cliente.setApellido(apellido);
				break;
			case 3 : 
				String domiclio = ingresarStringConEspacios("Registrar el nuevo domiclio del Cliente: ");
				cliente.setDomicilio(domiclio);
				break;
			case 4:
				String telefono = ingresarStringConEspacios("Registrar el nuevo telefono del Cliente: ");
				cliente.setTelefono(telefono);
			case 5 :
				System.out.println("Se finalizó la modicación");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		}while(opcion != 4);
		
		clienteDao.modificarCliente(cliente);
	}
	
	public static Cliente seleccionarCliente(String texto) {
		Cliente cliente = null;
		boolean existe = false;
		long id = 0 ;
		clienteDao.obtenerClientes().forEach(cli -> {
			if(cli.isEstado() == true) System.out.println(cli.getId()+" - "+cli.getNombre()+ " "
														+ cli.getApellido() + " "+ cli.getDni());
		});
		
		while(!existe) {
			
	        try {
	        	id = ingresarNumeroEntero(texto);
				cliente = clienteDao.obtenerCliente(id);
				existe=true;
	        } catch (NoResultException e) {
	            System.out.println("Cliente con ID " + id + " no encontrado.");
	            throw e;
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Ocurrió un error al buscar el cliente.");
	        }
		}
		
		return cliente;
	}
	
	public static void realizarReserva() {
		if(clienteDao.obtenerClientes().stream().filter(c -> c.isEstado() == true).count() == 0) {
			System.out.println("No hay clientes disponibles para realizar una reserva");
			return;
		}
		
		Cliente cliente = seleccionarCliente("Ingrese el id cliente que realizará la reserva");
		
		LocalDate fechaReserva = null;
	    boolean esValido = false;
		while (!esValido) {
	    	try {
	    		String aux = ingresarString("Ingrese la fecha del prestamo en formato dd/MM/yyyy: ");
	    		fechaReserva = FechaUtil.convertirStringLocalDate(aux);
	    		esValido = true;
	    	} catch (IllegalArgumentException e) {
	    		System.out.println(e.getMessage());
	    	}
	    }
		
		
		
	}
	
	public static void realizarPagoDeReserva() {
		if(reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true && r.isCancelado() == false).count() > 0) {
			
			reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true && r.isCancelado())
			.forEach(r -> System.out.println("\nId: " + r.getId() + "\nFecha: " + r.getFecha() + "\nMonto Pagado: " + r.getMontoPagado()));
			
			boolean existe = false;
			Reserva reserva = null;
			long id = 0;
			while(!existe) {
				try {
		        	id = ingresarNumeroEntero("Ingresar el id de la reserva que desea pagar: ");
					reserva = reservaDao.obtenerReserva(id);
					existe=true;
		        } catch (NoResultException e) {
		            System.out.println("Reserva con ID " + id + " no encontrado.");
		            throw e;
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Ocurrió un error al buscar la Reserva.");
		        }
			}
			
			reservaDao.modificarReserva(reserva);
		
		}
		else {
			System.out.println("No hay reservas pendientes a pagar");
		}
	}
	
	
	
	public static void consultarReservas() {
		if(reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true).count() > 0) {
			reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true).forEach(System.out::println);
		}
		else System.out.println("No hay reservas disponibles");
	}
	
	public static void consultarReserva() {
		if(reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true).count() > 0) {
			
			reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true && r.isCancelado())
			.forEach(r -> System.out.println("\nId: " + r.getId() + "\nFecha: " + r.getFecha() + "\nMonto Pagado: " + r.getMontoPagado()));
			
			boolean existe = false;
			Reserva reserva = null;
			long id = 0;
			while(!existe) {
				try {
		        	id = ingresarNumeroEntero("Ingresar el id de la reserva que desea consultar: ");
					reserva = reservaDao.obtenerReserva(id);
					existe=true;
		        } catch (NoResultException e) {
		            System.out.println("Reserva con ID " + id + " no encontrado.");
		            throw e;
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Ocurrió un error al buscar la Reserva.");
		        }
			}
			reserva.mostrarDatos();
		}
		else {
			System.out.println("No hay reservas disponibles");
		}
	}
	
	public static void consultarSalones() {
		List<Salon> salones = salonDao.obtenerSalones();
		salones.stream().forEach(System.out::println);
	}
}
