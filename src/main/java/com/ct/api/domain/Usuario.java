package com.ct.api.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "usuario_id")
	private Long usuarioId;

	@NotNull
	@Column(name = "nome_completo")
	private String nomeCompleto;

	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "senha")
	private String senha;

	@Column(name = "cidade")
	private String cidade;

	@NotNull
	@Column(name = "telefone")
	private String telefone;

	@NotNull
	@Column(name = "usuario_ativo")
	private boolean ativo;

	@NotNull
	@Column(name = "cadastro_completo")
	private boolean cadastroCompleto;

	@Column(name = "url_foto")
	private String foto;

	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	@Column(name = "data_exclusao")
	private Date dataExclusao;
}
