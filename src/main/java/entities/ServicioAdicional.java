package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

    
    public ServicioAdicional() {
    }

    
    public ServicioAdicional(Long id, String descripcion, double precio, boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
