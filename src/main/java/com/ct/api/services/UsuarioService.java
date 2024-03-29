package com.ct.api.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;

public interface UsuarioService {

	public abstract List<Usuario> listar();

	public abstract ResponseEntity<UsuarioDTO> encontrarPorId(Long id);

	public abstract UsuarioDTO criarConta(UsuarioCadastroDTO usuarioCadastroDTO);

	public abstract UsuarioDTO editarConta(EditarUsuarioDTO editarUsuarioDTO, Long codigoUsuario);

	public abstract UsuarioDTO editarFoto(Long codigoUsuario);

	public abstract UsuarioDTO editarPlano(Long codigoUsuario);
}
