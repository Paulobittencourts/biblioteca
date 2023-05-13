package com.br.acervo.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {


    private Long id;

    private String nome;

    private String email;

    private LocalDateTime data;

    private List<LivroDto> livros = new ArrayList<>();

    private List<ReservaDto> reserva = new ArrayList<>();
}
