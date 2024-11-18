package ar.edu.unju.escmi.tpfinal.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

public class ServicioAdicionalDaoImp implements IServicioAdicionalDao {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
    @Override
    public void guardarServicioAdicional(ServicioAdicional servicioAdicional) {
        try {
            manager.getTransaction().begin();
            manager.persist(servicioAdicional);
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(manager.getTransaction() != null) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo guardar el servicio");
        }
    }

    @Override
    public ServicioAdicional obtenerServicioAdicional(Long servicioId) {
        return manager.find(ServicioAdicional.class, servicioId);
    }

    @Override
    public List<ServicioAdicional> obtenerServiciosAdicionales() {
        TypedQuery<ServicioAdicional> query = manager.createQuery("SELECT s FROM ServicioAdicional s", ServicioAdicional.class);
        List<ServicioAdicional> servicios = query.getResultList();
        return servicios;
    }

	}

