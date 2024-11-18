package ar.edu.unju.escmi.tpfinal.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "reservas")
public class Reserva {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "res_id")
	 private Long id;

	 @ManyToOne
	 @JoinColumn(name = "cli_id", nullable = false)
	 private Cliente cliente;

	 @ManyToOne
	 @JoinColumn(name = "sal_id", nullable = false)
	 private Salon salon;
	 
	 @Column(name = "res_fecha")
	 private LocalDate fecha;

	 @Column(name = "res_hora_inicio")
	 private LocalTime horaInicio;

	 @Column(name = "res_hora_fin")
	 private LocalTime horaFin;

	 @Column(name = "res_monto_pagado")
	 private double montoPagado;

	 @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<ServicioAdicional> serviciosAdicionales;

	 @Column(name = "res_pago_adelantado")
	 private double pagoAdelantado;

	 @Column(name = "res_pagado")
	 private boolean cancelado;

	 @Column(name = "res_estado")
	 private boolean estado;
	 
	 public Reserva() {
		// TODO Auto-generated constructor stub
	}
	 
	 public Reserva(Cliente cliente, Salon salon, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
			 List<ServicioAdicional> serviciosAdicionales, double pagoAdelantado, boolean estado) {
		super();
		this.cliente = cliente;
		this.salon = salon;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.montoPagado = pagoAdelantado;
		this.serviciosAdicionales = serviciosAdicionales;
		this.pagoAdelantado = pagoAdelantado;
		this.cancelado = calcularPagoPendiente() == 0 ? true : false;
		this.estado = estado;
	}
	 
	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public List<ServicioAdicional> getServiciosAdicionales() {
		return serviciosAdicionales;
	}

	public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
		this.serviciosAdicionales = serviciosAdicionales;
	}

	public double getPagoAdelantado() {
		return pagoAdelantado;
	}

	public void setPagoAdelantado(double pagoAdelantado) {
		this.pagoAdelantado = pagoAdelantado;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double calcularCostoHorarioExtendido(){
		 Duration duracion = Duration.between(horaInicio, horaFin);
		 if (duracion.toHours() > 4) {
		     double costoPorHora = 10000;
			 return costoPorHora * (duracion.toHours() - 4);
		 }
		 else return 0;
	 }
	 
	 public double calcularMontoTotal() {
		 double montoTotalServicios = this.serviciosAdicionales.stream().filter(sv -> sv.isEstado() == true)
				 						  .mapToDouble(sv-> sv.getPrecio()).sum(); 
		 double montoTotal = this.salon.getPrecio() + calcularCostoHorarioExtendido() + montoTotalServicios;
		 return montoTotal;
	 }
	 
	 public void mostrarDatos() {
		 System.out.println(toString());
	 }
	 
	 @Override
	 public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nId: ").append(id).append("\nCliente: ").append(cliente).append("\nSalon: ").append(salon)
				.append("\nFecha: ").append(fecha).append("\nHoraInicio: ").append(horaInicio).append("\nHoraFinal: ")
				.append(horaFin).append("\nMontoPagado: ").append(montoPagado).append("\nPagoAdelantado: ")
				.append(pagoAdelantado).append("\nCancelado: ").append(cancelado == true ? "CANCELADO" : "PAGO PENDIENTE")
				.append("\nEstado: ").append(estado);
		return builder.toString();
	 }

	 public double calcularPagoPendiente() {
		 return (calcularMontoTotal() - montoPagado);
	 }
	 
	 public void mostrarPagoPendiente() {
		 System.out.println("El monto pendiente es: " + calcularPagoPendiente() );
	 }
	 
	 public void realizarPago(double monto) {
		 this.montoPagado += monto;
		 this.cancelado = calcularPagoPendiente() == 0 ? true : false;
	 }
	 
}
