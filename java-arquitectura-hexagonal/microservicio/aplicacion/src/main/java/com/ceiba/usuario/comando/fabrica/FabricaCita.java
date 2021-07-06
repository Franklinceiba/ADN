package com.ceiba.usuario.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.comando.ComandoCita;
import com.ceiba.usuario.modelo.entidad.Cita;

@Component
public class FabricaCita {
	
	public Cita crear(ComandoCita comandoCita) {
		return new Cita(
				comandoCita.getId(),
				comandoCita.getDescripcion(),
				comandoCita.getFecha(),
				comandoCita.getHora(),
				comandoCita.getPersona()
				);
	}
}
