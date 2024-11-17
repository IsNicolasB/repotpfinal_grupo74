package ar.edu.unju.escmi.tpfinal.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.ISalonDao;
import ar.edu.unju.escmi.tpfinal.entities.Salon;

public class SalonDaoImp implements ISalonDao{
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	@Override
	public void guardarSalon(Salon salon) {
		try {
			manager.getTransaction().begin();
			manager.persist(salon);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar la factura");
		}
	}

	@Override
	public Salon obtenerSalon(Long salonId) {
		return manager.find(Salon.class, salonId);
	}

	@Override
	public List<Salon> obtenerSalones() {
		TypedQuery<Salon> query = manager.createQuery("SELECT s FROM Salon s", Salon.class);
		List<Salon> salones = query.getResultList();
		return salones;
	}

}
