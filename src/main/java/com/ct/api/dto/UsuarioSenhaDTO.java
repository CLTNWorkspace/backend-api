package com.ct.api.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioSenhaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String senhaAtual;

	private String novaSenha;
}
