package com.amaris.prueba.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DNARecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 1000)
    private String dnaSequence;

    @Column()
    private boolean isAlien;
}
