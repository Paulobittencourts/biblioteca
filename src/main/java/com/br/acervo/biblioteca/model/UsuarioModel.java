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
    @Column(unique = true)
    private String email;
    @NotNull
    private LocalDateTime data;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "usuarioModel")
    private List<LivroModel> livros = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "usuario")
    private List<ReservaModel> reserva = new ArrayList<>();

}
