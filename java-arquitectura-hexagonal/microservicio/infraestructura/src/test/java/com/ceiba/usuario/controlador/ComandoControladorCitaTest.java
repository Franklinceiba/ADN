package com.ceiba.usuario.controlador;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.comando.ComandoCita;
import com.ceiba.usuario.servicio.testdatabuilder.ComandoCitaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorCita.class)
public class ComandoControladorCitaTest {

	@Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void crear() throws Exception{
        // arrange
    	ComandoCita cita = new ComandoCitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 6), LocalTime.of(10, 0)).build();
    	
    	// act - assert
    	mocMvc.perform(post("/citas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cita)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }
    
    @Test
    public void actualizar() throws Exception{
        // arrange
    	Long id = 2L;
    	ComandoCita cita = new ComandoCitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), LocalTime.of(11, 0)).build();
    	
    	// act - assert
    	  mocMvc.perform(put("/citas/{id}",id)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(cita)))
                  .andExpect(status().isOk());
    }
    
    @Test
    public void actualizarFallo() throws Exception{
        // arrange
    	Long id = 2L;
    	ComandoCita cita = new ComandoCitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), LocalTime.of(10, 30)).build();
    	
    	// act - assert
    	  mocMvc.perform(put("/citas/{id}",id)
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(objectMapper.writeValueAsString(cita)))
                  .andExpect(status().isBadRequest());
    }
    
    @Test
    public void listarFecha() throws Exception{
        // arrange
    	String fecha = "2021-07-05";
    	ComandoCita cita = new ComandoCitaTestDataBuilder().conFechaYHora(LocalDate.of(2021, 7, 5), LocalTime.of(10, 30)).conId(2l).build();
    	// act - assert
    	  mocMvc.perform(get("/citas/{fecha}",fecha)
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$[0].fecha", is(fecha)));
    }
    
    @Test
    public void eliminar() throws Exception {
        // arrange
        Long id = 2L;
        
        // act - assert
        mocMvc.perform(delete("/citas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
