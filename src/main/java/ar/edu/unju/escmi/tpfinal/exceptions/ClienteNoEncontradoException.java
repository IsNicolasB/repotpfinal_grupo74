package ar.edu.unju.escmi.tpfinal.exceptions;

public class ClienteNoEncontradoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ClienteNoEncontradoException(String mensaje) {
		super(mensaje);
	}
}
