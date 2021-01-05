package com.ct.api.enumerador;

public enum TipoVeiculoEnum {
	MOTO(1L), CARRO(2L), VAN(3L), ONIBUS(4L), CAMINHÃO(5L), CARRETA(6L);

	private Long id;

	private TipoVeiculoEnum(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return id;
	}

}
