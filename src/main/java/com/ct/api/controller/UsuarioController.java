package com.ct.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuarios")
public interface UsuarioController {

	@GetMapping("/lista")
	public abstract List<Usuario> receberTodos();

	@GetMapping("/{id}")
	public abstract Usuario receberPorId(Long id);

	@PostMapping("/criarNovo")
	public abstract ResponseEntity<UsuarioDTO> criarNovoUsuario(UsuarioCadastroDTO usuarioCadastroDTO);
}
