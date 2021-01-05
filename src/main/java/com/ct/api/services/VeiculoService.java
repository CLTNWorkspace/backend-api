package com.ct.api.services;

import java.util.List;

import com.ct.api.dto.VeiculoDTO;

public interface VeiculoService {

	public abstract List<VeiculoDTO> listarVeiculos(String auth);

	public abstract List<VeiculoDTO> listarPorUsuario(Long id);

	public abstract VeiculoDTO novoVeiculo(VeiculoDTO veiculoDTO);

	public abstract VeiculoDTO buscarVeiculoPorPlaca(String placa);

	public abstract Boolean excluir(Long id);

}
