package com.br.acervo.biblioteca.dto;

import com.br.acervo.biblioteca.model.Status;
import com.br.acervo.biblioteca.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {


    private Long id;

    private String nome;

    private String autor;

    private LocalDateTime anoDeLancamento;

    private Status status;

    private  Long quantidade;

}
