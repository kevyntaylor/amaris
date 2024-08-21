package com.amaris.prueba;

import com.amaris.prueba.controllers.AlienController;
import com.amaris.prueba.services.DNAAnalyzer;
import com.amaris.prueba.models.DNARequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AlienController.class)
public class AlienControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DNAAnalyzer dnaAnalyzer;

    @Test
    public void testIsAlienTrue() throws Exception {
        when(dnaAnalyzer.isAlien(any(String[].class))).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/alien")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dna\": [\"HTDCDH\", \"CHDTDC\", \"TTHTDT\", \"AGHHDD\", \"CCCCTH\", \"TCHCTD\"]}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testIsAlienFalse() throws Exception {
        when(dnaAnalyzer.isAlien(any(String[].class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/alien")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"dna\": [\"HTDCDH\", \"CHDTDC\", \"TTHTDT\", \"AGHHDD\", \"CCCCTH\", \"TCHCTD\"]}"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
