package com.ct.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioAutenticadoDTO {

	private Long id;

	private String nome;

	private String email;

	private String celular;
}
