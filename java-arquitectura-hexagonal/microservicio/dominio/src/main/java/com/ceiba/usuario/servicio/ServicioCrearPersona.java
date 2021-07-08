package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;

public class ServicioCrearPersona {
	
	private static final String PERSONA_YA_REGISTRADA_EN_EL_SISTEMA = "No se puede registrar la persona por que existe un registro con este documento";

	private final RepositorioPersona repositorioPersona;

	public ServicioCrearPersona(RepositorioPersona repositorioPersona) {
		this.repositorioPersona = repositorioPersona;
	}
	
	public Long ejecutar(Persona persona) {
		validarExistenciaDocumento(persona);
		return this.repositorioPersona.crear(persona);
	}
	
	private void validarExistenciaDocumento(Persona persona) {
		boolean existeRegistroDocumento = this.repositorioPersona.existe(persona.getTipoDocumento(), persona.getDocumento());
		if (existeRegistroDocumento) {
			throw new ExcepcionDuplicidad(PERSONA_YA_REGISTRADA_EN_EL_SISTEMA);
		}
	}
	
}
