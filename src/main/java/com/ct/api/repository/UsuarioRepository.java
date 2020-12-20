package com.ct.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ct.api.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public abstract Optional<Usuario> findFirstByEmailIgnoreCase(String email);

	public abstract Optional<Usuario> findFirstByCelularIgnoreCase(String telefone);

}
