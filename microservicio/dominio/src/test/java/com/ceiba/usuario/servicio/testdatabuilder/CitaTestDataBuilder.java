package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDate;

import com.ceiba.usuario.modelo.entidad.Cita;

public class CitaTestDataBuilder {
	
	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private String hora;
	private Long idPersona;
	
	public CitaTestDataBuilder() {
		descripcion = "Presento sintomas de tos";
		idPersona = 1l;
	}
	
	public CitaTestDataBuilder(String descripcion, LocalDate fecha, String hora, Long idPersona) {
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
		this.idPersona = idPersona;
	}



	public CitaTestDataBuilder conFechaYHora(LocalDate localDate, String localTime) {
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
