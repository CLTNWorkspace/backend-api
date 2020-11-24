package com.ct.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ct.api.domain.Usuario;
import com.ct.api.domain.Veiculo;
import com.ct.api.dto.VeiculoDTO;
import com.ct.api.errors.BusinessException;
import com.ct.api.repository.UsuarioRepository;
import com.ct.api.repository.VeiculoRepository;
import com.ct.api.services.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private UsuarioRepository usuariorepository;

	@Override
	public List<Veiculo> listarVeiculos() {
		return veiculoRepository.findAll();
	}

	@Override
	public VeiculoDTO novoVeiculo(VeiculoDTO veiculoDTO) {

		Optional<Veiculo> veiculoExistente = veiculoRepository.findFirstByPlacaIgnoreCase(veiculoDTO.getPlaca());

		if (veiculoExistente.isPresent()) {
			throw new BusinessException("Essa placa já foi cadastrada");
		}

		Usuario dono = usuariorepository.findById(veiculoDTO.getCodigoProprietario())
				.orElseThrow(() -> new BusinessException("Usuario não encontrado"));

		Veiculo novoVeiculo = new Veiculo();
		novoVeiculo.setApelido(veiculoDTO.getApelido());
		novoVeiculo.setPlaca(veiculoDTO.getPlaca());
		novoVeiculo.setDono(dono);

		veiculoRepository.save(novoVeiculo);

		return veiculoDTO;
	}

}
