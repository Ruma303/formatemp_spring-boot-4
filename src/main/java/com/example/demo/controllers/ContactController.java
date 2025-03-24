package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contatto;
import com.example.demo.services.ContattoService;

@RestController
@RequestMapping("/api/contatti")
public class ContactController {
	
	@GetMapping("/test")
	public String test() {
		return "ok";
	}

    private final ContattoService contattoService;

    @Autowired
    public ContactController(ContattoService contattoService) {
        this.contattoService = contattoService;
    }

    @GetMapping("/")
    public List<Contatto> all() {
        List<Contatto> contatti = contattoService.all();
        return contatti;
    }
}
