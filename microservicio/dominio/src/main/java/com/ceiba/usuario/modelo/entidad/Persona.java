package com.ceiba.usuario.modelo.entidad;

import java.time.LocalDate;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;

import lombok.Getter;

@Getter
public class Persona {
	
	private static final String CAMPO_TIPO_DOCUMENTO_ES_OBLIGATORIO = "El campo tipo de documento es obligatorio no puede ir vacio";
	private static final String CAMPO_DOCUMENTO_ES_OBLIGATORIO = "El campo documento es obligatorio no puede ir vacio";
	private static final String CAMPO_NOMBRE_ES_OBLIGATORIO = "El campo nombre es obligatorio no puede ir vacio";
	private static final String CAMPO_APELLIDO_ES_OBLIGATORIO = "El campo apellido es obligatorio no puede ir vacio";
	private static final String CAMPO_FECHA_NACIMIENTO_ES_OBLIGATORIO = "El campo fecha de nacimiento es obligatorio no puede ir vacio";
	private static final String CAMPO_CELULAR_ES_OBLIGATORIO = "El campo celular es obligatorio no puede ir vacio";
	private static final String CAMPO_EMAIL_ES_OBLIGATORIO = "El campo email es obligatorio no puede ir vacio";
	
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
		this.validarVacioTipoDocumento(tipoDocumento);
		this.validarVacioDocumento(documento);
		this.validarVacioNombre(nombre);
		this.validarVacioApellido(apellido);
		this.validarVacioFechaNacimiento(fechaNacimiento);
		this.validarVacioCelular(celular);
		this.validarVacioEmail(email);
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.celular = celular;
		this.email = email;
	}
	
	private void validarVacioTipoDocumento(String tipoDocumento) {
		if (tipoDocumento == null) {
			throw new ExcepcionValorObligatorio(CAMPO_TIPO_DOCUMENTO_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioDocumento(String documento) {
		if (documento == null) {
			throw new ExcepcionValorObligatorio(CAMPO_DOCUMENTO_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioNombre(String nombre) {
		if (nombre == null) {
			throw new ExcepcionValorObligatorio(CAMPO_NOMBRE_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioApellido(String apellido) {
		if (apellido == null) {
			throw new ExcepcionValorObligatorio(CAMPO_APELLIDO_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new ExcepcionValorObligatorio(CAMPO_FECHA_NACIMIENTO_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioCelular(String celular) {
		if (celular == null) {
			throw new ExcepcionValorObligatorio(CAMPO_CELULAR_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioEmail(String email) {
		if (email == null) {
			throw new ExcepcionValorObligatorio(CAMPO_EMAIL_ES_OBLIGATORIO);
		}
	}
}
