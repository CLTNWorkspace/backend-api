package com.ct.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct.api.domain.Usuario;
import com.ct.api.repository.custom.UsuarioRepositoryCustom;

@Repository
public interface UsuarioRepository extends UsuarioRepositoryCustom, JpaRepository<Usuario, Long> {

	public abstract Optional<Usuario> findFirstByEmailIgnoreCase(String email);

	public abstract Optional<Usuario> findFirstByCelularIgnoreCase(String telefone);

}
