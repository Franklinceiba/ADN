package com.ceiba.usuario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.usuario.servicio.ServicioEliminarPersona;

@Component
public class ManejadorEliminarPersona implements ManejadorComando<Long>{
	
	private final ServicioEliminarPersona servicioEliminarPersona;

	public ManejadorEliminarPersona(ServicioEliminarPersona servicioEliminarPersona) {
		this.servicioEliminarPersona = servicioEliminarPersona;
	}
	
	public void ejecutar(Long id) {
		this.servicioEliminarPersona.ejecutar(id);
	}

}
