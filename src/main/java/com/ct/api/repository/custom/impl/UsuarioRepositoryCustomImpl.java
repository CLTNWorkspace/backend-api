package com.ct.api.repository.custom.impl;

import static com.ct.api.domain.QRecado.recado;
import static com.ct.api.domain.QUsuario.usuario;
import static com.ct.api.domain.QVeiculo.veiculo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ct.api.domain.Usuario;
import com.ct.api.repository.custom.UsuarioRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long envios(Long idUsuario) {
		JPAQuery<Usuario> query = new JPAQuery<>(entityManager);
		query.from(usuario);
		query.innerJoin(recado).on(usuario.id.eq(recado.autor));
		query.where(usuario.id.eq(idUsuario));
		return query.fetchCount();
	}

	@Override
	public Long veiculos(Long idUsuario) {
		JPAQuery<Usuario> query = new JPAQuery<>(entityManager);
		query.from(usuario);
		query.innerJoin(veiculo).on(usuario.id.eq(veiculo.proprietario));
		query.where(usuario.id.eq(idUsuario));
		return query.fetchCount();
	}

}
