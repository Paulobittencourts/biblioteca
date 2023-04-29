package com.br.acervo.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private LocalDateTime dataDeNascimento;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "usuarioModel")
    private List<LivroModel> livroModelList = new ArrayList<>();



}
