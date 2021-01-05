package com.ct.api.enumerador;

public enum PlanoEnum {
	Free(1L), Intermediario(2L), Pro(3L);

	private Long id;

	private PlanoEnum(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return id;
	}

}
