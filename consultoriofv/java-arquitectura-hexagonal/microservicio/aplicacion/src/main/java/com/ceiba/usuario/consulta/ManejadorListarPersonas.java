package com.ceiba.usuario.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoPersona;
import com.ceiba.usuario.puerto.dao.DaoPersona;

@Component
public class ManejadorListarPersonas {

	private final DaoPersona daoPersona;

	public ManejadorListarPersonas(DaoPersona daoPersona) {
		this.daoPersona = daoPersona;
	}
	
	public List<DtoPersona> ejecutar() { return this.daoPersona.listar(); }
}
