package com.br.acervo.biblioteca.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto {
    
    private long id;

    private long livro;

    private UsuarioDto usuario;
}
