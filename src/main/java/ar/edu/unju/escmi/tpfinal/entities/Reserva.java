package ar.edu.unju.escmi.tpfinal.entities;

import java.time.LocalDate;
import java.time.LocalTime;

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

}
