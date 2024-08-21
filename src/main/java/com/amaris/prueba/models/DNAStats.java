package com.amaris.prueba.models;

import lombok.Data;

@Data
public class DNAStats {
    private long countAlienDna;
    private long countHumanDna;
    private double ratio;

    public DNAStats(long countAlienDna, long countHumanDna, double ratio) {
        this.countAlienDna = countAlienDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }
}

