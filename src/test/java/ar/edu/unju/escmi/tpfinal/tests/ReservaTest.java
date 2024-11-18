package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.dao.IReservaDao;
import ar.edu.unju.escmi.tpfinal.dao.ISalonDao;
import ar.edu.unju.escmi.tpfinal.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.SalonDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ServicioAdicionalDaoImp;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;
import ar.edu.unju.escmi.tpfinal.entities.Salon;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservaTest {
	
    static private IReservaDao reservaDao = new ReservaDaoImp();
    static private IClienteDao clienteDao = new ClienteDaoImp();
    static private ISalonDao salonDao = new SalonDaoImp();
    static private Reserva reservaExpected;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception{
    	Cliente cliente = new Cliente( null, "Nahuel" , "Vilte", "Reyes","388888", true);
        
    	clienteDao.guardarCliente(cliente);
    	Salon salon = new Salon("Galaxy", 0, false, 20000);
    	salonDao.guardarSalon(salon);
        // Crear servicios adicionales simulados
        List<ServicioAdicional> servicios = new ArrayList<ServicioAdicional>();
        ServicioAdicional servicio1 = new ServicioAdicional("Decoración", 5000, true);
        
        ServicioAdicional servicio2 = new ServicioAdicional("Catering", 5000, true);
        servicios.add(servicio1);
        servicios.add(servicio2);

        // Configurar reserva
        reservaExpected = new Reserva(cliente, salon, LocalDate.of(2024, 11, 5),
                LocalTime.of(14, 0), LocalTime.of(20, 0), servicios, 10000, false);
        
        reservaDao.guardarReserva(reservaExpected);
    }
    
    @Test
    @Order(1)
    void testGuardarReserva() {
    	Reserva reservaTest = reservaDao.obtenerReserva(reservaExpected.getId());
    	assertNotNull(reservaTest,"La reserva no se guardó correctamente");
    }
    
    @Test
    @Order(2)
    void testCalcularCostoHorarioExtendido() {
        double costoExtendido = reservaExpected.calcularCostoHorarioExtendido();
        assertEquals(20000, costoExtendido, "El costo extendido debería ser 20,000 para las 2 horas extras.");
    }

    @Test
    @Order(3)
    void testCalcularMontoTotal() {
        double montoTotal = reservaExpected.calcularMontoTotal();
        assertEquals(50000, montoTotal, "El monto total debería incluir el precio del salón, el costo extendido y el precio de los servicios.");
    }

    @Test
    @Order(4)
    void testCalcularPagoPendiente() {
        double pagoPendiente = reservaExpected.calcularPagoPendiente();
        assertEquals(40000, pagoPendiente, "El pago pendiente debería ser la diferencia entre el monto total y lo pagado.");
    }
    
    @Test
    @Order(5)
    void testRealizarPago() {
    	Reserva reservaTest = reservaDao.obtenerReserva(reservaExpected.getId());
    	reservaTest.realizarPago(10000);
    	assertEquals(30000, reservaTest.calcularPagoPendiente(), "El pago pendiente debería ser 30000");
    	assertEquals(20000, reservaTest.getMontoPagado(), "El monto pagado debería ser 20000");
    	reservaTest.realizarPago(30000);
    	assertEquals(reservaTest.isCancelado(), true, "El pago ya debería estar cancelado");
    	reservaExpected = reservaTest;
    	reservaDao.modificarReserva(reservaTest);
    }
    
    @Test
    @Order(6)
    void testVerificarCambios() {
    	assertEquals(reservaExpected, reservaDao.obtenerReserva(reservaExpected.getId()), "No se guardaron los cambios modificados");
    }
}

