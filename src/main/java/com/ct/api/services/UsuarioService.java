package com.ct.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ct.api.domain.Usuario;

@Service
public interface UsuarioService {

	public List<Usuario> listar();

	public Usuario encontrarPorId(Long id);
}
