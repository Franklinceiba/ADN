package com.ceiba.usuario.controlador;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ApplicationMock;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorCita.class)
public class ConsultaControladorCitaTest {
	
	@Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
    	// arrange

        // act - assert
        mocMvc.perform(get("/citas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].descripcion", is("revicion de examenes")));
    }
    
    @Test
    public void listarFecha() throws Exception{
        // arrange
    	String fecha = "2021-07-05";
    	
    	// act - assert
    	mocMvc.perform(get("/citas/{fecha}",fecha)
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$", hasSize(1)))
                  .andExpect(jsonPath("$[0].fecha", is(fecha)));
    }

}
