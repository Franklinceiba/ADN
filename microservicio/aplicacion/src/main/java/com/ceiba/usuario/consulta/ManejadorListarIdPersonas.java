package com.ceiba.usuario.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoPersona;
import com.ceiba.usuario.puerto.dao.DaoPersona;

@Component
public class ManejadorListarIdPersonas {
	
	private final DaoPersona daoPersona;

	public ManejadorListarIdPersonas(DaoPersona daoPersona) {
		this.daoPersona = daoPersona;
	}
	
	public DtoPersona ejecutar(Long id) {
		return this.daoPersona.listarId(id);
	}

}
