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
@Table(name = "recado")
@SequenceGenerator(name = "REC_SEQ", sequenceName = "SEQ_RECADO", allocationSize = 1)
public class Recado {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REC_SEQ")
	@Column(name = "recado_id")
	private Long id;

	@Column(name = "autor")
	private Long autor;

	@Column(name = "veiculo")
	private Long veiculo;

	@NotBlank
	@Column(name = "menssagem")
	private String menssagem;

	@Column(name = "url_foto")
	private String foto;

	@Column(name = "foi_util")
	private Boolean like = false;

	@Column(name = "nao_util")
	private Boolean dislike = false;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataInsercao;
}
