package com.amaris.prueba.controllers;

import com.amaris.prueba.models.DNARequest;
import com.amaris.prueba.services.DNAAnalyzer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alien")
public class AlienController {

    private final DNAAnalyzer dnaAnalyzer;

    public AlienController(DNAAnalyzer dnaAnalyzer) {
        this.dnaAnalyzer = dnaAnalyzer;
    }

    @PostMapping
    public ResponseEntity<Void> isAlien(@RequestBody DNARequest dnaRequest) {
        boolean isAlien = dnaAnalyzer.isAlien(dnaRequest.getDna());

        if (isAlien) {
            return ResponseEntity.ok().build();  // Devuelve 200 OK si es alienígena
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Devuelve 403 Forbidden si es humano
        }
    }
}
