package com.generation.educa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.educa.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	void start() {
		
		repository
			.save(new Usuario(0L, "Marjory Matos", "marjory@test.com.br", "123456789", "https://google.com.br"));
		repository
			.save(new Usuario(0L, "Yhuri Gross", "yhuri@test.com.br", "123456789", "https://google.com.br"));
		repository
			.save(new Usuario(0L, "Simone Matos", "simone@test.com.br", "123456789", "https://google.com.br"));
		repository
			.save(new Usuario(0L, "Usuario Usuario", "usuario@test.com.br", "123456789", "https://google.com.br"));
	}
	
	@Test
	@DisplayName("Retorna apenas um usuario")
	public void retornarUmUsuario() {
		
		Optional<Usuario> usuario = repository.findByUsuario("marjory@test.com.br");
		assertTrue(usuario.get().getUsuario().equals("marjory@test.com.br"));
		
	}
	
	@Test
	@DisplayName("Retorna dois usuarios")
	public void retornaDoisUsuarios() {
		List<Usuario> listDeUsuarios = repository.findAllByNomeContainingIgnoreCase("Matos");
		assertEquals(2, listDeUsuarios.size());
		
		assertTrue(listDeUsuarios.get(0).getNome().equals("Marjory Matos"));
		assertTrue(listDeUsuarios.get(1).getNome().equals("Simone Matos"));
		
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}


}
