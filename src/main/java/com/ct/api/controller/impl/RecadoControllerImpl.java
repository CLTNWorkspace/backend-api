package com.ct.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ct.api.controller.RecadoController;
import com.ct.api.dto.RecadoCadastroDTO;
import com.ct.api.dto.RecadoDTO;
import com.ct.api.services.RecadoService;

@Controller
public class RecadoControllerImpl implements RecadoController {

	@Autowired
	private RecadoService recadoService;

	@Override
	public List<RecadoDTO> adquiriRecadosPorVeiculo(@PathVariable Long idVeiculo) {
		return recadoService.listarPorVeiculo(idVeiculo);
	}

	@Override
	public ResponseEntity<RecadoDTO> salvar(@RequestBody RecadoCadastroDTO novoRecado) {
		return ResponseEntity.ok(recadoService.salvar(novoRecado));
	}

	@Override
	public boolean like(@PathVariable Long idRecado) {
		return recadoService.like(idRecado);
	}

	@Override
	public boolean dislike(@PathVariable Long idRecado) {
		return recadoService.dislike(idRecado);
	}
}
