package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;

public class ServicioActualizarCita {

	private static final String CUPO_REGISTRADO_EN_EL_SISTEMA = "No se puede registrar cita en este horario por que ya hay una cita registrada";
	
	private final RepositorioCita repositorioCita;

	public ServicioActualizarCita(RepositorioCita repositorioCita) {
		this.repositorioCita = repositorioCita;
	}
	
	public void ejecutar(Cita cita) {
		validarExistenciaFechaHora(cita);
		this.repositorioCita.actualizar(cita);
	}
	
	private void validarExistenciaFechaHora(Cita cita) {
		boolean existeRegistroFechaHora = this.repositorioCita.existePorId(cita.getFecha(), cita.getHora(), cita.getId());
		if (existeRegistroFechaHora) {
			throw new ExcepcionDuplicidad(CUPO_REGISTRADO_EN_EL_SISTEMA);
		}
	}
	

}
