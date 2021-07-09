package com.ceiba.usuario.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;
import com.ceiba.usuario.servicio.testdatabuilder.PersonaTestDataBuilder;

public class ServicioCrearPersonaTest {
	
	@Test
    public void validarPersonaExistenciaPreviaTest() {
        // arrange
		Persona persona = new PersonaTestDataBuilder().build();
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		Mockito.when(repositorioPersona.existe(Mockito.any(), Mockito.any())).thenReturn(true);
		ServicioCrearPersona servicioCrearPersona = new ServicioCrearPersona(repositorioPersona);
		// act - assert
        BasePrueba.assertThrows(() -> servicioCrearPersona.ejecutar(persona), ExcepcionDuplicidad.class,"No se puede registrar la persona por que existe un registro con este documento");
	}
	
}
