package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.usuario.comando.ComandoPersona;
import com.ceiba.usuario.comando.fabrica.FabricaPersona;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.servicio.ServicioCrearPersona;

@Component
public class ManejadorCrearPersona implements ManejadorComandoRespuesta<ComandoPersona, ComandoRespuesta<Long>> {

	private final FabricaPersona fabricaPersona;
	private final ServicioCrearPersona servicioCrearPersona;
	public ManejadorCrearPersona(FabricaPersona fabricaPersona, ServicioCrearPersona servicioCrearPersona) {
		this.fabricaPersona = fabricaPersona;
		this.servicioCrearPersona = servicioCrearPersona;
	}
	
	public ComandoRespuesta<Long> ejecutar(ComandoPersona comandoPersona) {
		Persona persona = this.fabricaPersona.crear(comandoPersona);
		return new ComandoRespuesta<>(this.servicioCrearPersona.ejecutar(persona));
	}
}
