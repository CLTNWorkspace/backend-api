package com.ct.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAutenticadoDTO {

	private Long id;

	private String nome;

	private String email;

	private String celular;

	private String foto;

	private Long plano;

	private String localidade;
}
