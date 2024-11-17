package ar.edu.unju.escmi.tpfinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "salon")
public class Salon {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "sal_id")
	private Long id;
	@Column (name = "sal_nombre", nullable = false)
	private String nombre;
	@Column (name = "sal_capacidad", nullable = false)
	private int capacidad;
	@Column (name = "sal_pileta", nullable = false)
	private boolean conPileta;
	@Column (name = "sal_precio", nullable = false)
    private double precio;
	
	public Salon() {
		
	}

	public Salon(Long id, String nombre, int capacidad, boolean conPileta, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.conPileta = conPileta;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isConPileta() {
		return conPileta;
	}

	public void setConPileta(boolean conPileta) {
		this.conPileta = conPileta;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nId: ").append(id).append("\nNombre: ").append(nombre).append("\nCapacidad: ")
				.append(capacidad).append("\nCon Pileta: ").append(conPileta == true ? "Si" : "No").append("\nPrecio: ").append(precio);
		return builder.toString();
	}
	
	public void mostrarDatos() {
		System.out.println(toString());
	}
}
