package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDate;
import java.util.UUID;

import com.ceiba.usuario.comando.ComandoPersona;

public class ComandoPersonaTestDataBuilder {

	private Long id;
	private String tipoDocumento;
	private String documento;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String celular;
	private String email;
	
	public ComandoPersonaTestDataBuilder() {
		tipoDocumento = "c.c";
		documento = UUID.randomUUID().toString();
		nombre = "jose";
		apellido = UUID.randomUUID().toString();
		fechaNacimiento = LocalDate.now();
		celular = "3142644081";
		email = "jose@gmail.com";
	}
	
	public ComandoPersonaTestDataBuilder conNombreAndApellido(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		return this;
	}
	
	public ComandoPersonaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ComandoPersona build() {
		return new ComandoPersona(id, tipoDocumento, documento, nombre, apellido, fechaNacimiento, celular, email);
	}
	
	
}
