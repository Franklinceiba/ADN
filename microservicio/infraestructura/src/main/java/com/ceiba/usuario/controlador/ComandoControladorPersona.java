package com.ceiba.usuario.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.usuario.comando.ComandoPersona;
import com.ceiba.usuario.comando.manejador.ManejadorActualizarPersona;
import com.ceiba.usuario.comando.manejador.ManejadorCrearPersona;
import com.ceiba.usuario.comando.manejador.ManejadorEliminarPersona;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/personas")
@Api(tags = { "Controlador comando persona"})
public class ComandoControladorPersona {
	
	private final ManejadorCrearPersona manejadorCrearPersona;
	private final ManejadorEliminarPersona manejadorEliminarPersona;
	private final ManejadorActualizarPersona manejadorActualizarPersona;
	
	public ComandoControladorPersona(ManejadorCrearPersona manejadorCrearPersona,
			ManejadorEliminarPersona manejadorEliminarPersona, ManejadorActualizarPersona manejadorActualizarPersona) {
		this.manejadorCrearPersona = manejadorCrearPersona;
		this.manejadorEliminarPersona = manejadorEliminarPersona;
		this.manejadorActualizarPersona = manejadorActualizarPersona;
	}
	
	@PostMapping
	@ApiOperation("Crear Persona")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoPersona comandoPersona) {
		return manejadorCrearPersona.ejecutar(comandoPersona);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiOperation("Eliminar Persona")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarPersona.ejecutar(id);
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation("Actualizar Persona")
	public void actualizar(@RequestBody ComandoPersona comandoPersona, @PathVariable Long id) {
		comandoPersona.setId(id);
		manejadorActualizarPersona.ejecutar(comandoPersona);
	}

}
