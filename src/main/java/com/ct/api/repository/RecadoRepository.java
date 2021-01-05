package com.ct.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct.api.domain.Recado;

@Repository
public interface RecadoRepository extends JpaRepository<Recado, Long> {

	public abstract List<Recado> findByVeiculo(Long id);
}
