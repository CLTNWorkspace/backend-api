package com.ct.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct.api.domain.Usuario;

@RestController
@RequestMapping("/usuarios")
public interface UsuarioController {

	@GetMapping("/lista")
	public List<Usuario> receberTodos();

	@GetMapping("/{id}")
	public Usuario receberPorId(@PathVariable Long id);
}
