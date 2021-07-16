package com.ceiba.usuario.comando;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCita {
	
	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private String hora;
	private int valor;
	private Long idPersona;

}
