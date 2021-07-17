package com.ceiba.usuario.modelo.entidad;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;

import lombok.Getter;

@Getter
public class Cita {
	
	private static final String CAMPO_DESCRIPCION_ES_OBLIGATORIO = "El campo descripcion es obligatorio no puede ir vacio";
	private static final String CAMPO_FECHA_ES_OBLIGATORIO = "El campo fecha es obligatorio no puede ir vacio";
	private static final String CAMPO_HORA_ES_OBLIGATORIO = "El campo hora es obligatorio no puede ir vacio";
	private static final String CAMPO_ID_PERSONA_ES_OBLIGATORIO = "El campo id de la persona es obligatorio no puede ir vacio";
	private static final String SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_LUNES_A_VIERNES = "Solo se permite sacar cita de lunes a viernes en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m y de 2:00 p.m a 3:30 p.m";
	private static final String SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_FESTIVOS = "Solo se permite sacar cita los festivos en el siguiente horario extablecido de 8:00 a.m a 11:30 a.m";
	private static final String NO_SE_PERMITE_LOS_SABADOS_Y_DOMINGOS = "No se permite sacar cita los sabados y domingos";
	private static final String NO_SE_PERMITE_MINUTOS = "No se permite estos minutos para solicitar cita, solo se permite a las :00 o :30";
	private static final int NUMERO_SABADO = 6;
	private static final int NUMERO_DOMINGO = 7;
	private static final int VALOR_LUNES_A_VIERNES = 30000;
	private static final int VALOR_FESTIVOS = 60000;
	private static final int DESCONTAR_MES = 1;
	private static final int HORARIO_MANANA = 8;
	private static final int HORARIO_MEDIO_DIA = 12;
	private static final int HORARIO_TARDE = 14;
	private static final int HORARIO_NOCHE = 16;
	private static final int MINUTOS_CERO = 0;
	private static final int MINUTOS_TREINTA = 30;
	
	private Long id;
	private String descripcion;
	private LocalDate fecha;
	private LocalTime hora;
	private int valor;
	private Long idPersona;
	private HolidayUtil diaFestivo ;
	
	public Cita(Long id, String descripcion, LocalDate fecha, String hora, Long idPersona) {
		this.validarVacioDescripcion(descripcion);
		this.validarVacioFecha(fecha);
		this.validarVacioHora(hora);
		this.validarVacioIdPersona(idPersona);
		this.diaFestivo = new HolidayUtil(fecha.getYear());
		this.validarHorarioPermitidoLunesViernes(fecha, LocalTime.parse(hora), SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_LUNES_A_VIERNES);
		this.validarHorarioPermitidoFestivo(fecha, LocalTime.parse(hora), SOLO_SE_PERMITE_EL_SIGUIENTE_HORARIO_FESTIVOS);
		this.validarFechaNoPermitida(fecha, NO_SE_PERMITE_LOS_SABADOS_Y_DOMINGOS);
		this.validarMinutosPermitidos(LocalTime.parse(hora),NO_SE_PERMITE_MINUTOS);
		this.calcularValor(fecha);
		this.id = id;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.hora = LocalTime.parse(hora);
		this.idPersona = idPersona;
		
	}
	
	
	private void validarHorarioPermitidoLunesViernes(LocalDate fecha, LocalTime hora, String mensaje) {
		int mes = fecha.getMonthValue() - DESCONTAR_MES;
		boolean validarFestivo = this.diaFestivo.isHoliday(mes, fecha.getDayOfMonth());
		int horaDia = hora.getHour();
		if (!validarFestivo && fueraHorarioOficina(horaDia)) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	private void validarVacioDescripcion(String descripcion) {
		if (descripcion == null) {
			throw new ExcepcionValorObligatorio(CAMPO_DESCRIPCION_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioFecha(LocalDate fecha) {
		if (fecha == null) {
			throw new ExcepcionValorObligatorio(CAMPO_FECHA_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioHora(String hora) {
		if (hora == null) {
			throw new ExcepcionValorObligatorio(CAMPO_HORA_ES_OBLIGATORIO);
		}
	}
	
	private void validarVacioIdPersona(Long idPersona) {
		if (idPersona == null) {
			throw new ExcepcionValorObligatorio(CAMPO_ID_PERSONA_ES_OBLIGATORIO);
		}
	}
	
	private boolean fueraHorarioOficina(int horaDia) {
		boolean validar = false;
		if (horaDia < HORARIO_MANANA || horaDia > HORARIO_NOCHE ||(horaDia >= HORARIO_MEDIO_DIA && horaDia < HORARIO_TARDE)) {
			validar = true;
		}
		return validar;
	}
	
	private void validarHorarioPermitidoFestivo(LocalDate fecha, LocalTime hora, String mensaje) {
		int mes = fecha.getMonthValue() - DESCONTAR_MES;
		boolean validarFestivo = this.diaFestivo.isHoliday(mes, fecha.getDayOfMonth());
		int horaDia = hora.getHour();
		if (validarFestivo && (horaDia < HORARIO_MANANA || horaDia >= HORARIO_MEDIO_DIA)) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	private void validarFechaNoPermitida(LocalDate fecha, String mensaje) {
		int numeroDia = fecha.getDayOfWeek().getValue();
		if (numeroDia == NUMERO_SABADO || numeroDia == NUMERO_DOMINGO) {
			 throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	private void calcularValor(LocalDate fecha) {
		int mes = fecha.getMonthValue() - DESCONTAR_MES;
		boolean validarFestivo = this.diaFestivo.isHoliday(mes, fecha.getDayOfMonth());
		if (validarFestivo) {
			this.valor = VALOR_FESTIVOS;
		} else {
			this.valor = VALOR_LUNES_A_VIERNES;
		}
	}
	
	private void validarMinutosPermitidos(LocalTime hora, String mensaje) {
		int horaDia = hora.getMinute();
		if(horaDia != MINUTOS_CERO && horaDia != MINUTOS_TREINTA) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
}
