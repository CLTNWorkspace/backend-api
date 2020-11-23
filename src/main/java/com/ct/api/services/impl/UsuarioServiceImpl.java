package com.ct.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ct.api.domain.Usuario;
import com.ct.api.repository.UsuarioRepository;
import com.ct.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario encontrarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}
}
