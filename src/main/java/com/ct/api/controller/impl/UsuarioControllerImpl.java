package com.ct.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ct.api.controller.UsuarioController;
import com.ct.api.domain.Usuario;
import com.ct.api.services.UsuarioService;

@Controller
public class UsuarioControllerImpl implements UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Override
	public List<Usuario> receberTodos() {
		return usuarioService.listar();
	}

	@Override
	public Usuario receberPorId(Long id) {
		return usuarioService.encontrarPorId(id);
	}
}
