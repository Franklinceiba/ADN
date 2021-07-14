package com.ceiba.usuario.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.usuario.consulta.ManejadorListarDocumentoPersonas;
import com.ceiba.usuario.consulta.ManejadorListarIdPersonas;
import com.ceiba.usuario.consulta.ManejadorListarPersonas;
import com.ceiba.usuario.modelo.dto.DtoPersona;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/personas")
@Api(tags = {"Controlador consulta persona"})
public class ConsultaControladorPersona {
	
	private final ManejadorListarPersonas manejadorListarPersonas;
	private final ManejadorListarIdPersonas manejadorListarIdPersonas;
	private final ManejadorListarDocumentoPersonas manejadorListarDocumentoPersonas;

	public ConsultaControladorPersona(ManejadorListarPersonas manejadorListarPersonas,
			ManejadorListarIdPersonas manejadorListarIdPersonas,
			ManejadorListarDocumentoPersonas manejadorListarDocumentoPersonas) {
		this.manejadorListarPersonas = manejadorListarPersonas;
		this.manejadorListarIdPersonas = manejadorListarIdPersonas;
		this.manejadorListarDocumentoPersonas = manejadorListarDocumentoPersonas;
	}

	@GetMapping
	@ApiOperation("Listar Personas")
	public List<DtoPersona> listar(){
		return this.manejadorListarPersonas.ejecutar();
	}
	
	@GetMapping("/id/{id}")
	@ApiOperation("Listar Personas por id")
	public DtoPersona listar(@PathVariable Long id){
		return this.manejadorListarIdPersonas.ejecutar(id);
	}
	
	@GetMapping("/documento/{documento}")
	@ApiOperation("Listar Personas por documento")
	public List<DtoPersona> listar(@PathVariable String documento){
		return this.manejadorListarDocumentoPersonas.ejecutar(documento);
	}

}
