package com.ct.api.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String cidade;
	private String telefone;
}
