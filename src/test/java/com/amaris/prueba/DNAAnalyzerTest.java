package com.amaris.prueba;
import com.amaris.prueba.repositorys.DNARecordRepository;
import com.amaris.prueba.models.DNARecord;
import com.amaris.prueba.services.DNAAnalyzer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DNAAnalyzerTest {

    @Autowired
    private DNAAnalyzer dnaAnalyzer;

    @MockBean
    private DNARecordRepository dnaRecordRepository;

    @Test
    public void testIsAlienFalse() {
        // Configura el comportamiento del mock
        when(dnaRecordRepository.findByDnaSequence(anyString())).thenReturn(Optional.of(new DNARecord()));

        // Datos de prueba
        String[] dnaSample = {"HTDCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CCCCTH", "TCHCTD"};

        // Llama al método de prueba
        boolean result = dnaAnalyzer.isAlien(dnaSample);

        // Verifica los resultados
        assertFalse(result);
    }

    @Test
    public void testIsAlienTrue() {
        // Configura el comportamiento del mock
        when(dnaRecordRepository.findByDnaSequence(anyString())).thenReturn(Optional.empty());

        // Datos de prueba
        String[] dnaSample = {"HTDCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CCCCTH", "TCHCTD"};

        // Llama al método de prueba
        boolean result = dnaAnalyzer.isAlien(dnaSample);

        // Verifica los resultados
        assertTrue(result);
    }
}
