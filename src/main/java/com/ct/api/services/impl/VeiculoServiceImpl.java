package com.ct.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.ct.api.domain.Veiculo;
import com.ct.api.dto.UsuarioAutenticadoDTO;
import com.ct.api.dto.VeiculoCadastroDTO;
import com.ct.api.dto.VeiculoDTO;
import com.ct.api.enumerador.TipoVeiculoEnum;
import com.ct.api.errors.BusinessException;
import com.ct.api.repository.UsuarioRepository;
import com.ct.api.repository.VeiculoRepository;
import com.ct.api.security.JwtTokenService;
import com.ct.api.services.AmazonS3Service;
import com.ct.api.services.VeiculoService;
import com.ct.api.util.StringUtil;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtTokenService tokenService;

	@Autowired
	private AmazonS3Service amazonS3Service;

	@Override
	public List<VeiculoDTO> listarVeiculos(String auth) {
		return veiculoRepository.findAll().stream().map(veiculo -> modelMapper.map(veiculo, VeiculoDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public VeiculoDTO novoVeiculo(VeiculoCadastroDTO veiculoDTO, String auth) {

		veiculoRepository.findFirstByPlacaIgnoreCase(veiculoDTO.getPlaca()).ifPresent(veiculo -> {
			throw new BusinessException("Essa placa já foi cadastrada");
		});

		UsuarioAutenticadoDTO dadosLogin = tokenService.obterDadosUsuario(auth);

		Long veiculos = usuariorepository.veiculos(dadosLogin.getId());

		if (veiculos > 2 && dadosLogin.getPlano() == 1) {
			throw new BusinessException("Para cadastrar mais veículos atualize seu plano");
		}

		Veiculo novoVeiculo = new Veiculo();
		novoVeiculo.setApelido(veiculoDTO.getApelido());
		novoVeiculo.setPlaca(veiculoDTO.getPlaca());
		novoVeiculo.setProprietario(dadosLogin.getId());
		novoVeiculo.setTipoVeiculo(TipoVeiculoEnum.porValor(veiculoDTO.getTipoVeiculo()));

		veiculoRepository.save(novoVeiculo);

		return modelMapper.map(novoVeiculo, VeiculoDTO.class);
	}

	@Override
	public List<VeiculoDTO> listarPorUsuario(Long id) {
		return veiculoRepository.findByProprietario(id).stream()
				.map(veiculo -> modelMapper.map(veiculo, VeiculoDTO.class)).collect(Collectors.toList());
	}

	@Override
	public VeiculoDTO buscarVeiculoPorPlaca(String placa) {
		Veiculo veiculo = veiculoRepository.findFirstByPlacaIgnoreCase(placa)
				.orElseThrow(() -> new BusinessException("Veículo não encontrado"));
		return modelMapper.map(veiculo, VeiculoDTO.class);
	}

	@Override
	public Boolean excluir(Long id) {
		veiculoRepository.findById(id).ifPresentOrElse(veiculo -> {
			veiculoRepository.delete(veiculo);
		}, () -> {
			throw new BusinessException("Veículo não encontrado");
		});
		return true;
	}

	@Override
	public String mudarFoto(Long veiculoId, MultipartFile foto) {
		Assert.isTrue(foto.getOriginalFilename().contains(".jpg") || foto.getOriginalFilename().contains(".jpeg")
				|| foto.getOriginalFilename().contains(".png"), "Tipo de arquivo inválido");

		Veiculo veiculo = veiculoRepository.findById(veiculoId)
				.orElseThrow(() -> new BusinessException("Veículo não encontrado"));

		String nomeVeiculo = StringUtil.removerAcentos(veiculo.getApelido().replace(" ", "_").toLowerCase());
		String veiculoDados = veiculo.getId().toString().concat("_").concat(nomeVeiculo);
		String url = null;

		try {
			if (veiculo.getFoto() != null) {
				amazonS3Service.deleteFile(veiculo.getFoto(), "veiculos");
			}

			url = amazonS3Service.fileUpload("veiculos", veiculoDados, foto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (url != null) {
			veiculo.setFoto(url);
			veiculoRepository.save(veiculo);
		}

		return url;
	}

	@Override
	public Boolean editar(String authorization, VeiculoDTO veiculoDTO) {
		UsuarioAutenticadoDTO dadosLogin = tokenService.obterDadosUsuario(authorization);

		Veiculo veiculo = veiculoRepository.findFirstByIdAndProprietario(veiculoDTO.getId(), dadosLogin.getId())
				.orElseThrow(() -> new BusinessException("Veículo não encontrado"));

		veiculo.setApelido(veiculoDTO.getApelido());
		veiculoRepository.save(veiculo);

		return true;
	}

}
