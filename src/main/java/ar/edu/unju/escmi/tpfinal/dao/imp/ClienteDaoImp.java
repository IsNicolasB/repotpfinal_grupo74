package ar.edu.unju.escmi.tpfinal.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;

public class ClienteDaoImp implements IClienteDao {

	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void guardarCliente(Cliente cliente) {
		try {
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar el Cliente");
		}
		
	}

	@Override
	public void modificarCliente(Cliente cliente) {
		try {
			manager.getTransaction().begin();
			manager.merge(cliente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo modificar el cliente");
		}
		
	}

	@Override
	public Cliente obtenerCliente(Long clienteId) {
		return manager.find(Cliente.class, clienteId);
	}

	@Override
	public List<Cliente> obtenerClientes() {
		TypedQuery<Cliente> query = manager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}

}
