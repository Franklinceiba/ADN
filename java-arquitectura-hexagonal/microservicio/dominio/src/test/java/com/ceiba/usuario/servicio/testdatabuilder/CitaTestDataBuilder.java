package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ceiba.usuario.modelo.entidad.Cita;

public class CitaTestDataBuilder {
	
	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private int valor;
	private Long idPersona;
	
	public CitaTestDataBuilder() {
		descripcion = "Presento sintomas de tos";
		valor = 0;
		idPersona = 1l;
	}
	
	public CitaTestDataBuilder conFechaYHora(LocalDate localDate, LocalTime localTime) {
		this.fecha = localDate;
		this.hora = localTime;
		return this;
	}
	
	public CitaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public Cita build() {
		return new Cita(id, descripcion, fecha, hora, idPersona);
	}
	

}
