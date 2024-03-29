package com.ct.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ct.api.domain.Veiculo;
import com.ct.api.dto.VeiculoDTO;

@RestController
@RequestMapping("/veiculo")
public interface VeiculoController {

	@GetMapping("/todos")
	public abstract List<Veiculo> listarVeiculos();

	@PostMapping("/novo")
	@ResponseStatus(HttpStatus.CREATED)
	public abstract ResponseEntity<VeiculoDTO> criarNovo(VeiculoDTO veiculoDTO);
}
