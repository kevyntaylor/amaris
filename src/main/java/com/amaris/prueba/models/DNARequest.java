package com.amaris.prueba.models;

import lombok.Data;

@Data
public class DNARequest {

    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}

