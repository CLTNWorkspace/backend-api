package com.ct.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.ct.api.controller.VeiculoController;
import com.ct.api.domain.Veiculo;
import com.ct.api.dto.VeiculoDTO;
import com.ct.api.services.VeiculoService;

@Controller
public class VeiculoControllerImpl implements VeiculoController {

	@Autowired
	private VeiculoService veiculoService;

	@Override
	public List<Veiculo> listarVeiculos() {
		return veiculoService.listarVeiculos();
	}

	@Override
	public ResponseEntity<VeiculoDTO> criarNovo(@RequestBody VeiculoDTO veiculoDTO) {
		return ResponseEntity.ok(veiculoService.novoVeiculo(veiculoDTO));
	}

}
