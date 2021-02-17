package com.ct.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.ct.api.domain.Usuario;
import com.ct.api.dto.EditarUsuarioDTO;
import com.ct.api.dto.LoginDTO;
import com.ct.api.dto.SucessoLoginDTO;
import com.ct.api.dto.UsuarioAutenticadoDTO;
import com.ct.api.dto.UsuarioCadastroDTO;
import com.ct.api.dto.UsuarioDTO;
import com.ct.api.dto.UsuarioSenhaDTO;
import com.ct.api.errors.BusinessException;
import com.ct.api.repository.UsuarioRepository;
import com.ct.api.security.JwtTokenService;
import com.ct.api.services.AmazonS3Service;
import com.ct.api.services.UsuarioService;
import com.ct.api.util.StringUtil;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenService tokenService;

	@Autowired
	private AmazonS3Service amazonS3Service;

	@Override
	public List<UsuarioDTO> listar() {
		return usuarioRepository.findAll().stream().map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
				.collect(Collectors.toList());
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

		usuarioRepository.findFirstByCelularIgnoreCase(usuarioCadastroDTO.getCelular()).ifPresent(c -> {
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
	public UsuarioDTO editarPlano(Long codigoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long contarEnvios(Long id) {
		return usuarioRepository.envios(id);
	}

	@Override
	public Long contarVeiculos(Long id) {
		return usuarioRepository.veiculos(id);
	}

	@Override
	public SucessoLoginDTO entrar(LoginDTO login) {
		Usuario usuarioLogin = usuarioRepository.findFirstByEmailIgnoreCase(login.getEmail())
				.orElseThrow(() -> new BusinessException("Email inválido"));

		if (!passwordEncoder.matches(login.getSenha(), usuarioLogin.getSenha())) {
			throw new BusinessException("Senha incorreta");
		}
		SucessoLoginDTO sucesso = new SucessoLoginDTO();
		sucesso.setToken(tokenService.gerarToken(usuarioLogin));

		return sucesso;
	}

	@Override
	public Boolean alterarSenha(UsuarioSenhaDTO usuarioSenhaDTO) {

		Assert.isTrue(!usuarioSenhaDTO.getSenhaAtual().equals(usuarioSenhaDTO.getNovaSenha()),
				"As senhas não podem ser iguais");
		Assert.hasText(usuarioSenhaDTO.getNovaSenha(), "A nova senha não pode ser vazia");
		Assert.isTrue(usuarioSenhaDTO.getNovaSenha().length() >= 8, "A senha deve ter 8 ou mais caracteres");

		Usuario usuario = usuarioRepository.findById(usuarioSenhaDTO.getId())
				.orElseThrow(() -> new BusinessException("Usuário não encontrado"));

		if (!passwordEncoder.matches(usuarioSenhaDTO.getSenhaAtual(), usuario.getSenha())) {
			throw new BusinessException("Senha incorreta");
		}

		String senhaEncriptada = passwordEncoder.encode(usuarioSenhaDTO.getNovaSenha());
		usuario.setSenha(senhaEncriptada);

		usuarioRepository.save(usuario);

		return true;
	}

	@Override
	public String alterarFoto(String authorization, MultipartFile multipartFile) {
		Assert.isTrue(multipartFile.getOriginalFilename().contains(".jpg")
				|| multipartFile.getOriginalFilename().contains(".jpeg"), "Tipo de arquivo inválido");

		UsuarioAutenticadoDTO obterDadosUsuario = tokenService.obterDadosUsuario(authorization);
		String nomeUsuario = StringUtil.removerAcentos(obterDadosUsuario.getNome().replace(" ", "_").toLowerCase());
		String usuarioDados = obterDadosUsuario.getId().toString().concat("_").concat(nomeUsuario);
		String url = null;

		Usuario usuario = usuarioRepository.findById(obterDadosUsuario.getId())
				.orElseThrow(() -> new BusinessException("Usuário não encontrado"));

		try {
			if (usuario.getFoto() != null) {
				amazonS3Service.deleteFile(usuario.getFoto(), "fotousuarios");
			}

			url = amazonS3Service.fileUpload("usuarios", usuarioDados, multipartFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (url != null) {
			usuario.setFoto(url);
			usuarioRepository.save(usuario);
		}

		return url;
	}

	@Override
	public Boolean editarUsuario(String authorization, UsuarioDTO usuarioDTO) {
		Assert.isTrue(usuarioDTO.getCidade() != null && usuarioDTO.getUf() != null, "Os dados não podem estar vazios");

		UsuarioAutenticadoDTO dadosLogin = tokenService.obterDadosUsuario(authorization);

		Usuario usuario = usuarioRepository.findById(dadosLogin.getId())
				.orElseThrow(() -> new BusinessException("Usuário não encontrado"));

		usuario.setCidade(usuarioDTO.getCidade());
		usuario.setUf(usuarioDTO.getUf());

		usuarioRepository.save(usuario);

		return true;
	}
}
