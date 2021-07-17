package com.ceiba.usuario.servicio;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;
import com.ceiba.usuario.servicio.testdatabuilder.PersonaTestDataBuilder;

public class ServicioCrearPersonaTest {
	
	private static final String TIPO_DOCUMENTO = "C.C";
	private static final String DOCUMENTO = "34342343242332";
	private static final String NOMBRE = "juan felipe";
	private static final String APELLIDO = "mantilla ruiz";
	private static final LocalDate FECHA = LocalDate.now();
	private static final String CELULAR = "3156766789";
	private static final String EMAIL = "felipe@gmail.com";
	private static final String CAMPO_TIPO_DOCUMENTO_ES_OBLIGATORIO = "El campo tipo de documento es obligatorio no puede ir vacio";
	private static final String CAMPO_DOCUMENTO_ES_OBLIGATORIO = "El campo documento es obligatorio no puede ir vacio";
	private static final String CAMPO_NOMBRE_ES_OBLIGATORIO = "El campo nombre es obligatorio no puede ir vacio";
	private static final String CAMPO_APELLIDO_ES_OBLIGATORIO = "El campo apellido es obligatorio no puede ir vacio";
	private static final String CAMPO_FECHA_NACIMIENTO_ES_OBLIGATORIO = "El campo fecha de nacimiento es obligatorio no puede ir vacio";
	private static final String CAMPO_CELULAR_ES_OBLIGATORIO = "El campo celular es obligatorio no puede ir vacio";
	private static final String CAMPO_EMAIL_ES_OBLIGATORIO = "El campo email es obligatorio no puede ir vacio";
	
	@Test
	public void validarRequeridoTipoDocumentoTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(null, DOCUMENTO, NOMBRE, APELLIDO, FECHA, CELULAR, EMAIL);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_TIPO_DOCUMENTO_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoDocumentoTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(TIPO_DOCUMENTO, null, NOMBRE, APELLIDO, FECHA, CELULAR, EMAIL);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_DOCUMENTO_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoNombreTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(TIPO_DOCUMENTO, DOCUMENTO, null, APELLIDO, FECHA, CELULAR, EMAIL);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_NOMBRE_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoApellidoTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(TIPO_DOCUMENTO, DOCUMENTO, NOMBRE, null, FECHA, CELULAR, EMAIL);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_APELLIDO_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoFechaTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(TIPO_DOCUMENTO, DOCUMENTO, NOMBRE, APELLIDO, null, CELULAR, EMAIL);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_FECHA_NACIMIENTO_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoCelularTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(TIPO_DOCUMENTO, DOCUMENTO, NOMBRE, APELLIDO, FECHA, null, EMAIL);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_CELULAR_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoEmailTest() {
		// arrange
		PersonaTestDataBuilder PersonaTestDataBuilder = new PersonaTestDataBuilder(TIPO_DOCUMENTO, DOCUMENTO, NOMBRE, APELLIDO, FECHA, CELULAR, null);
		// act - assert
		BasePrueba.assertThrows(() -> PersonaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_EMAIL_ES_OBLIGATORIO);
	}
	
	@Test
    public void validarPersonaExistenciaPreviaTest() {
        // arrange
		String documento = "324342423423";
		Persona persona = new PersonaTestDataBuilder().conDocumento(documento).build();
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		Mockito.when(repositorioPersona.existe(Mockito.any(), Mockito.any())).thenReturn(true);
		ServicioCrearPersona servicioCrearPersona = new ServicioCrearPersona(repositorioPersona);
		// act - assert
        BasePrueba.assertThrows(() -> servicioCrearPersona.ejecutar(persona), ExcepcionDuplicidad.class,"No se puede registrar la persona por que existe un registro con este documento");
	}
	
	@Test
    public void validarPersonaTest() {
        // arrange
		String documento = "324342423423";
		Persona persona = new PersonaTestDataBuilder().conId(1L).conDocumento(documento).build();
		RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
		Mockito.when(repositorioPersona.existe(Mockito.any(), Mockito.any())).thenReturn(false);
		ServicioCrearPersona servicioCrearPersona = new ServicioCrearPersona(repositorioPersona);
		String nombre = "luis";
		String apellido = "pineda";
		LocalDate fechaNacimiento = LocalDate.now();
		String email = "franklin@gmail.com";
		Long id = 1L;
		servicioCrearPersona.ejecutar(persona);
		// act - assert
		Assert.assertEquals(persona.getNombre(), nombre);
		Assert.assertEquals(persona.getApellido(), apellido);
		Assert.assertEquals(persona.getFechaNacimiento(), fechaNacimiento);
		Assert.assertEquals(persona.getEmail(), email);
		Assert.assertEquals(persona.getId(), id);
	}
	
}
