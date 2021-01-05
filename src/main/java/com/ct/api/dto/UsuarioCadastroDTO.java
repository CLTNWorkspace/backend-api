package com.ct.api.dto;

import java.io.Serializable;

import com.ct.api.enumerador.PlanoEnum;

import lombok.Data;

@Data
public class UsuarioCadastroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	private String email;

	private String senha;

	private String cidade;

	private String uf;

	private Long plano = PlanoEnum.Free.getValue();

	private String celular;
}
