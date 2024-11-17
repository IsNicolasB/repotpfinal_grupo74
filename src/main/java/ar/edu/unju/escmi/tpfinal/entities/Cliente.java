package ar.edu.unju.escmi.tpfinal.entities;

public class Cliente {
	private Long id;
	private Long dni;
	private String nombre;
	private String apellido;
	private String domicilio;
	private String telefono;
	private boolean estado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nId: ").append(id).append("\nNombre: ").append(nombre).append("\nApellido: ")
				.append(apellido).append("\nDomicilio: ").append(domicilio).append("\nDni: ").append(dni).append("\nTelefono: ").append(telefono)
				.append("\nEstado: ").append(estado == true ? "Disponible" : "No disponible");
		return builder.toString();
	}
	
	public void mostrarCliente() {
		System.out.println(toString());
	}
}
