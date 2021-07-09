package com.ceiba.usuario.servicio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;
import com.ceiba.usuario.servicio.testdatabuilder.PersonaTestDataBuilder;

public class ServicioActualizarPersonaTest {

	@Test
    public void validarPersonaExistenciaPreviaTest() {
        // arrange
		Persona persona = new PersonaTestDataBuilder().build();
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		Mockito.when(repositorioPersona.existe(Mockito.any(), Mockito.any())).thenReturn(true);
		ServicioActualizarPersona servicioActualizarPersona = new ServicioActualizarPersona(repositorioPersona);
		// act - assert
        BasePrueba.assertThrows(() -> servicioActualizarPersona.ejecutar(persona), ExcepcionDuplicidad.class,"No se puede registrar la persona por que existe un registro con este documento");
	}
	
	@Test
    public void validarPersonaTest() {
        // arrange
		Persona persona = new PersonaTestDataBuilder().build();
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		ServicioActualizarPersona servicioActualizarPersona = new ServicioActualizarPersona(repositorioPersona);
		servicioActualizarPersona.ejecutar(persona);
		String celular = "3177093283";
		// act - assert
		Assert.assertEquals(persona.getCelular(), celular);
	}
}
