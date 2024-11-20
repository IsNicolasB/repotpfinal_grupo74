package ar.edu.unju.escmi.tpfinal.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.NoResultException;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;
import ar.edu.unju.escmi.tpfinal.entities.Salon;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;
import ar.edu.unju.escmi.tpfinal.exceptions.ClienteNoEncontradoException;
import ar.edu.unju.escmi.tpfinal.exceptions.InvalidTimeRangeException;
import ar.edu.unju.escmi.tpfinal.utils.FechaUtil;
import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.dao.IReservaDao;
import ar.edu.unju.escmi.tpfinal.dao.ISalonDao;
import ar.edu.unju.escmi.tpfinal.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.SalonDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ServicioAdicionalDaoImp;

public class Main {
	public static Scanner scanner = new Scanner(System.in);
	public static IClienteDao clienteDao = new ClienteDaoImp();
	public static ISalonDao salonDao = new SalonDaoImp();
	public static IReservaDao reservaDao = new ReservaDaoImp();
	public static IServicioAdicionalDao servicioAdicionalDao = new ServicioAdicionalDaoImp();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion; 
		precargarClientes();
		precargarSalones();
		precargarServiciosAdicionales();
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
				consultarServiciosAdicionales();
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
	
	public static void precargarSalones() {
		salonDao.guardarSalon(new Salon("Cosmos", 60, false, 60000.0));
		salonDao.guardarSalon(new Salon("Esmeralda", 20, false, 20000.0));
		salonDao.guardarSalon(new Salon("Galaxy", 100, true, 150000.0));
	}
	
	public static void precargarServiciosAdicionales() {
		servicioAdicionalDao.guardarServicioAdicional(new ServicioAdicional("Camara 360",1000,true));
		servicioAdicionalDao.guardarServicioAdicional(new ServicioAdicional("Cabina de fotos",2000,true));
		servicioAdicionalDao.guardarServicioAdicional(new ServicioAdicional("Filmacion",500,true));
		servicioAdicionalDao.guardarServicioAdicional(new ServicioAdicional("Pintacaritas",500,true));
	}

	public static void precargarClientes() {
		clienteDao.guardarCliente(new Cliente((long)44444444,"Nicolás","Burgos","Perico","38881234",true));
		clienteDao.guardarCliente(new Cliente((long)44444443,"Nahuel","Vilte","Reyes","385345234",true));
		clienteDao.guardarCliente(new Cliente((long)44444442,"Jael","Silva","Alto Comedero","332344534",true));
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
		
		String nombre = ingresarStringConEspacios( "Registrar el nombre del Cliente: ");
		String apellido = ingresarString( "Registrar el apellido del Cliente: ");
		String domicilio = ingresarStringConEspacios( "Registrar el domicilio del Cliente: ");
		String telefono = ingresarString( "Registrar el telefono del cliente: ");
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
				break;
			case 5 :
				System.out.println("Se finalizó la modicación");
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		}while(opcion != 5);
		
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
	        } catch (ClienteNoEncontradoException e) {
	        	System.out.println("ERROR: " + e.getMessage());
	        }
		}
		
		return cliente;
	}
	
	public static Salon seleccionarSalon(String texto) {
		Salon salon = null;
		boolean existe = false;
		long id = 0 ;
		
		consultarSalones();
		
		while(!existe) {
			
	        try {
	        	id = ingresarNumeroEntero(texto);
				salon = salonDao.obtenerSalon(id);
				if(salon != null)existe=true;
				else System.out.println("Salon con ID: " + id + " no encontrado.");
	        }catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Ocurrió un error al buscar el salon.");
	        }
		}
		
		return salon;
	}
	
	public static List<ServicioAdicional> seleccionarServicios(String texto) {
		List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
		
		boolean existe = false;
		long id = 0 ;
		int opcion = 0;
		consultarServiciosAdicionales();
		do {
			ServicioAdicional servicioAdicional = null; 
			while(!existe) {
				
		        try {
		        	id = ingresarNumeroEntero(texto);
					servicioAdicional = servicioAdicionalDao.obtenerServicioAdicional(id);
					if(servicioAdicional != null)existe=true;
					else System.out.println("Servicio Adicional con ID: " + id + " no encontrado.");
		        }catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Ocurrió un error al buscar el salon.");
		        }
			}
			long servicioId = servicioAdicional.getId();
			
			if (serviciosAdicionales.stream().noneMatch(sv -> sv.getId().equals(servicioId))) {
			    serviciosAdicionales.add(servicioAdicional);
			    System.out.println("se agrego");
			} else {
			    System.out.println("El servicio ya ha sido agregado anteriormente.");
			}
			
			do {
				System.out.println("¿Desea agregar más servicios?" + "\n1-SI" + "\n2-NO");
				opcion = ingresarNumeroEntero("Ingrese una opción: ");
				existe = false;
			}while(opcion < 1 || opcion > 2);
			
		}while(opcion != 2);
		
		return serviciosAdicionales;
	}
	
	public static double realizarPago(Reserva reserva, String texto, boolean opcion) { //true para pago adelantado - false para otros pagos
		
		 boolean pagoValido = false;
		 double pago = 0.00;
	     double montoPendiente = reserva.calcularPagoPendiente();
	     
	     if(opcion == true) {
	    	 System.out.println("Monto Total: "+ montoPendiente);
		     while (!pagoValido)  {
		        	pago = ingresarNumeroDouble(texto);
		        
		        	if (pago < reserva.calcularMontoTotal()) pagoValido = true;
		        	else System.out.println("¡ Ingrese un monto menor a "+montoPendiente+" !");
		     }
	     }
	     else {
	    	 System.out.println("Monto Pendiente: "+ montoPendiente);
		     while (!pagoValido)  {
		        	pago = ingresarNumeroDouble(texto);
		        
		        	if (pago <= reserva.calcularMontoTotal()) pagoValido = true;
		        	else System.out.println("¡ Ingrese un monto menor o igual a "+montoPendiente+" !");
		     }
	     }
	        
	     return pago;
	}
	
	public static void realizarReserva() {
		
		Reserva reserva = new Reserva();
		
		if(clienteDao.obtenerClientes().stream().filter(c -> c.isEstado() == true).count() == 0) {
			System.out.println("No hay clientes disponibles para realizar una reserva");
			return;
		}
		
		Cliente cliente = seleccionarCliente("Ingrese el id cliente que realizará la reserva: ");
		reserva.setCliente(cliente);
		
		Salon salon = seleccionarSalon("Ingrese el id del salon que quiere reservar: ");
		reserva.setSalon(salon);
		
		List<ServicioAdicional> serviciosAdicionales = seleccionarServicios("Ingrese el servicio que desea agregar: ");
		//serviciosAdicionales.forEach(s -> s.mostrarDatos());
		reserva.setServiciosAdicionales(serviciosAdicionales);
		
		LocalDate fechaReserva = null, fechaActual = LocalDate.now();
	    boolean esValido = false;
	    List<Reserva> reservas = reservaDao.obtenerReservas();
		while (!esValido) {
	    	try {
	    		String aux = ingresarString("Ingrese la fecha del prestamo en formato dd/MM/yyyy: ");
	    		LocalDate fecha = FechaUtil.convertirStringLocalDate(aux);
	    		//Recorro las reservas activas, si el salón ya se reservó en esa fecha, se debe volver a ingresar la fecha
	    		if(fecha.isBefore(fechaActual)) {
	    			System.out.println("No se puede reservar en una fecha pasada");
	    			continue;
	    		}
	    		if(reservas.stream().filter(r-> r.isEstado() == true && r.getFecha().equals(fecha) && r.getSalon().equals(salon)).count() == 0) {
	    			esValido = true;
	    			fechaReserva = fecha;
	    		}
	    		else System.out.println("El salón ya está reservado en esa fecha, ingrese otra");
	    	} catch (IllegalArgumentException e) {
	    		System.out.println(e.getMessage());
	    	}
	    }
		
		reserva.setFecha(fechaReserva);
		
		boolean horasValidas = false;

        while (!horasValidas) {
            try {
                System.out.print("Ingrese la hora de inicio (formato HH:mm): ");
                String horaInicio = scanner.next();

                System.out.print("Ingrese la hora de fin (formato HH:mm): ");
                String horaFin = scanner.next();

                reserva.setHoras(horaInicio, horaFin);

                // Si no lanza ningúna excepción, las horas son válidas
                horasValidas = true;
                System.out.println("Horas asignadas correctamente.");
            } catch (InvalidTimeRangeException | IllegalArgumentException e) {
            	
                System.err.println("Error: " + e.getMessage());
                System.out.println("Por favor, ingrese las horas nuevamente.");
                scanner.nextLine();
            }
        } 
		
        double pagoAdelantado = realizarPago(reserva,"Ingrese el monto que pagara por adelantado, este debe ser menor que el total: ", true);
        reserva.setPagoAdelantado(pagoAdelantado);
        reserva.setEstado(true);
        
        reservaDao.guardarReserva(reserva);
        
        System.out.println("¡ Se realizo la reserva correctamente !");
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
					
					if (reserva == null) {
		                System.out.println("Reserva con ID " + id + " no encontrada. Intente nuevamente.");
		            } else existe =true;
					
		        } catch (NoResultException e) {
		            System.out.println("Reserva con ID " + id + " no encontrado.");
		            throw e;
		        } catch (Exception e) {
		            e.printStackTrace();
		            System.out.println("Ocurrió un error al buscar la Reserva.");
		        }
			}
			
			double pago = realizarPago(reserva, "Ingrese el monto que pagara este debe ser menor o igual que el monto Pendiente: ",false);
			reserva.realizarPago(pago);
            reservaDao.modificarReserva(reserva);
	
		}
		else {
			System.out.println("No hay reservas pendientes a pagar");
		}
	}
	
	public static void consultarReservas() {
		if(reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true).count() > 0) {
			reservaDao.obtenerReservas().stream().filter(r -> r.isEstado() == true).forEach(r -> r.mostrarDatos());
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
					if(reserva != null ) existe=true;
					else System.out.println("Reserva con ID " + id + " no encontrado.");
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

	public static void consultarServiciosAdicionales() {
		servicioAdicionalDao.obtenerServiciosAdicionales().stream().forEach(sv -> sv.mostrarDatos());
	}

}