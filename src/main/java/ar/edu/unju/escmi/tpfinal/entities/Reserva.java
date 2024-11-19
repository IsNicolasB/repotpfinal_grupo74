package ar.edu.unju.escmi.tpfinal.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import ar.edu.unju.escmi.tpfinal.exceptions.InvalidTimeRangeException;
import ar.edu.unju.escmi.tpfinal.utils.TimeUtil;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

	 @ManyToMany()
	 @JoinTable(
	     name = "reserva_servicio",
	     joinColumns = @JoinColumn(name = "res_id"),
	     inverseJoinColumns = @JoinColumn(name = "serv_id")
	 )
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

	public LocalTime getHoraFin() {
		return horaFin;
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
		this.montoPagado += pagoAdelantado;
		this.pagoAdelantado = pagoAdelantado;
	}
	
	public boolean isCancelado() {
		return cancelado;
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
		 System.out.println("-------------------------------");
		 System.out.println("Reserva id: " + id 
				 		+ "\nCliente: " + this.cliente.getNombre() + " " + cliente.getApellido() + ", DNI: " + cliente.getDni()
				 		+ "\nSalon: " + salon.getNombre() + ", Precio: " + salon.getPrecio());
		 System.out.println("-------------------------------" + "\nServicios Adicionales: ");
		 serviciosAdicionales.forEach(sv -> System.out.println(sv.getDescripcion() + ", Precio: " + sv.getPrecio()));
		 System.out.println("-------------------------------");
		 System.out.println("Fecha de Reserva: " + fecha 
				 			+ "\nHora de Inicio: " + horaInicio + ", Hora de Finalización: " + horaFin
				 			+ "\nEstado del Pago: " + (cancelado == true ? "CANCELADO" : "PAGO PENDIENTE")
				 			+ "\nEstado de la Resreva: " + (estado == true ? "ACTIVA" : "INACTIVA"));
		 System.out.println("-------------------------------");
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
	 
	 public void setHoras(String inicio, String fin) {
	        this.horaInicio = TimeUtil.parseTime(inicio);
	        this.horaFin = TimeUtil.parseTime(fin);    
	        validarHoras(); 
	    }
	 
	 private void validarHoras() {
	        if (!this.horaInicio.isBefore(this.horaFin)) {
	            throw new InvalidTimeRangeException(
	                "La hora de inicio debe ser anterior a la hora de fin. Inicio: " 
	                + this.horaInicio + ", Fin: " + this.horaFin
	            );
	        }

	        Duration duracion = Duration.between(this.horaInicio, this.horaFin);
	        if (duracion.toHours() < 4) {
	            throw new InvalidTimeRangeException(
	                "La diferencia entre la hora de inicio y la hora de fin debe ser de al menos 4 horas. "
	                + "Inicio: " + this.horaInicio + ", Fin: " + this.horaFin + ", Duración: " + duracion.toHours() + " horas."
	            );
	        }
	        
	        LocalTime limiteInferior = LocalTime.of(10, 0); // 10:00 AM
	        LocalTime limiteSuperior = LocalTime.of(23, 0); // 11:00 PM
	        
	        if (this.horaInicio.isBefore(limiteInferior) || this.horaFin.isAfter(limiteSuperior)) {
	            throw new InvalidTimeRangeException(
	                "Las horas deben estar entre las 10:00 AM y las 11:00 PM. "
	                + "Inicio: " + this.horaInicio + ", Fin: " + this.horaFin
	            );
	        }
	    }
	 
}
