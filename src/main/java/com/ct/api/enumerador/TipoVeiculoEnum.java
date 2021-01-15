package com.ct.api.enumerador;

import java.util.HashMap;
import java.util.Map;

public enum TipoVeiculoEnum {
	MOTO(1L), CARRO(2L), CAMINHONETE(3L), SUV(4L), VAN(5L), ONIBUS(6L), CAMINHÃO(7L), CARRETA(8L);

	private Long id;

	private static final Map<Long, TipoVeiculoEnum> TIPOS_POR_VALOR = new HashMap<>();

	static {
		for (TipoVeiculoEnum tipo : TipoVeiculoEnum.values()) {
			TIPOS_POR_VALOR.put(tipo.id, tipo);
		}

	}

	private TipoVeiculoEnum(Long id) {
		this.id = id;
	}

	public static TipoVeiculoEnum porValor(Long valor) {
		return TIPOS_POR_VALOR.get(valor);
	}

	public Long getValue() {
		return id;
	}
}
