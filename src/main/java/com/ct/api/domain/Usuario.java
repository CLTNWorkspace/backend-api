package com.ct.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "USU_SEQ", sequenceName = "USUARIO_SEQ", allocationSize = 1)
@Data
public class Usuario {

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USU_SEQ")
	@Column(name = "usuario_id")
	private Long usuarioId;

	@NotBlank
	@Column(name = "nome_completo")
	private String nomeCompleto;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "senha")
	private String senha;

	@Column(name = "cidade")
	private String cidade;

	@NotBlank
	@Column(name = "telefone")
	private String telefone;

	@NotNull
	@Column(name = "usuario_ativo")
	private boolean ativo;

	@Column(name = "plano")
	private Long plano;

	@NotNull
	@Column(name = "cadastro_completo")
	private boolean cadastroCompleto;

	@Column(name = "url_foto")
	private String foto;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "data_exclusao")
	private Date dataExclusao;
}
