package com.generation.educa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.educa.model.Postagem;
import com.generation.educa.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());

	}

	@GetMapping("/{Id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long Id) {
		return repository.findById(Id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String Titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(Titulo));
	}

	@PostMapping
	public ResponseEntity<Postagem> Post(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping
	public ResponseEntity<Postagem> Put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}

	@DeleteMapping("/{Id}")
	public void delete(@PathVariable Long Id) {
		repository.deleteById(Id);
	}

}
