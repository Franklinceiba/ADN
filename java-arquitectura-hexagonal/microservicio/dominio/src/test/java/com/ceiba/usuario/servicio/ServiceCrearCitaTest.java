package com.ceiba.usuario.servicio;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.servicio.testdatabuilder.CitaTestDataBuilder;

public class ServiceCrearCitaTest {
	
	@Test
	public void validarFechaNoPermitidaTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), LocalTime.of(18, 0));
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m");
	}
	
	@Test
	public void validarCitaExistenciaPreviaTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), LocalTime.of(10, 0)).build();
		RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
		Mockito.when(repositorioCita.existe(Mockito.any(), Mockito.any())).thenReturn(true);
		ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita);
		// act - assert
		BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionDuplicidad.class, "No se puede registrar cita en este horario por que ya hay una cita registrada");
		
	}

}
