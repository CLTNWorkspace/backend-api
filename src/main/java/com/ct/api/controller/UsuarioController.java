package com.ct.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
public interface UsuarioController {

	@GetMapping("/listar")
	public abstract List<Usuario> receberTodos();

	@GetMapping("/{id}")
	public abstract ResponseEntity<UsuarioDTO> receberPorId(Long id);

	@PostMapping("/criarNovo")
	public abstract ResponseEntity<UsuarioDTO> criarNovoUsuario(UsuarioCadastroDTO usuarioCadastroDTO);

	@PutMapping("/editarPerfil/{codigoUsuario}")
	public abstract ResponseEntity<UsuarioDTO> atualizarUsuario(EditarUsuarioDTO editarUsuarioDTO, Long codigoUsuario);

	@PutMapping("/mudarFoto/{codigoUsuario}")
	public abstract ResponseEntity<UsuarioDTO> mudarFoto(Long codigoUsuario);// https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/

	@PutMapping("/mudarPlano/{codigoUsuario}")
	public abstract ResponseEntity<UsuarioDTO> mudarPlano(Long codigoUsuario);
}
