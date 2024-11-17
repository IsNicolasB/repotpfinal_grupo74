package ar.edu.unju.escmi.tpfinal.dao;

import java.util.List;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;

public interface IClienteDao {
	abstract void guardarCliente(Cliente cliente);
	void modificarCliente(Cliente cliente);
	Cliente obtenerCliente(Long clienteId);
	List<Cliente> obtenerClientes();
}
