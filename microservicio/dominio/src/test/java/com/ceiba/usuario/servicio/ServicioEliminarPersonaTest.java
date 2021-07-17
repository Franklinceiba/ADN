package com.ceiba.usuario.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionRelacionado;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;
import com.ceiba.usuario.servicio.testdatabuilder.PersonaTestDataBuilder;

public class ServicioEliminarPersonaTest {
	
	private static final String PERSONA_TIENE_REGISTRO_EN_UNA_CITA = "No se puede eliminar la persona por que existe un registro con una cita medica en el consultorio";

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
	
	@Test
    public void validarPersonaRelacionadaPreviaTest() {
        // arrange
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		Mockito.when(repositorioPersona.existePorCita(Mockito.any())).thenReturn(true);
		ServicioEliminarPersona servicioEliminarPersona = new ServicioEliminarPersona(repositorioPersona);
		// act - assert
        BasePrueba.assertThrows(() -> servicioEliminarPersona.ejecutar(1l), ExcepcionRelacionado.class,PERSONA_TIENE_REGISTRO_EN_UNA_CITA);
	}
}
