package com.ceiba.usuario.modelo.entidad;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Cita {
	
	private static final String SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_LUNES_A_VIERNES = "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m";
	private static final String SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_FESTIVOS = "Solo se permite sacar cita los festivos en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m";
	private static final String NO_SE_PERMITE_LOS_SABADOS_Y_DOMINGOS = "No se permite sacar cita los sabados y domingos";
	
	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private int valor;
	private Long idPersona;
	private HolidayUtil diaFestivo ;
	
	public Cita(Long id, String descripcion, LocalDate fecha, LocalTime hora, Long idPersona) {
		this.diaFestivo = new HolidayUtil(fecha.getYear());
		this.validarFechaNoPermitida(fecha, NO_SE_PERMITE_LOS_SABADOS_Y_DOMINGOS);
		this.validarHorarioPermitidoLunesViernes(fecha, hora, SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_LUNES_A_VIERNES);
		this.validarHorarioPermitidoFestivo(fecha, hora, SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_FESTIVOS);
		this.calcularValor(fecha);
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = hora;
		this.idPersona = idPersona;
	}
	
	
	private void validarHorarioPermitidoLunesViernes(LocalDate fecha, LocalTime hora, String mensaje) {
		int mes = fecha.getMonthValue() - 1;
		boolean validarFestivo = this.diaFestivo.isHoliday(mes, fecha.getDayOfMonth());
		int horaDia = hora.getHour();
		if (!validarFestivo && ((horaDia < 8 || horaDia > 16)||(horaDia >= 12 && horaDia < 12))) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	private void validarHorarioPermitidoFestivo(LocalDate fecha, LocalTime hora, String mensaje) {
		int mes = fecha.getMonthValue() - 1;
		boolean validarFestivo = this.diaFestivo.isHoliday(mes, fecha.getDayOfMonth());
		int horaDia = hora.getHour();
		if (validarFestivo && (horaDia < 8 || horaDia > 12)) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	private void validarFechaNoPermitida(LocalDate fecha, String mensaje) {
		int numeroDia = fecha.getDayOfWeek().getValue();
		if (numeroDia == 6 || numeroDia == 7) {
			 throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	private void calcularValor(LocalDate fecha) {
		int mes = fecha.getMonthValue() - 1;
		boolean validarFestivo = this.diaFestivo.isHoliday(mes, fecha.getDayOfMonth());
		if (validarFestivo) {
			this.valor = 60000;
		} else {
			this.valor = 30000;
		}
	}
}
