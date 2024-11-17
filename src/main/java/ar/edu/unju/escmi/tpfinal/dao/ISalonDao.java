package ar.edu.unju.escmi.tpfinal.dao;

import java.util.List;
import ar.edu.unju.escmi.tpfinal.entities.Salon;

public interface ISalonDao {
	void guardarSalon(Salon salon);
	Salon obtenerSalon(Long salonId);
	List<Salon> obtenerSalones();
}
