package com.amaris.prueba.services;

import com.amaris.prueba.models.DNAStats;
import com.amaris.prueba.repositorys.DNARecordRepository;
import org.springframework.stereotype.Service;

@Service
public class DNAStatsService {

    private final DNARecordRepository dnaRecordRepository;

    public DNAStatsService(DNARecordRepository dnaRecordRepository) {
        this.dnaRecordRepository = dnaRecordRepository;
    }

    public DNAStats getStats() {
        long countAlien = dnaRecordRepository.countByIsAlien(true);
        long countHuman = dnaRecordRepository.countByIsAlien(false);
        double ratio = (countHuman == 0) ? 0 : (double) countAlien / countHuman;

        return new DNAStats(countAlien, countHuman, ratio);
    }
}

