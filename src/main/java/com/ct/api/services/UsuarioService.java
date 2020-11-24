package com.ct.api.services;

import java.util.List;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;

public interface UsuarioService {

	public abstract List<Usuario> listar();

	public abstract Usuario encontrarPorId(Long id);

	public abstract UsuarioDTO criarConta(UsuarioCadastroDTO usuarioCadastroDTO);
}
