package com.ct.api.services;

import java.util.List;

import com.ct.api.dto.VeiculoCadastroDTO;
import com.ct.api.dto.VeiculoDTO;

public interface VeiculoService {

	public abstract List<VeiculoDTO> listarVeiculos(String auth);

	public abstract List<VeiculoDTO> listarPorUsuario(Long id);

	public abstract VeiculoDTO novoVeiculo(VeiculoCadastroDTO veiculoDTO, String auth);

	public abstract VeiculoDTO buscarVeiculoPorPlaca(String placa);

	public abstract Boolean excluir(Long id);

}
