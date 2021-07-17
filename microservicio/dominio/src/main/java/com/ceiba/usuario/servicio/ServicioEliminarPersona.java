package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionRelacionado;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;

public class ServicioEliminarPersona {
	
	private static final String PERSONA_TIENE_REGISTRO_EN_UNA_CITA = "No se puede eliminar la persona por que existe un registro con una cita medica en el consultorio";
	
	private final RepositorioPersona repositorioPersona;

	public ServicioEliminarPersona(RepositorioPersona repositorioPersona) {
		this.repositorioPersona = repositorioPersona;
	}
	
	public void ejecutar(Long id) {
		validarExistenciaCita(id);
		this.repositorioPersona.eliminar(id);
	}
	
	private void validarExistenciaCita(Long id) {
		boolean existeRegistroCita = this.repositorioPersona.existePorCita(id);
		if (existeRegistroCita) {
			throw new ExcepcionRelacionado(PERSONA_TIENE_REGISTRO_EN_UNA_CITA);
		}
	}
	
}
