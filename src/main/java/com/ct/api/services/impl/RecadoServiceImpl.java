package com.ct.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ct.api.domain.Recado;
import com.ct.api.domain.Usuario;
import com.ct.api.domain.Veiculo;
import com.ct.api.dto.RecadoCadastroDTO;
import com.ct.api.dto.RecadoDTO;
import com.ct.api.errors.BusinessException;
import com.ct.api.repository.RecadoRepository;
import com.ct.api.repository.UsuarioRepository;
import com.ct.api.repository.VeiculoRepository;
import com.ct.api.services.RecadoService;

@Service
public class RecadoServiceImpl implements RecadoService {

	@Autowired
	private RecadoRepository recadoRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<RecadoDTO> listarPorVeiculo(Long idVeiculo) {
		return recadoRepository.findByVeiculo(idVeiculo).stream()
				.map(recado -> modelMapper.map(recado, RecadoDTO.class)).collect(Collectors.toList());
	}

	@Override
	public RecadoDTO salvar(RecadoCadastroDTO recado) {

		Usuario autor = usuarioRepository.findById(recado.getAutor())
				.orElseThrow(() -> new BusinessException("Usuario não encontrado"));
		Veiculo veiculo = veiculoRepository.findFirstByPlacaIgnoreCase(recado.getPlaca())
				.orElseThrow(() -> new BusinessException("Veículo não encontrado"));

		Recado novoRecado = new Recado();

		novoRecado.setAutor(autor.getId());
		novoRecado.setVeiculo(veiculo.getId());
		novoRecado.setMenssagem(recado.getMenssagem());

		recadoRepository.save(novoRecado);

		return modelMapper.map(novoRecado, RecadoDTO.class);
	}

	@Override
	public boolean like(Long id) {
		recadoRepository.findById(id).ifPresentOrElse(recado -> {
			if (recado.getDislike()) {
				recado.setDislike(false);
			}

			recado.setLike(!recado.getLike());
			recadoRepository.save(recado);
		}, () -> {
			throw new BusinessException("Recado não encontrado");
		});
		return true;
	}

	@Override
	public boolean dislike(Long id) {
		recadoRepository.findById(id).ifPresentOrElse(recado -> {

			if (recado.getLike()) {
				recado.setLike(false);
			}

			recado.setDislike(!recado.getDislike());
			recadoRepository.save(recado);
		}, () -> {
			throw new BusinessException("Recado não encontrado");
		});

		return true;
	}

}
