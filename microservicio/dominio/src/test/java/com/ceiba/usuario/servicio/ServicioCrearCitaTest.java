package com.ceiba.usuario.servicio;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.servicio.testdatabuilder.CitaTestDataBuilder;

public class ServicioCrearCitaTest {
	
	private static final String DESCRIPCION = "problemas en los ojos";
	private static final LocalDate FECHA = LocalDate.of(2021, 7, 6);
	private static final String HORA = "09:30:00";
	private static final Long IDPERSONA = 1l;
	private static final String CAMPO_DESCRIPCION_ES_OBLIGATORIO = "El campo descripcion es obligatorio no puede ir vacio";
	private static final String CAMPO_FECHA_ES_OBLIGATORIO = "El campo fecha es obligatorio no puede ir vacio";
	private static final String CAMPO_HORA_ES_OBLIGATORIO = "El campo hora es obligatorio no puede ir vacio";
	private static final String CAMPO_ID_PERSONA_ES_OBLIGATORIO = "El campo id de la persona es obligatorio no puede ir vacio";
	
	
	@Test
	public void validarFechaNoPermitidaLunesAViernesNocturnoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "17:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m");
	}
	
	@Test
	public void validarFechaNoPermitidaLunesAViernesHorarioAlmuerzoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "12:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m");
	}
	
	@Test
	public void validarFechaNoPermitidaLunesAViernesHorarioTardeTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "13:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m");
	}
	
	@Test
	public void validarFechaNoPermitidaLunesAViernesHorarioMananaTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "07:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m");
	}
	
	@Test
	public void validarFechaNoPermitidaSabadoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 3), "10:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "No se permite sacar cita los sabados y domingos");
	}
	
	@Test
	public void validarFechaNoPermitidaDomingoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 4), "10:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "No se permite sacar cita los sabados y domingos");
	}
	
	@Test
	public void validarFechaNoPermitidaDomingoFestivoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2019, 12, 8), "13:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita los festivos en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m");
	}
	
	@Test
	public void validarFechaNoPermitidaFestivoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), "18:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita los festivos en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m");
	}
	
	@Test
	public void validarFechaNoPermitidaFestivoHorarioPermitidoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), "07:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita los festivos en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m");
	}
	
	@Test
	public void validarFechaNoPermitidaFestivoHorarioPermitidoAlmuerzoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), "12:00:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "Solo se permite sacar cita los festivos en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m");
	}
	
	@Test
	public void validarHorarioPermitidoTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), "11:50:00");
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorInvalido.class, "No se permite estos minutos para solicitar cita, solo se permite a las :00 o :30");
	}
	
	@Test
	public void validarRequeridoDescripcionTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder(null, FECHA, HORA, IDPERSONA);
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_DESCRIPCION_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoFechaTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder(DESCRIPCION, null, HORA, IDPERSONA);
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_FECHA_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoHoraTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder(DESCRIPCION, FECHA, null, IDPERSONA);
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_HORA_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarRequeridoIdPersonaTest() {
		// arrange
		CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder(DESCRIPCION, FECHA, HORA, null);
		// act - assert
		BasePrueba.assertThrows(() -> citaTestDataBuilder.build(), ExcepcionValorObligatorio.class, CAMPO_ID_PERSONA_ES_OBLIGATORIO);
	}
	
	@Test
	public void validarCitaValorLunesAViernesTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "08:00:00").build();
		int valorLunesAViernes = 30000;
		//assert
		Assert.assertEquals(cita.getValor(), valorLunesAViernes);
	}
	
	@Test
	public void validarCitaValorFestivoTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), "11:30:00").build();
		int valorFestivo = 60000;
		//assert
		Assert.assertEquals(cita.getValor(), valorFestivo);
	}
	
	@Test
	public void validarCitaExistenciaPreviaTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "10:00:00").build();
		RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
		Mockito.when(repositorioCita.existe(Mockito.any(), Mockito.any())).thenReturn(true);
		ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita);
		// act - assert
		BasePrueba.assertThrows(() -> servicioCrearCita.ejecutar(cita), ExcepcionDuplicidad.class, "No se puede registrar cita en este horario por que ya hay una cita registrada");
		
	}
	
	@Test
	public void validarCitaTest() {
		// arrange
		Cita cita = new CitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), "11:30:00").build();
		RepositorioCita repositorioCita = Mockito.mock(RepositorioCita.class);
		ServicioCrearCita servicioCrearCita = new ServicioCrearCita(repositorioCita);
		servicioCrearCita.ejecutar(cita);
		String descripcion = "Presento sintomas de tos";
		// act - assert
		Assert.assertEquals(cita.getDescripcion(), descripcion);
	}

}
