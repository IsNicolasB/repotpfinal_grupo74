package ar.edu.unju.escmi.tpfinal.dao;

import java.util.List;

import ar.edu.unju.escmi.tpfinal.entities.Reserva;

public interface IReservaDao {
	void guardarReserva(Reserva reserva);
	void modificarReserva(Reserva reserva);
	Reserva obtenerReserva(Long id);
	List<Reserva> obtenerReservas();
}
