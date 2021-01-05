package com.ct.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct.api.domain.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	public abstract Optional<Veiculo> findFirstByPlacaIgnoreCase(String placa);

	public abstract List<Veiculo> findByProprietario(Long id);
}
