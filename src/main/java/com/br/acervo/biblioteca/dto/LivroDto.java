package com.br.acervo.biblioteca.dto;

import com.br.acervo.biblioteca.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {


    private Long id;

    private String nome;

    private String autor;

    private String anoDeLancamento;

    private Status status;

    private  Long quantidade;

}
