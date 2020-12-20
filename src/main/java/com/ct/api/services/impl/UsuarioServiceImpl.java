package com.ct.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;
import com.ct.api.errors.BusinessException;
import com.ct.api.repository.UsuarioRepository;
import com.ct.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@Override
	public ResponseEntity<UsuarioDTO> encontrarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			UsuarioDTO usuarioDTO = modelMapper.map(usuario.get(), UsuarioDTO.class);
			return ResponseEntity.ok(usuarioDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public UsuarioDTO criarConta(UsuarioCadastroDTO usuarioCadastroDTO) {

		usuarioRepository.findFirstByEmailIgnoreCase(usuarioCadastroDTO.getEmail()).ifPresent(e -> {
			throw new BusinessException("Esse email já foi cadastrado");
		});

		usuarioRepository.findFirstByCelularIgnoreCase(usuarioCadastroDTO.getCelular()).ifPresent(t -> {
			throw new BusinessException("Esse telefone já foi cadastrado");
		});

		String senhaDecriptada = usuarioCadastroDTO.getSenha();
		String senhaEncriptada = passwordEncoder.encode(senhaDecriptada);
		Usuario novoUsuario = new Usuario();

		novoUsuario.setNomeUsuario(usuarioCadastroDTO.getNome());
		novoUsuario.setEmail(usuarioCadastroDTO.getEmail());
		novoUsuario.setSenha(senhaEncriptada);
		novoUsuario.setCelular(usuarioCadastroDTO.getCelular());
		novoUsuario.setCidade(usuarioCadastroDTO.getCidade());
		novoUsuario.setUf(usuarioCadastroDTO.getUf());

		usuarioRepository.save(novoUsuario);

		return modelMapper.map(novoUsuario, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO editarConta(EditarUsuarioDTO editarUsuarioDTO, Long codigoUsuario) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(codigoUsuario);

		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			usuario.setNomeUsuario(editarUsuarioDTO.getNome());
			usuario.setCidade(editarUsuarioDTO.getCidade());
			usuarioRepository.save(usuario);

			return modelMapper.map(usuario, UsuarioDTO.class);
		}

		return null;
	}

	@Override
	public UsuarioDTO editarFoto(Long codigoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioDTO editarPlano(Long codigoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
