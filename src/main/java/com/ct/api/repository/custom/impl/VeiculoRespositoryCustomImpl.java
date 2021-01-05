package com.ct.api.repository.custom.impl;

import static com.ct.api.domain.QUsuario.usuario;
import static com.ct.api.domain.QVeiculo.veiculo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ct.api.domain.Veiculo;
import com.ct.api.dto.VeiculoDTO;
import com.ct.api.repository.custom.VeiculoRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

public class VeiculoRespositoryCustomImpl implements VeiculoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<VeiculoDTO> findByProprietario(Long proprietario) {
		JPAQuery<Veiculo> query = new JPAQuery<Veiculo>(entityManager);
		query.from(veiculo);
		query.where(veiculo.dataExclusao.isNull());
		query.innerJoin(usuario).on(veiculo.proprietario.eq(usuario.id));
		query.where(usuario.ativo.isTrue(), usuario.dataExclusao.isNull(), usuario.id.eq(proprietario));
		return query
				.select(Projections.bean(VeiculoDTO.class, veiculo.apelido, veiculo.placa, veiculo.foto.as("avatar")))
				.fetch();
	}

}
