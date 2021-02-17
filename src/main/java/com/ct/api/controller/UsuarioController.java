package com.ct.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.LoginDTO;
import com.ct.api.dto.SucessoLoginDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;
import com.ct.api.dto.UsuarioSenhaDTO;

@RestController
@RequestMapping("/usuario")
public interface UsuarioController {

	@GetMapping("/listar")
	public abstract List<UsuarioDTO> receberTodos();

	@GetMapping("/{id}")
	public abstract ResponseEntity<UsuarioDTO> receberPorId(Long id);

	@PostMapping("/criarNovo")
	public abstract ResponseEntity<UsuarioDTO> criarNovoUsuario(UsuarioCadastroDTO usuarioCadastroDTO);

	@PostMapping("/entrar")
	public abstract ResponseEntity<SucessoLoginDTO> fazerLogin(LoginDTO login);

	@PutMapping("/editarPerfil/{codigoUsuario}")
	public abstract ResponseEntity<UsuarioDTO> atualizarUsuario(EditarUsuarioDTO editarUsuarioDTO, Long codigoUsuario);

	@PutMapping("/mudarFoto/{codigoUsuario}")
	public abstract ResponseEntity<UsuarioDTO> mudarFoto(Long codigoUsuario);

	@PutMapping("/mudarPlano/{codigoUsuario}")
	public abstract ResponseEntity<UsuarioDTO> mudarPlano(Long codigoUsuario);

	@GetMapping("/{id}/envios")
	public abstract Long contarEnvios(Long id);

	@GetMapping("/{id}/veiculos")
	public abstract Long contarVeiculos(Long id);

	@PutMapping("/novaSenha")
	public abstract Boolean mudarSenhaUsuario(UsuarioSenhaDTO usuarioSenhaDTO);

	@PostMapping("/avatar")
	public abstract String mudarFotoUsuario(String authorization, MultipartFile multipartFile);
}
