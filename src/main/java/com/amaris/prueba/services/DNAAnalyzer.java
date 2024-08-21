package com.amaris.prueba.services;

import com.amaris.prueba.models.DNARecord;
import com.amaris.prueba.repositorys.DNARecordRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DNAAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(DNAAnalyzer.class);

    private final DNARecordRepository dnaRecordRepository;

    public DNAAnalyzer(DNARecordRepository dnaRecordRepository) {
        this.dnaRecordRepository = dnaRecordRepository;
    }

    @Transactional
    public boolean isAlien(String[] dna) {
        // Convertimos la secuencia de ADN en un string único para la base de datos
        String dnaSequence = String.join(",", dna);

        // Verificamos si ya existe en la base de datos
        if (dnaRecordRepository.findByDnaSequence(dnaSequence).isPresent()) {
            return dnaRecordRepository.findByDnaSequence(dnaSequence).get().isAlien();
        }

        int n = dna.length;
        int countSequences = 0;

        // Lógica para verificar si es alienígena
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (checkHorizontal(dna, i, j, n) ||
                        checkVertical(dna, i, j, n) ||
                        checkDiagonalRight(dna, i, j, n) ||
                        checkDiagonalLeft(dna, i, j, n)) {
                    countSequences++;
                    if (countSequences > 1) {
                        // Guardamos en la base de datos como alienígena
                        DNARecord record = new DNARecord();
                        record.setDnaSequence(dnaSequence);
                        record.setAlien(true);
                        dnaRecordRepository.save(record);
                        return true; // Alienígena
                    }
                }
            }
        }

        // Guardamos en la base de datos como humano
        DNARecord record = new DNARecord();
        record.setDnaSequence(dnaSequence);
        record.setAlien(false);
        dnaRecordRepository.save(record);
        return false;  // Humano
    }

    // Verificar secuencia horizontal
    private boolean checkHorizontal(String[] dna, int row, int col, int n) {
        if (col + 3 < n) {
            char base = dna[row].charAt(col);
            return base == dna[row].charAt(col + 1) &&
                    base == dna[row].charAt(col + 2) &&
                    base == dna[row].charAt(col + 3);
        }
        return false;
    }

    // Verificar secuencia vertical
    private boolean checkVertical(String[] dna, int row, int col, int n) {
        if (row + 3 < n) {
            char base = dna[row].charAt(col);
            return base == dna[row + 1].charAt(col) &&
                    base == dna[row + 2].charAt(col) &&
                    base == dna[row + 3].charAt(col);
        }
        return false;
    }

    // Verificar secuencia diagonal hacia la derecha abajo
    private boolean checkDiagonalRight(String[] dna, int row, int col, int n) {
        if (row + 3 < n && col + 3 < n) {
            char base = dna[row].charAt(col);
            return base == dna[row + 1].charAt(col + 1) &&
                    base == dna[row + 2].charAt(col + 2) &&
                    base == dna[row + 3].charAt(col + 3);
        }
        return false;
    }

    // Verificar secuencia diagonal hacia la izquierda abajo
    private boolean checkDiagonalLeft(String[] dna, int row, int col, int n) {
        if (row + 3 < n && col - 3 >= 0) {
            char base = dna[row].charAt(col);
            return base == dna[row + 1].charAt(col - 1) &&
                    base == dna[row + 2].charAt(col - 2) &&
                    base == dna[row + 3].charAt(col - 3);
        }
        return false;
    }
}
