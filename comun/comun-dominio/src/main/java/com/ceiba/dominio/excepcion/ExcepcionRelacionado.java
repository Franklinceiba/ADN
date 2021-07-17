package com.ceiba.dominio.excepcion;

public class ExcepcionRelacionado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionRelacionado(String mensaje) {
		super(mensaje);
	}
}
