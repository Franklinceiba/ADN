package com.ceiba.usuario.servicio;

import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;

public class ServicioEliminarPersona {
	
	private final RepositorioPersona repositorioPersona;

	public ServicioEliminarPersona(RepositorioPersona repositorioPersona) {
		this.repositorioPersona = repositorioPersona;
	}
	
	public void ejecutar(Long id) {
		this.repositorioPersona.eliminar(id);
	}
	
}
