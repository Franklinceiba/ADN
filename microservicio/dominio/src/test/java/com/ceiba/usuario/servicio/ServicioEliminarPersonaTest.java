package com.ceiba.usuario.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;
import com.ceiba.usuario.servicio.testdatabuilder.PersonaTestDataBuilder;

public class ServicioEliminarPersonaTest {

	@Test
	public void validarPersonaEliminarTest() {
		// arrange
		String documento = "324342423423";
		Persona persona = new PersonaTestDataBuilder().conId(1L).conDocumento(documento).build();
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		ServicioEliminarPersona servicioEliminarPersona = new ServicioEliminarPersona(repositorioPersona);
		Long id = 1L;
		servicioEliminarPersona.ejecutar(id);
		// act - assert
		Assert.assertEquals(persona.getId(), id);
	}
}
