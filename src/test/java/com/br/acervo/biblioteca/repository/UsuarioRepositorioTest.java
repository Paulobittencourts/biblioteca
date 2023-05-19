package com.br.acervo.biblioteca.repository;

import com.br.acervo.biblioteca.model.UsuarioModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Test
    public void removeUsuario(){
        UsuarioModel usuarioCriado = criarUsuario();

        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);
        usuarioRepositorio.delete(usuarioSalvo);

        Optional<UsuarioModel> byId = usuarioRepositorio.findById(usuarioSalvo.getId());

        Assertions.assertThat(byId.isEmpty()).isTrue();
    }

    @Test
    public void updateUsuario(){
        UsuarioModel usuarioCriado = criarUsuario();

        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);
        usuarioSalvo.setNome("Talita");
        usuarioSalvo.setEmail("talita@gmail.com");
        usuarioSalvo.setData(LocalDateTime.now());

        UsuarioModel usuarioUpdate = usuarioRepositorio.save(usuarioCriado);

        Assertions.assertThat(usuarioUpdate.getNome()).isEqualTo(usuarioSalvo.getNome());
        Assertions.assertThat(usuarioUpdate.getEmail()).isEqualTo(usuarioSalvo.getEmail());
        Assertions.assertThat(usuarioUpdate.getData()).isEqualTo(usuarioSalvo.getData());
        Assertions.assertThat(usuarioUpdate.getId()).isNotNull();
        Assertions.assertThat(usuarioUpdate).isNotNull();



    }

    @Test
    public void listarUsuario(){
        List<UsuarioModel>listaUsuario = usuarioRepositorio.findAll();

        Assertions.assertThat(listaUsuario).isNotNull();
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