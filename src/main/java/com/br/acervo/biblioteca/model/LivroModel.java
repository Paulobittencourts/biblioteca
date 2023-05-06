package com.br.acervo.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private String autor;

    private String anoDeLancamento;
    @NotNull
    private Status status;
    @NotNull
    private Long quantidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuarioModel;


}
