package com.amaris.prueba.repositorys;

import com.amaris.prueba.models.DNARecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DNARecordRepository extends JpaRepository<DNARecord, Long> {
    Optional<DNARecord> findByDnaSequence(String dnaSequence);

    long countByIsAlien(boolean isAlien);
}

