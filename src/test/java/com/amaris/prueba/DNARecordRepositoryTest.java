package com.amaris.prueba;

import com.amaris.prueba.models.DNARecord;
import com.amaris.prueba.repositorys.DNARecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class DNARecordRepositoryTest {

    @Autowired
    private DNARecordRepository dnaRecordRepository;

    @Test
    public void testFindByDnaSequence() {
        // Crea un registro y guárdalo en la base de datos
        DNARecord record = new DNARecord();
        record.setDnaSequence("HTDCDH");
        record.setAlien(true);
        dnaRecordRepository.save(record);

        // Consulta el registro por secuencia de DNA
        Optional<DNARecord> foundRecord = dnaRecordRepository.findByDnaSequence("HTDCDH");

        // Verifica que el registro encontrado es el esperado
        assertEquals("HTDCDH", foundRecord.get().getDnaSequence());
        assertEquals(true, foundRecord.get().isAlien());
    }
}
