package com.br.acervo.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.acervo.biblioteca.model.ReservaModel;

@Repository
public interface ReservaRepositorio extends JpaRepository<ReservaModel,Long> {
    
}
