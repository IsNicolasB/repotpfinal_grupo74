package ar.edu.unju.escmi.tpfinal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servicio_adicional")
public class ServicioAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serv_id")
    private Long id;

    @Column(name = "serv_descripcion", nullable = false)
    private String descripcion;

    @Column(name = "serv_precio", nullable = false)
    private double precio;

    @Column(name = "serv_estado", nullable = false)
    private boolean estado;

    /*@ManyToMany(mappedBy = "serviciosAdicionales")
    private List<Reserva> reservas;*/
    
    public ServicioAdicional() {
    }

    
    public ServicioAdicional(String descripcion, double precio, boolean estado) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

   
    public Long getId() {
        return id;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

  
    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: " + precio);
        System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
    }
}
