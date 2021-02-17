package com.ct.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public abstract ResponseEntity<VeiculoDTO> criarNovo(VeiculoCadastroDTO veiculoDTO, String auth);

	@DeleteMapping("/excluir/{id}")
	public abstract Boolean excluirVeiculo(Long idVeiculo);

	@PostMapping("/{veiculo}/avatar")
	public abstract String mudarFotoUsuario(Long veiculoId, MultipartFile foto);

	@PutMapping("/editar")
	public abstract ResponseEntity<Boolean> editar(String authorization, VeiculoDTO veiculoDTO);
}
