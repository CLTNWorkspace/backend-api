package com.ct.api.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nomeCompleto;
	private String email;
	private String cidade;
	private String telefone;
	private String cpfCnpj;
	private boolean ativo;
	private boolean cadastroCompleto = false;
	private String foto;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Date dataExclusao;
}
