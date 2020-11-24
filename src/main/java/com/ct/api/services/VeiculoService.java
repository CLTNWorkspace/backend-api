package com.ct.api.services;

import java.util.List;

import com.ct.api.domain.Veiculo;
import com.ct.api.dto.VeiculoDTO;

public interface VeiculoService {

	public abstract List<Veiculo> listarVeiculos();

	public abstract VeiculoDTO novoVeiculo(VeiculoDTO veiculoDTO);
}
