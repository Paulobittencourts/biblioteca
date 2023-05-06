package com.br.acervo.biblioteca.repository;

import com.br.acervo.biblioteca.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<UsuarioModel, Long>{

}
