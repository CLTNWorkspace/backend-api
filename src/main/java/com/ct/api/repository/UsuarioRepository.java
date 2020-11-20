package com.ct.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ct.api.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
