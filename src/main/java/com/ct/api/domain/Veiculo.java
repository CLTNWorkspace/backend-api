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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "VEI_SEQ", sequenceName = "SEQ_VEICULO", allocationSize = 1)
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEI_SEQ")
	@Column(name = "veiculo_id")
	private Long id;

	@NotBlank
	@Column(name = "apelido")
	private String apelido;

	@NotBlank
	@Column(name = "placa")
	private String placa;

	@Column(name = "proprietario")
	private Long proprietario;

	@Column(name = "url_foto")
	private String foto;

	@Column(name = "roubado")
	private boolean roubado;

	@Column(name = "encontrado")
	private boolean encontrado;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCriacao;

	@Column(name = "data_atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;

	@Column(name = "data_exclusao")
	@Temporal(TemporalType.DATE)
	private Date dataExclusao;
}
