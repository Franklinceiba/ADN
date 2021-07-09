package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDate;

import com.ceiba.usuario.modelo.entidad.Persona;

public class PersonaTestDataBuilder {

	private Long id;
	private String tipoDocumento;
	private String documento;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String celular;
	private String email;
	
	public PersonaTestDataBuilder() {
		tipoDocumento = "c.c";
		nombre = "luis";
		apellido = "pineda";
		fechaNacimiento = LocalDate.now();
		celular = "3177093283";
		email = "franklin@gmail.com";
	}
	
	public PersonaTestDataBuilder conDocumento(String documento) {
		this.documento = documento;
		return this;
	}
	
	public PersonaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public Persona build() {
		return new Persona(id, tipoDocumento, documento, nombre, apellido, fechaNacimiento, celular, email);
	}
	
}
