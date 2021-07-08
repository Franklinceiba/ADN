package com.ceiba.configuracion;

import com.ceiba.usuario.puerto.repositorio.RepositorioCita;
import com.ceiba.usuario.puerto.repositorio.RepositorioPersona;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarCita;
import com.ceiba.usuario.servicio.ServicioActualizarPersona;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearCita;
import com.ceiba.usuario.servicio.ServicioCrearPersona;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarCita;
import com.ceiba.usuario.servicio.ServicioEliminarPersona;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }
	
    @Bean
    public ServicioCrearCita servicioCrearCita(RepositorioCita repositorioCita) {
    	return new ServicioCrearCita(repositorioCita);
    }
    
    @Bean
    public ServicioEliminarCita servicioEliminarCita(RepositorioCita repositorioCita) {
    	return new ServicioEliminarCita(repositorioCita);
    }
    
    @Bean
    public ServicioActualizarCita servicioActualizarCita(RepositorioCita repositorioCita) {
    	return new ServicioActualizarCita(repositorioCita);

    }
    
    @Bean
    public ServicioCrearPersona servicioCrearPersona(RepositorioPersona repositorioPersona) {
    	return new ServicioCrearPersona(repositorioPersona);
    }
    
    @Bean
    public ServicioEliminarPersona servicioEliminarPersona(RepositorioPersona repositorioPersona) {
    	return new ServicioEliminarPersona(repositorioPersona);
    }
    
    @Bean
    public ServicioActualizarPersona servicioActualizarPersona(RepositorioPersona repositorioPersona) {
    	return new ServicioActualizarPersona(repositorioPersona);

    }

}
