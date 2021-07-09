package com.ceiba.usuario.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.usuario.modelo.dto.DtoPersona;

public class MapeoPersona implements RowMapper<DtoPersona>, MapperResult {

	@Override
	public DtoPersona mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		Long id = resultSet.getLong("pers_id");
		String tipoDocumento = resultSet.getString("pers_tipo_documento");
		String documento = resultSet.getString("pers_documento");
		String nombre = resultSet.getString("pers_nombre");
		String apellido = resultSet.getString("pers_apellido");
		LocalDate fechaNacimiento = extraerLocalDate(resultSet, "pers_fecha_nacimiento");
		String celular = resultSet.getString("pers_celular");
		String email = resultSet.getString("pers_email");
		
		return new DtoPersona(id, tipoDocumento, documento, nombre, apellido, fechaNacimiento, celular, email);
	}
}
