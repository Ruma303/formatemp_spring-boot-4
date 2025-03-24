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
}
