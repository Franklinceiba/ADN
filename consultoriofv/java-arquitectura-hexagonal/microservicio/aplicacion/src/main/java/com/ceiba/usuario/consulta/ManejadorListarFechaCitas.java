package com.ceiba.usuario.consulta;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoCita;
import com.ceiba.usuario.puerto.dao.DaoCita;

@Component
public class ManejadorListarFechaCitas {

	private final DaoCita daoCita;
	
	public ManejadorListarFechaCitas(DaoCita daoCita) {
		this.daoCita = daoCita;
	}
	
	public List<DtoCita> ejecutar(LocalDate fecha) { return this.daoCita.listarFecha(fecha); }
}
