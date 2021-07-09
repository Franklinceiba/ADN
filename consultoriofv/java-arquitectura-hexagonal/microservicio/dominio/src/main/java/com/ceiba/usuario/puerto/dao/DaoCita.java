package com.ceiba.usuario.puerto.dao;

import java.time.LocalDate;
import java.util.List;

import com.ceiba.usuario.modelo.dto.DtoCita;

public interface DaoCita {

	/**
	 * Permite listar citas
	 * 
	 * @return las citas
	 */
	List<DtoCita> listar();
	
	/**
	 * Permite listar citas por fecha
	 * 
	 * @return las citas
	 */
	List<DtoCita> listarFecha(LocalDate fecha);
}
