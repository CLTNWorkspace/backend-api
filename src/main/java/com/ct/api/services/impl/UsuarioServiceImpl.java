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

		Optional<Usuario> usuarioExistente = usuarioRepository
				.findFirstByEmailIgnoreCase(usuarioCadastroDTO.getEmail());

		if (usuarioExistente.isPresent()) {
			throw new BusinessException("Esse email já foi cadastrado");
		}

		String senhaDecriptada = usuarioCadastroDTO.getSenha();
		String senhaEncriptada = passwordEncoder.encode(senhaDecriptada);
		Usuario novoUsuario = new Usuario();

		novoUsuario.setNomeCompleto(usuarioCadastroDTO.getNome());
		novoUsuario.setEmail(usuarioCadastroDTO.getEmail());
		novoUsuario.setSenha(senhaEncriptada);
		novoUsuario.setTelefone(usuarioCadastroDTO.getTelefone());

		usuarioRepository.save(novoUsuario);

		return modelMapper.map(usuarioCadastroDTO, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO editarConta(EditarUsuarioDTO editarUsuarioDTO, Long codigoUsuario) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(codigoUsuario);

		if (usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			usuario.setNomeCompleto(editarUsuarioDTO.getNome());
			usuario.setCidade(editarUsuarioDTO.getCidade());
			usuarioRepository.save(usuario);

			return modelMapper.map(usuario, UsuarioDTO.class);
		}

		return null;
	}
}
