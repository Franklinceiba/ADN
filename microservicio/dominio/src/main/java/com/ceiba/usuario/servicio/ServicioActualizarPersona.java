package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;

public class ServicioActualizarPersona {

	private static final String PERSONA_YA_REGISTRADA_EN_EL_SISTEMA = "No se puede registrar la persona por que existe un registro con este documento";

	private final RepositorioPersona repositorioPersona;

	public ServicioActualizarPersona(RepositorioPersona repositorioPersona) {
		this.repositorioPersona = repositorioPersona;
	}
	
	public void ejecutar(Persona persona) {
		validarExistenciaDocumento(persona);
		this.repositorioPersona.actualizar(persona);
	}

	private void validarExistenciaDocumento(Persona persona) {
		boolean existeRegistroDocumento = this.repositorioPersona.existe(persona.getTipoDocumento(), persona.getDocumento());
		if (existeRegistroDocumento) {
			throw new ExcepcionDuplicidad(PERSONA_YA_REGISTRADA_EN_EL_SISTEMA);
		}
	}
	
	
}
