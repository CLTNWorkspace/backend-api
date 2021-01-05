package com.ct.api.dto;

import lombok.Data;

@Data
public class VeiculoDTO {
	private Long id;
	private String apelido;
	private String placa;
	private Long proprietario;
	private String avatar;
}
