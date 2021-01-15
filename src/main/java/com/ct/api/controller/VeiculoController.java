package com.ct.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ct.api.dto.VeiculoCadastroDTO;
import com.ct.api.dto.VeiculoDTO;

@RestController
@RequestMapping("/veiculo")
public interface VeiculoController {

	@GetMapping("/todos")
	public abstract List<VeiculoDTO> listarVeiculos(String auth);

	@GetMapping("/porUsuario/{idUsuario}")
	public abstract List<VeiculoDTO> listarPorUsuario(Long idUsuario);

	@GetMapping("/porPlaca/{placa}")
	public abstract VeiculoDTO listarPorPlaca(String placa);

	@PostMapping("/novo")
	@ResponseStatus(HttpStatus.CREATED)
	public abstract ResponseEntity<VeiculoDTO> criarNovo(VeiculoCadastroDTO veiculoDTO, String auth);

	@DeleteMapping("/excluir/{id}")
	public abstract Boolean excluirVeiculo(Long idVeiculo);
}
