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
	
	/**
	 * Permite listar persona por id
	 * 
	 * @return las persona
	 */
	DtoPersona listarId(Long id);
	
	/**
	 * Permite listar persona por documento
	 * 
	 * @return las persona
	 */
	List<DtoPersona> listarDocumento(String documento);
}
