package com.ct.api.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ct.api.dto.VeiculoCadastroDTO;
import com.ct.api.dto.VeiculoDTO;

public interface VeiculoService {

	public abstract List<VeiculoDTO> listarVeiculos(String auth);

	public abstract List<VeiculoDTO> listarPorUsuario(Long id);

	public abstract VeiculoDTO novoVeiculo(VeiculoCadastroDTO veiculoDTO, String auth);

	public abstract VeiculoDTO buscarVeiculoPorPlaca(String placa);

	public abstract Boolean excluir(Long id);

	public abstract String mudarFoto(Long veiculoId, MultipartFile foto);

	public abstract Boolean editar(String authorization, VeiculoDTO veiculoDTO);
}
