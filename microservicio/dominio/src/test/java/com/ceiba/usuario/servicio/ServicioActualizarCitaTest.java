package com.ceiba.usuario.servicio;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.servicio.testdatabuilder.CitaTestDataBuilder;

public class ServicioActualizarCitaTest {

	@Test
	public void validarCitaExistenciaPreviaTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conId(1L).conFechaYHora(LocalDate.of(2021, 7, 6), "15:00:00").build();
		RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
		Mockito.when(repositorioCita.existePorId(Mockito.any(), Mockito.any(),Mockito.any())).thenReturn(true);
		ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
		// act - assert
		BasePrueba.assertThrows(() -> servicioActualizarCita.ejecutar(cita), ExcepcionDuplicidad.class,"No se puede registrar cita en este horario por que ya hay una cita registrada");
	}
	
	@Test
	public void validarCitaTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conId(1L).conFechaYHora(LocalDate.of(2021, 7, 6), "15:00:00").build();
		RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
		Mockito.when(repositorioCita.existe(Mockito.any(), Mockito.any())).thenReturn(false);
		ServicioActualizarCita servicioActualizarCita = new ServicioActualizarCita(repositorioCita);
		servicioActualizarCita.ejecutar(cita);
		Long idPersona = 1L;
		Long id = 1L;
		int fecha = 2021;
		// act - assert
		Assert.assertEquals(cita.getIdPersona(), idPersona);
		Assert.assertEquals(cita.getId(), id);
		Assert.assertEquals(cita.getDiaFestivo().getAno(), fecha);
		
	}
}
