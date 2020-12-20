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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "USU_SEQ", sequenceName = "SEQ_USUARIO", allocationSize = 1)
@Data
public class Usuario {

	@NotBlank
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USU_SEQ")
	@Column(name = "usuario_id")
	private Long usuarioId;

	@NotBlank
	@Column(name = "nome_usuario")
	private String nomeUsuario;

	@NotBlank
	@Email
	@Column(name = "email")
	private String email;

	@NotBlank
	@Column(name = "senha")
	private String senha;

	@NotBlank
	@Column(name = "numero_celular")
	private String celular;

	@NotBlank
	@Column(name = "cidade")
	private String cidade;

	@NotBlank
	@Column(name = "uf")
	private String uf;

	@NotBlank
	@Column(name = "usuario_ativo")
	private boolean ativo;

	@NotBlank
	@Column(name = "plano")
	private Long plano;

	@Column(name = "url_foto")
	private String foto;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_exclusao")
	private Date dataExclusao;
}
