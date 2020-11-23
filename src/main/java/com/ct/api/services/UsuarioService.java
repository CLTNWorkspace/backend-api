package com.ct.api.services;

import java.util.List;

import com.ct.api.domain.Usuario;

public interface UsuarioService {

	public abstract List<Usuario> listar();

	public abstract Usuario encontrarPorId(Long id);
}
