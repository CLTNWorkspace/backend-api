package com.ct.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ct.api.controller.VeiculoController;
import com.ct.api.dto.VeiculoDTO;
import com.ct.api.services.VeiculoService;

@Controller
public class VeiculoControllerImpl implements VeiculoController {

	@Autowired
	private VeiculoService veiculoService;

	@Override
	public List<VeiculoDTO> listarVeiculos(@RequestHeader("Authorization") String auth) {
		return veiculoService.listarVeiculos(auth);
	}

	@Override
	public ResponseEntity<VeiculoDTO> criarNovo(@RequestBody VeiculoDTO veiculoDTO) {
		return ResponseEntity.ok(veiculoService.novoVeiculo(veiculoDTO));
	}

	@Override
	public List<VeiculoDTO> listarPorUsuario(@PathVariable Long idUsuario) {
		return veiculoService.listarPorUsuario(idUsuario);
	}

	@Override
	public VeiculoDTO listarPorPlaca(@PathVariable String placa) {
		return veiculoService.buscarVeiculoPorPlaca(placa);
	}

	@Override
	public Boolean excluirVeiculo(@PathVariable("id") Long idVeiculo) {
		return veiculoService.excluir(idVeiculo);
	}

}
