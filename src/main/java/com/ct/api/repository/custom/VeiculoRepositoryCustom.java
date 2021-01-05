package com.ct.api.repository.custom;

import java.util.List;

import com.ct.api.dto.VeiculoDTO;

public interface VeiculoRepositoryCustom {

	public abstract List<VeiculoDTO> findByProprietario(Long proprietario);
}
