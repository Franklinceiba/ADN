package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ceiba.usuario.comando.ComandoCita;

public class ComandoCitaTestDataBuilder {

	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private int valor;
	private Long idPersona;
	
	public ComandoCitaTestDataBuilder() {
		descripcion = "Presento sintomas de tos";
		valor = 0;
		idPersona = 1l;
	}
	
	public ComandoCitaTestDataBuilder conFechaYHora(LocalDate localDate, LocalTime localTime) {
		this.fecha = localDate;
		this.hora = localTime;
		return this;
	}
	
	public ComandoCitaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ComandoCita build() {
		return new ComandoCita(id, descripcion, fecha, hora, valor, idPersona);
	}
}
