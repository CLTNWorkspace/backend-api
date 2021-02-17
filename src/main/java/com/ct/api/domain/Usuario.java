package com.ct.api.domain;

import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ct.api.enumerador.PlanoEnum;

import lombok.Data;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "USU_SEQ", sequenceName = "SEQ_USUARIO", allocationSize = 1)
@Data
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USU_SEQ")
	@Column(name = "usuario_id")
	private Long id;

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

	@Column(name = "usuario_ativo")
	private Boolean ativo = false;

	@Column(name = "plano")
	private Long plano = PlanoEnum.Free.getValue();

	@Column(name = "url_foto")
	private String foto;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao")
	private Date dataAtualizacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_exclusao")
	private Date dataExclusao;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return dataExclusao == null;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return ativo;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return dataExclusao == null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ativo;
	}
}
