package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tpfinal.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.ServicioAdicionalDaoImp;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

class ConsultarServiciosAdicionalesTest {
	private List<ServicioAdicional> expected;
	private IServicioAdicionalDao servicioAdicionalDao = new ServicioAdicionalDaoImp();
	@BeforeEach
	void setUp() throws Exception {
		expected=new ArrayList<ServicioAdicional>();
		ServicioAdicional test1 = new ServicioAdicional("test1",1000.0,true);
		servicioAdicionalDao.guardarServicioAdicional(test1);
		expected.add(test1);
		ServicioAdicional test2 = new ServicioAdicional("test2",1000.0,true);
		servicioAdicionalDao.guardarServicioAdicional(test2);
		expected.add(test2);
	}

	@Test
	void testConsultarServiciosAdicionales() {
		assertEquals(expected, servicioAdicionalDao.obtenerServiciosAdicionales(),"Los servicios adicionales no se guardaron correctamente");
	}
	

}
