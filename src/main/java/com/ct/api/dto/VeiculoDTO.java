package com.ct.api.dto;

import com.ct.api.enumerador.TipoVeiculoEnum;

import lombok.Data;

@Data
public class VeiculoDTO {
	private Long id;
	private String apelido;
	private String placa;
	private Long proprietario;
	private TipoVeiculoEnum tipoVeiculoEnum;
	private String avatar;
}
