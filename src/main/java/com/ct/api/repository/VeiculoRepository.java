package com.ct.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ct.api.domain.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	public abstract Optional<Veiculo> findFirstByPlacaIgnoreCase(String placa);
}
