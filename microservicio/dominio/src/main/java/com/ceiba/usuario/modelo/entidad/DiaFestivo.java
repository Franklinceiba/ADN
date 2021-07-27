package com.ceiba.usuario.modelo.entidad;

import java.util.ArrayList;
import java.util.Calendar;

public class DiaFestivo {
	private int ano;
	private int mesPascua;
	private int diaPascua;
	private ArrayList<String> festivos;

	public DiaFestivo(int ano) {
		this.ano = ano;
		this.festivos = new ArrayList<>();
		int a = ano % 19;
		int b = ano / 100;
		int c = ano % 100;
		int d = b / 4;
		int e = b % 4;
		int g = (8 * b + 13) / 25;
		int h = (19 * a + b - d - g + 15) % 30;
		int j = c / 4;
		int k = c % 4;
		int m = (a + 11 * h) / 319;
		int r = (2 * e + 2 * j - k - h + m + 32) % 7;
		this.mesPascua = (h - m + r + 90) / 25;
		this.diaPascua = (h - m + r + this.mesPascua + 19) % 32;
		this.mesPascua--;
		this.festivos.add("0:1"); // Primero de Enero
		this.festivos.add("4:1"); // Dia del trabajo 1 de mayo
		this.festivos.add("6:20"); // Independencia 20 de Julio
		this.festivos.add("7:7"); // Batalla de boyaca 7 de agosto
		this.festivos.add("11:8"); // Maria inmaculada 8 de diciembre
		this.festivos.add("11:25"); // Navidad 25 de diciembre
		this.calcularEmiliani(0, 6); // Reyes magos 6 de enero
		this.calcularEmiliani(2, 19); // San jose 19 de marzo
		this.calcularEmiliani(5, 29); // San pedro y san pablo 29 de junio
		this.calcularEmiliani(7, 15); // Asuncion 15 de agosto
		this.calcularEmiliani(9, 12); // Descubrimiento de america 12 de octubre
		this.calcularEmiliani(10, 1); // Todos los santos 1 de noviembre
		this.calcularEmiliani(10, 11); // Independencia de cartagena 11 de noviembre
		this.calcularOtroFestivo(-3, false); // jueves santos
		this.calcularOtroFestivo(-2, false); // viernes santo
		this.calcularOtroFestivo(40, true); // Asención del señor de pascua
		this.calcularOtroFestivo(60, true); // Corpus cristi
		this.calcularOtroFestivo(68, true); // Sagrado corazon
	}

	private void calcularEmiliani(int mes, int dia) {
		Calendar date = Calendar.getInstance();
		date.set(this.ano, mes, dia);
		int diaSemana = date.get(Calendar.DAY_OF_WEEK);
		switch (diaSemana) {
		case 1:
			date.add(Calendar.DATE, 1);
			break;
		case 3:
			date.add(Calendar.DATE, 6);
			break;
		case 4:
			date.add(Calendar.DATE, 5);
			break;
		case 5:
			date.add(Calendar.DATE, 4);
			break;
		case 6:
			date.add(Calendar.DATE, 3);
			break;
		case 7:
			date.add(Calendar.DATE, 2);
			break;
		default:
			break;
		}
		this.festivos.add(date.get(Calendar.MONTH) + ":" + date.get(Calendar.DATE));
	}

	private void calcularOtroFestivo(int dias, boolean emiliani) {
		Calendar fecha = Calendar.getInstance();
		fecha.set(this.ano, this.mesPascua, this.diaPascua);
		fecha.add(Calendar.DATE, dias);
		if (emiliani) {
			this.calcularEmiliani(fecha.get(Calendar.MONTH), fecha.get(Calendar.DATE));
		} else {
			this.festivos.add(fecha.get(Calendar.MONTH) + ":" + fecha.get(Calendar.DATE));
		}
	}

	public boolean esFestivo(int mes, int dia) {
		return this.festivos.contains(mes + ":" + dia);
	}

	public int getano() {
		return ano;
	}
}
