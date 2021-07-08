package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.comando.ComandoPersona;
import com.ceiba.usuario.comando.fabrica.FabricaPersona;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.servicio.ServicioActualizarPersona;

@Component
public class ManejadorActualizarPersona implements ManejadorComando<ComandoPersona>{

	private final FabricaPersona fabricaPersona;
	private final ServicioActualizarPersona servicioActualizarPersona;
	
	public ManejadorActualizarPersona(FabricaPersona fabricaPersona,
			ServicioActualizarPersona servicioActualizarPersona) {
		this.fabricaPersona = fabricaPersona;
		this.servicioActualizarPersona = servicioActualizarPersona;
	}
	
	public void ejecutar(ComandoPersona comandoPersona) {
		Persona persona = this.fabricaPersona.crear(comandoPersona);
		this.servicioActualizarPersona.ejecutar(persona);
	}
	
	
}
