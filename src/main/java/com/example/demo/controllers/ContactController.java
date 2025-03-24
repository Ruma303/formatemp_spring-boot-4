package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Contatto;
import com.example.demo.services.ContattoService;

@RestController
@RequestMapping("/api/contatti")
public class ContactController {

	private final ContattoService contattoService;

	@Autowired
	public ContactController(ContattoService contattoService) {
		this.contattoService = contattoService;
	}

	@GetMapping("/")
	public List<Contatto> all() {
		return contattoService.all();
	}

	@GetMapping("/{id}")
	public Contatto get(@PathVariable Long id) {
		return contattoService.get(id);
	}

	@PostMapping("/")
	public Contatto create(@RequestBody Contatto contatto) {
		return contattoService.create(contatto);
	}

	@PutMapping("/{id}")
	public Contatto update(@RequestBody Contatto contatto, @PathVariable Long id) {
		return contattoService.update(contatto, id);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		contattoService.delete(id);
		return "Contatto con id " + id + " eliminato correttamente.";
	}
}
