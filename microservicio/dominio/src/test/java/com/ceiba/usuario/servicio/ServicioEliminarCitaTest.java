package com.ceiba.usuario.servicio;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.servicio.testdatabuilder.CitaTestDataBuilder;

public class ServicioEliminarCitaTest {

	@Test
	public void validarCitaEliminarTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conId(1L).conFechaYHora(LocalDate.of(2021, 7, 6), LocalTime.of(15, 0)).build();
		RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
		ServicioEliminarCita servicioEliminarCita = new ServicioEliminarCita(repositorioCita);
		Long id = 1L;
		servicioEliminarCita.ejecutar(id);
		// act - assert
		Assert.assertEquals(cita.getId(), id);
	}
}
