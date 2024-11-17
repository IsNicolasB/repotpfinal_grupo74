package ar.edu.unju.escmi.tpfinal.dao;

import java.util.List;

import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

public interface IServicioAdicionalDao {

	void guardarServicioAdicional(ServicioAdicional servicioAdicional);
	ServicioAdicional obtenerServicioAdicional(Long servicioId);
	List<ServicioAdicional> obtenerServiciosAdicionales();
	
}
