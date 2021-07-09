package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.entidad.Persona;

public interface RepositorioPersona {

	/**
	 * Permite crear una persona
	 * @param persona
	 * @return
	 */
	Long crear(Persona persona);
	
	/**
	 * Permite actualizar una persona
	 * @param persona
	 */
	void actualizar(Persona persona);
	
	/**
	 * Permite eliminar una persona
	 * @param id
	 */
	void eliminar(Long id);
	
	/**
	 * Permite validar si existe una persona por el documento
	 * @param documento
	 * @return
	 */
	boolean existe(String tipoDocumento, String documento);
}
