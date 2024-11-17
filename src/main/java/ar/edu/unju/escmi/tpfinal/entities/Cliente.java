package ar.edu.unju.escmi.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY)
	@Column (name = "cli_id")
	private Long id;
	@Column (name = "cli_dni", unique = true)
	private Long dni;
	@Column (name = "cli_nombre", nullable = false)
	private String nombre;
	@Column (name = "cli_apellido")
	private String apellido;
	@Column (name = "cli_domicilio")
	private String domicilio;
	@Column (name = "telefono")
	private String telefono;
	@Column (name = "cli_estado")
	private boolean estado;
	
	public Cliente(Long dni, String nombre, String apellido, String domicilio, String telefono,
			boolean estado) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.estado = estado;
	}

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
