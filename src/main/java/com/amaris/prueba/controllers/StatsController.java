package com.amaris.prueba.controllers;

import com.amaris.prueba.models.DNAStats;
import com.amaris.prueba.services.DNAStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final DNAStatsService dnaStatsService;

    public StatsController(DNAStatsService dnaStatsService) {
        this.dnaStatsService = dnaStatsService;
    }

    @GetMapping
    public ResponseEntity<DNAStats> getStats() {
        DNAStats stats = dnaStatsService.getStats();
        return ResponseEntity.ok(stats);
    }
}
