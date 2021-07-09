package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.comando.ComandoCita;
import com.ceiba.usuario.comando.fabrica.FabricaCita;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.servicio.ServicioCrearCita;

@Component
public class ManejadorCrearCita implements ManejadorComandoRespuesta<ComandoCita, ComandoRespuesta<Long>>{

	private final FabricaCita fabricaCita;
	private final ServicioCrearCita servicioCrearCita;
	
	public ManejadorCrearCita(FabricaCita fabricaCita, ServicioCrearCita servicioCrearCita) {
		this.fabricaCita = fabricaCita;
		this.servicioCrearCita = servicioCrearCita;
	}
	
	public ComandoRespuesta<Long> ejecutar(ComandoCita comandoCita) {
		Cita cita = this.fabricaCita.crear(comandoCita);
		return new ComandoRespuesta<>(this.servicioCrearCita.ejecutar(cita));
	}
}
