package com.ceiba.usuario.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.modelo.entidad.Persona;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;

@Repository
public class RepositorioPersonaMysql implements RepositorioPersona {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "persona", value = "crear")
	private static String sqlCrear;
	
	@SqlStatement(namespace = "persona", value = "actualizar")
	private static String sqlActualizar;
	
	@SqlStatement(namespace = "persona", value = "eliminar")
	private static String sqlEliminar;
	
	@SqlStatement(namespace = "persona", value = "existe")
	private static String sqlExiste;
	
	@SqlStatement(namespace = "persona", value = "existePorActualizacion")
	private static String sqlExistePorActualizacion;

	public RepositorioPersonaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Persona persona) {
		return this.customNamedParameterJdbcTemplate.crear(persona, sqlCrear);
	}

	@Override
	public void actualizar(Persona persona) {
		this.customNamedParameterJdbcTemplate.actualizar(persona, sqlActualizar);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, parameterSource);
	}

	@Override
	public boolean existe(String tipoDocumento,String documento) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("documento", documento);
		parameterSource.addValue("tipoDocumento", tipoDocumento);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,  parameterSource, Boolean.class);
	}
	
	@Override
	public boolean existePorActualizar(String tipoDocumento,String documento, Long id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("documento", documento);
		parameterSource.addValue("tipoDocumento", tipoDocumento);
		parameterSource.addValue("id", id);
		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorActualizacion,  parameterSource, Boolean.class);
	}
	
	
}
