package com.ct.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ct.api.controller.UsuarioController;
import com.ct.api.domain.Usuario;
import com.ct.api.repository.UsuarioRepository;

@Controller
public class UsuarioControllerImpl implements UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> receberTodos() {
		return usuarioRepository.findAll();
	}

}
