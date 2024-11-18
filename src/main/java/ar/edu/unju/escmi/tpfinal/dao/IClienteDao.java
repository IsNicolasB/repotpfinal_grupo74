package ar.edu.unju.escmi.tpfinal.dao;

import java.util.List;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.exceptions.ClienteNoEncontradoException;

public interface IClienteDao {
	void guardarCliente(Cliente cliente);
	void modificarCliente(Cliente cliente);
	Cliente obtenerCliente(Long clienteId) throws ClienteNoEncontradoException;
	List<Cliente> obtenerClientes();
}
