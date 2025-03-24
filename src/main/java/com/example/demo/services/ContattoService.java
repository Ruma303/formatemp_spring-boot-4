package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Contatto;
import com.example.demo.repositories.ContattoRepository;

@Service
public class ContattoService {

    private final ContattoRepository contattoRepository;

    @Autowired
    public ContattoService(ContattoRepository contattoRepository) {
        this.contattoRepository = contattoRepository;
    }

    public List<Contatto> all() {
        List<Contatto> contatti = contattoRepository.findAll();
        return contatti;
    }
    
    public Contatto get(Long id) {
        return contattoRepository.findById(id).orElseThrow(() -> new RuntimeException("Contatto non trovato"));
    }

    public Contatto create(Contatto contatto) {
        return contattoRepository.save(contatto);
    }
    
    public Contatto update(Contatto contatto, Long id) {
        Contatto contattoEsistente = contattoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
        contattoEsistente.setNome(contatto.getNome());
        contattoEsistente.setCognome(contatto.getCognome());
        contattoEsistente.setEmail(contatto.getEmail());
        contattoEsistente.setNumero(contatto.getNumero());
        contattoEsistente.setNote(contatto.getNote());
        
        return contattoRepository.save(contattoEsistente);
    }

    public void delete(Long id) {
        Contatto contatto = contattoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contatto non trovato"));
        contattoRepository.delete(contatto);
    }
}
