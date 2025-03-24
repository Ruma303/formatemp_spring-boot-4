package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Contatto;

public interface ContattoRepository extends JpaRepository<Contatto, Long> {
    // Metodi personalizzati se necessari
}
