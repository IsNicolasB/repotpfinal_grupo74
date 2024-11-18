package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tpfinal.dao.ISalonDao;
import ar.edu.unju.escmi.tpfinal.dao.imp.SalonDaoImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tpfinal.entities.Salon;

class ConsultarSalonesTest {
	private List<Salon> expected;
	private ISalonDao salonDao = new SalonDaoImp();
	@BeforeEach
	void setUp() throws Exception {
		expected=new ArrayList<Salon>();
		Salon test1 = new Salon("Test1", 20, true, 10000.0);
		salonDao.guardarSalon(test1);
		expected.add(test1);
		Salon test2 = new Salon("Test2", 20, true, 10000.0);
		salonDao.guardarSalon(test2);
		expected.add(test2);
	}

	@Test
	void testConsultarSalones() {
		assertEquals(expected, salonDao.obtenerSalones(),"Los salones no se guardaron correctamente");
	}
}
