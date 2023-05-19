package com.br.acervo.biblioteca.repository;

import com.br.acervo.biblioteca.model.UsuarioModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositorioTest {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void salvaUsuario(){
        UsuarioModel usuarioCriado = criarUsuario();

        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);

        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
        Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo(usuarioCriado.getNome());
        Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioCriado.getEmail());
        Assertions.assertThat(usuarioSalvo.getLivros()).isEqualTo(usuarioCriado.getLivros());
        Assertions.assertThat(usuarioSalvo.getReserva()).isEqualTo(usuarioCriado.getReserva());

    }

    public UsuarioModel criarUsuario(){
        return UsuarioModel.builder()
                .nome("Paulo")
                .email("phbs@gmail.com")
                .data(LocalDateTime.now())
                .livros(new ArrayList<>())
                .reserva(new ArrayList<>())
                .build();
    }


}