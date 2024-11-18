package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tpfinal.dao.IReservaDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;
import ar.edu.unju.escmi.tpfinal.entities.Salon;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

class ReservaTest {
	
    private IReservaDao reservaDao = new ReservaDaoImp();
    Reserva reservaExpected;
    
    @BeforeEach
    void setUp() throws Exception{
    	Cliente cliente = new Cliente( (long) 111, "Nahuel" , "Vilte", "Reyes","388888", true);
        Salon salon = new Salon();
        salon.setPrecio(20000);

        // Crear servicios adicionales simulados
        List<ServicioAdicional> servicios = new ArrayList();
        ServicioAdicional servicio1 = new ServicioAdicional("Decoración", 5000, true);
        ServicioAdicional servicio2 = new ServicioAdicional("Catering", 8000, false);
        servicios.add(servicio1);
        servicios.add(servicio2);

        // Configurar reserva
        reservaExpected = new Reserva(cliente, salon, LocalDate.of(2024, 11, 5),
                LocalTime.of(14, 0), LocalTime.of(20, 0), servicios, 10000, false);
    }

    @Test
    void testCalcularCostoHorarioExtendido() {
        double costoExtendido = reservaExpected.calcularCostoHorarioExtendido();
        assertEquals(20000, costoExtendido, "El costo extendido debería ser 40,000 para 4 horas adicionales.");
    }

    @Test
    void testCalcularMontoTotal() {
        double montoTotal = reservaExpected.calcularMontoTotal();
        assertEquals(45000, montoTotal, "El monto total debería incluir el precio del salón y el costo extendido.");
    }

    @Test
    void testCalcularPagoPendiente() {
        double pagoPendiente = reservaExpected.calcularPagoPendiente();
        assertEquals(35000, pagoPendiente, "El pago pendiente debería ser la diferencia entre el monto total y lo pagado.");
    }

}
