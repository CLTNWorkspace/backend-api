package com.ct.api.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "veiculo_id")
	private Long veiculoId;

	@NotNull
	@Column(name = "apelido")
	private String apelido;

	@NotNull
	@Column(name = "placa")
	private String placa;

	@ManyToOne
	@JoinColumn(name = "proprietario")
	private Usuario dono;

	@Column(name = "url_imagem")
	private String foto;

	@NotNull
	@Column(name = "roubado")
	private boolean roubado;

	@NotNull
	@Column(name = "encontrado")
	private boolean encontrado;

	@NotNull
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	@Column(name = "data_exclusao")
	private Date dataExclusao;
}
