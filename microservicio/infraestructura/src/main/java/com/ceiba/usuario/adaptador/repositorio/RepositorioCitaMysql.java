package com.ceiba.usuario.adaptador.repositorio;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.entidad.Cita;
import com.ceiba.usuario.puerto.repositorio.RepositorioCita;

@Repository
public class RepositorioCitaMysql implements RepositorioCita {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "cita", value = "crear")
	private static String sqlCrear;
	
	@SqlStatement(namespace = "cita", value = "actualizar")
	private static String sqlActualizar;
	
	@SqlStatement(namespace = "cita", value = "eliminar")
	private static String sqlEliminar;
	
	@SqlStatement(namespace = "cita", value = "existe")
	private static String sqlExiste;
	
	@SqlStatement(namespace = "cita", value = "existePorId")
	private static String sqlExistePorId;

	public RepositorioCitaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}
	
	@Override
	public Long crear(Cita cita) {
		return this.customNamedParameterJdbcTemplate.crear(cita, sqlCrear);
	}

	@Override
	public void actualizar(Cita cita) {
		this.customNamedParameterJdbcTemplate.actualizar(cita, sqlActualizar);
		
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, parameterSource);
	}

	@Override
	public boolean existe(LocalDate fecha, LocalTime hora) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("fecha", fecha);
		parameterSource.addValue("hora", hora);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,  parameterSource, Boolean.class);
	}
	
	@Override
	public boolean existePorId(LocalDate fecha, LocalTime hora, Long id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("fecha", fecha);
		parameterSource.addValue("hora", hora);
		parameterSource.addValue("id", id);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,  parameterSource, Boolean.class);
	}
	
	
}
