package com.ceiba.usuario.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.dto.DtoPersona;
import com.ceiba.usuario.puerto.dao.DaoPersona;

@Component
public class DaoPersonaMysql implements DaoPersona {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "persona", value = "listar")
	private static String sqlListar;
	
	@SqlStatement(namespace = "persona", value = "listarId")
	private static String sqlListarId;
	
	@SqlStatement(namespace = "persona", value = "listarDocumento")
	private static String sqlListarDocumento;

	public DaoPersonaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}
	
	@Override
	public List<DtoPersona> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoPersona());	
	}

	@Override
	public DtoPersona listarId(Long id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlListarId, parameterSource, new MapeoPersona());
	}
	
	@Override
	public List<DtoPersona> listarDocumento(String documento) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("documento", documento);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarDocumento, parameterSource, new MapeoPersona());
	}
	
}
