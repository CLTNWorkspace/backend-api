package com.ct.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "VEI_SEQ", sequenceName = "VEICULO_SEQ", allocationSize = 1)
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEI_SEQ")
	@NotNull
	@Column(name = "veiculo_id")
	private Long veiculoId;

	@NotNull
	@Column(name = "apelido")
	private String apelido;

	@NotNull
	@Column(name = "placa")
	private String placa;

	@OneToOne
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

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Column(name = "data_atualizacao")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;

	@Column(name = "data_exclusao")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExclusao;
}
