package com.ct.api.enumerador;

import java.util.stream.Stream;

public enum TipoVeiculoEnum {
	MOTO(1L), CARRO(2L), VAN(3L), ONIBUS(4L), CAMINHÃO(5L), CARRETA(6L);

	private Long id;

	private TipoVeiculoEnum(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return id;
	}

	public static TipoVeiculoEnum of(Long tipo) {
		return Stream.of(TipoVeiculoEnum.values()).filter(tipoVel -> tipoVel.getValue() == tipo).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
