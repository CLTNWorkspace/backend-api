package com.ct.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct.api.dto.RecadoCadastroDTO;
import com.ct.api.dto.RecadoDTO;

@RestController
@RequestMapping("/recado")
public interface RecadoController {

	@GetMapping("/{idVeiculo}")
	public abstract List<RecadoDTO> adquiriRecadosPorVeiculo(Long idVeiculo);

	@PostMapping("/salvar")
	public abstract ResponseEntity<RecadoDTO> salvar(RecadoCadastroDTO novoRecado);

	@PutMapping("/like/{idRecado}")
	public abstract boolean like(Long idRecado);

	@PutMapping("/dislike/{idRecado}")
	public abstract boolean dislike(Long idRecado);
}
