package com.ct.api.services;

import java.util.List;

import com.ct.api.dto.RecadoCadastroDTO;
import com.ct.api.dto.RecadoDTO;

public interface RecadoService {

	public abstract List<RecadoDTO> listarPorVeiculo(Long idVeiculo);

	public abstract RecadoDTO salvar(RecadoCadastroDTO recado);

	public abstract boolean like(Long id);

	public abstract boolean dislike(Long id);
}
