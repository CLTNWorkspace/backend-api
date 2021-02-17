package com.ct.api.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.LoginDTO;
import com.ct.api.dto.SucessoLoginDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;
import com.ct.api.dto.UsuarioSenhaDTO;

public interface UsuarioService {

	public abstract List<UsuarioDTO> listar();

	public abstract ResponseEntity<UsuarioDTO> encontrarPorId(Long id);

	public abstract UsuarioDTO criarConta(UsuarioCadastroDTO usuarioCadastroDTO);

	public abstract SucessoLoginDTO entrar(LoginDTO login);

	public abstract UsuarioDTO editarConta(EditarUsuarioDTO editarUsuarioDTO, Long codigoUsuario);

	public abstract UsuarioDTO editarFoto(Long codigoUsuario);

	public abstract UsuarioDTO editarPlano(Long codigoUsuario);

	public abstract Long contarEnvios(Long id);

	public abstract Long contarVeiculos(Long id);

	public abstract Boolean alterarSenha(UsuarioSenhaDTO usuarioSenhaDTO);

	public abstract String alterarFoto(String authorization, MultipartFile multipartFile);
}
