package com.ceiba.usuario.puerto.dao;

import java.util.List;

import com.ceiba.usuario.modelo.dto.DtoPersona;

public interface DaoPersona {

	/**
	 * Permite listar personas
	 * 
	 * @return las personas
	 */
	List<DtoPersona> listar();
}
