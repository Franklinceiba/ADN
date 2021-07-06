package com.ceiba.usuario.modelo.entidad;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class Persona {

	private Long id;
	private String tipoDocumento;
	private String documento;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String celular;
	private String email;
	public Persona(Long id, String tipoDocumento, String documento, String nombre, String apellido,
			LocalDate fechaNacimiento, String celular, String email) {
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.celular = celular;
		this.email = email;
	}
	
	
}
