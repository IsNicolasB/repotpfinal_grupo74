package ar.edu.unju.escmi.tpfinal.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.IReservaDao;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;

public class ReservaDaoImp implements IReservaDao{

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void guardarReserva(Reserva reserva) {
		try {
			manager.getTransaction().begin();
			manager.persist(reserva);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar la Reserva");
		}
		
	}

	@Override
	public Reserva obtenerReserva(Long id) {
		return manager.find(Reserva.class, id);
	}

	@Override
	public List<Reserva> obtenerReservas() {
		TypedQuery<Reserva> query = manager.createQuery("SELECT r FROM Reserva r", Reserva.class);
		List<Reserva> reservas = query.getResultList();
		return reservas;
	}

	@Override
	public void modificarReserva(Reserva reserva) {
		try {
			manager.getTransaction().begin();
			manager.merge(reserva);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo modificar la Reserva");
		}
		
	}

}
