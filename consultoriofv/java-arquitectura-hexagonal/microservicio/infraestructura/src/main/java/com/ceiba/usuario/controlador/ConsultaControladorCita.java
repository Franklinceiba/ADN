package com.ceiba.usuario.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.usuario.consulta.ManejadorListarCitas;
import com.ceiba.usuario.consulta.ManejadorListarFechaCitas;
import com.ceiba.usuario.modelo.dto.DtoCita;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/citas")
@Api(tags={"Controlador consulta citas"})
public class ConsultaControladorCita {
	
	private final ManejadorListarCitas manejadorListarCitas;
	private final ManejadorListarFechaCitas manejadorListarFechaCitas;

	
	
	public ConsultaControladorCita(ManejadorListarCitas manejadorListarCitas,
			ManejadorListarFechaCitas manejadorListarFechaCitas) {
		this.manejadorListarCitas = manejadorListarCitas;
		this.manejadorListarFechaCitas = manejadorListarFechaCitas;
	}

	@GetMapping
	@ApiOperation("Listar Citas")
	public List<DtoCita> listar() {
		return this.manejadorListarCitas.ejecutar();
	}
	
	@GetMapping(value = "/{fecha}")
	@ApiOperation("Listar Citas Por Fecha")
	public List<DtoCita> listarFecha(@PathVariable String fecha) {
		LocalDate fechaBusqueda = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return this.manejadorListarFechaCitas.ejecutar(fechaBusqueda);
	}

}
