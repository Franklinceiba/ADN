package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.comando.ComandoCita;
import com.ceiba.usuario.comando.fabrica.FabricaCita;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.servicio.ServicioActualizarCita;

@Component
public class ManejadorActualizarCita implements ManejadorComando<ComandoCita>{

	private final FabricaCita fabricaCita;
	private final ServicioActualizarCita servicioActualizarCita;
	public ManejadorActualizarCita(FabricaCita fabricaCita, ServicioActualizarCita servicioActualizarCita) {
		this.fabricaCita = fabricaCita;
		this.servicioActualizarCita = servicioActualizarCita;
	}
	
	public void ejecutar(ComandoCita comandoCita) {
		Cita cita = this.fabricaCita.crear(comandoCita);
		this.servicioActualizarCita.ejecutar(cita);
	}
}
