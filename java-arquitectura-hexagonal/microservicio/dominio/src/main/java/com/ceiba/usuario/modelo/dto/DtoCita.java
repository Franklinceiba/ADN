package com.ceiba.usuario.modelo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ceiba.usuario.modelo.entidad.Persona;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCita {
	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private int valor;
	private Long idPersona;

}
