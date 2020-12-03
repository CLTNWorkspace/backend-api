package com.ct.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ct.api.controller.UsuarioController;
import com.ct.api.domain.Usuario;
import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;
import com.ct.api.services.UsuarioService;

@Controller
public class UsuarioControllerImpl implements UsuarioController {

	@Autowired
	@Lazy
	private UsuarioService usuarioService;

	@Override
	public List<Usuario> receberTodos() {
		return usuarioService.listar();
	}

	@Override
	public ResponseEntity<UsuarioDTO> receberPorId(@PathVariable("id") Long id) {
		return usuarioService.encontrarPorId(id);
	}

	@Override
	public ResponseEntity<UsuarioDTO> criarNovoUsuario(@RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
		return ResponseEntity.ok(usuarioService.criarConta(usuarioCadastroDTO));
	}

	@Override
	public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody EditarUsuarioDTO editarUsuarioDTO,
			@PathVariable Long codigoUsuario) {
		return ResponseEntity.ok(usuarioService.editarConta(editarUsuarioDTO, codigoUsuario));
	}

	@Override
	public ResponseEntity<UsuarioDTO> mudarFoto(@PathVariable Long codigoUsuario) {
		return null;
	}

	@Override
	public ResponseEntity<UsuarioDTO> mudarPlano(@PathVariable Long codigoUsuario) {
		return null;
	}
}
