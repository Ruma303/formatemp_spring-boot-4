package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.Contatto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContattoRepository extends JpaRepository<Contatto, Long> {
    // Metodi personalizzati se necessari
	
	List<Contatto> findByNome(String nome);
    Contatto findByEmail(String email);
    
    
    // Query personalizzate con JPQL (Java Persistence Query Language) 
    @Query("SELECT c FROM Contatto c WHERE c.nome = :nome")
    List<Contatto> findContattiByNome(@Param("nome") String nome);
}
