package com.ceiba.usuario.modelo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPersona {
	private Long id;
	private String tipoDocumento;
	private String documento;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String celular;
	private String email;

}
