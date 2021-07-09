package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;

public class ServicioCrearCita {
	
	private static final String CUPO_REGISTRADO_EN_EL_SISTEMA = "No se puede registrar cita en este horario por que ya hay una cita registrada";
	
	private final RepositorioCita repositorioCita;

	public ServicioCrearCita(RepositorioCita repositorioCita) {
		this.repositorioCita = repositorioCita;
	}
	
	public Long ejecutar(Cita cita) {
		validarExistenciaFechaHora(cita);
		return this.repositorioCita.crear(cita);
	}
	
	private void validarExistenciaFechaHora(Cita cita) {
		boolean existeRegistroFechaHora = this.repositorioCita.existe(cita.getFecha(), cita.getHora());
		if (existeRegistroFechaHora) {
			throw new ExcepcionDuplicidad(CUPO_REGISTRADO_EN_EL_SISTEMA);
		}
	}

}
