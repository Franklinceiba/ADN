package com.ceiba.usuario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoCita;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {

	@Override
	public DtoCita mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Long id = rs.getLong("cita_id");
		String descripcion = rs.getString("cita_descripcion");
		LocalDate fecha = extraerLocalDate(rs, "cita_fecha");
		LocalTime hora = extraerLocalTime(rs, "cita_hora");
		int valor = rs.getInt("cita_valor");
		Long idPersona = rs.getLong("pers_id");
		
		return new DtoCita(id, descripcion, fecha, hora, valor, idPersona);
	}
}
