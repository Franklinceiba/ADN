package com.ceiba.usuario.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.comando.ComandoPersona;
import com.ceiba.usuario.modelo.entidad.Persona;

@Component
public class FabricaPersona {

	public Persona crear(ComandoPersona comandoPersona) {
		return new Persona(
				comandoPersona.getId(), 
				comandoPersona.getTipoDocumento(), 
				comandoPersona.getDocumento(), 
				comandoPersona.getNombre(), 
				comandoPersona.getApellido(), 
				comandoPersona.getFechaNacimiento(), 
				comandoPersona.getCelular(), 
				comandoPersona.getEmail());
	}
}
