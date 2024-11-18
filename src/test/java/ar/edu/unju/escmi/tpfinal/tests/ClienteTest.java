package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.exceptions.ClienteNoEncontradoException;
import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ClienteDaoImp;

class ClienteTest {

	public static IClienteDao clienteDao = new ClienteDaoImp();
	Cliente expected;
	
	@BeforeEach
	void setUp() throws Exception {
		expected = new Cliente(null, "Nahuel", "Vilte", "Reyes", "+5493885311540", true);

        clienteDao.guardarCliente(expected);
	}
	
	@AfterEach
	void tearDown() throws Exception{
		expected = new Cliente(null, "Nahuel", "Vilte", "Reyes", "+5493885311540", true);
	}
	
    @Test
    @Order(1)
    void testGuardarCliente() throws ClienteNoEncontradoException {

        // Verificar que se guardó correctamente
        Cliente clienteGuardado = clienteDao.obtenerCliente(expected.getId());
        assertNotNull(clienteGuardado, "El cliente no se guardó en la base de datos.");
        assertEquals("Nahuel", clienteGuardado.getNombre());
        assertEquals("+5493885311540", clienteGuardado.getTelefono());
    }
    
    @Test
    @Order(2)
    void testModificarCliente() throws ClienteNoEncontradoException {
    	
        // Modificar el cliente
        expected.setNombre("Daniel");
        expected.setTelefono("+549388111111");
        clienteDao.modificarCliente(expected);

        // Verificar que el cliente fue modificado
        Cliente modificado = clienteDao.obtenerCliente(expected.getId());
        assertNotNull(modificado, "El cliente modificado no se encontró en la base de datos.");
        assertEquals("Daniel", modificado.getNombre());
        assertEquals("+549388111111", modificado.getTelefono());
    	
    	
    }
}

