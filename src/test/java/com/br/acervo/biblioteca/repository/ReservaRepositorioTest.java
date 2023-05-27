package com.br.acervo.biblioteca.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.br.acervo.biblioteca.model.LivroModel;
import com.br.acervo.biblioteca.model.ReservaModel;
import com.br.acervo.biblioteca.model.Status;
import com.br.acervo.biblioteca.model.UsuarioModel;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReservaRepositorioTest {


    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ReservaRepositorio reservaRepositorio;


    @Test
    public void criarReseva(){
        UsuarioModel usuarioCriado = criarUsuario();
        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);
        UsuarioModel usuarioCriado2 = criarUsuario();
        UsuarioModel usuarioSalvo2 = usuarioRepositorio.save(usuarioCriado2);

        LivroModel livroCriado = criarLivro();
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo = livroRepository.save(livroCriado);

        ReservaModel reserva = new ReservaModel();
        reserva.setLivro(livroSalvo);
        reserva.setUsuario(usuarioSalvo2);
        reservaRepositorio.save(reserva);

        Assertions.assertThat(reserva.getLivro()).isEqualTo(livroSalvo);
        Assertions.assertThat(reserva.getUsuario()).isEqualTo(usuarioSalvo2);
        Assertions.assertThat((reserva.getId())).isNotNull();
        Assertions.assertThat(reserva).isNotNull();

    }

    @Test
    public void deletarReserva(){

        UsuarioModel usuarioCriado = criarUsuario();
        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);
        UsuarioModel usuarioCriado2 = criarUsuario();
        UsuarioModel usuarioSalvo2 = usuarioRepositorio.save(usuarioCriado2);

        LivroModel livroCriado = criarLivro();
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo = livroRepository.save(livroCriado);

        ReservaModel reserva = new ReservaModel();
        reserva.setLivro(livroSalvo);
        reserva.setUsuario(usuarioSalvo2);
        reservaRepositorio.save(reserva);

        reservaRepositorio.delete(reserva);

        Optional<ReservaModel> byId = reservaRepositorio.findById(reserva.getId());

        Assertions.assertThat(byId.isEmpty()).isTrue();



    }

    @Test
    public void listarReserva(){

        List<ReservaModel>listaReserva = reservaRepositorio.findAll();

        Assertions.assertThat(listaReserva).isNotNull();

    }

    @Test
    public void updateReserva(){
        UsuarioModel usuarioCriado = criarUsuario();
        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);

        UsuarioModel usuarioCriado2 = criarUsuario();
        UsuarioModel usuarioSalvo2 = usuarioRepositorio.save(usuarioCriado2);

        LivroModel livroCriado = criarLivro();
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo = livroRepository.save(livroCriado);

        
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo2 = livroRepository.save(livroCriado);

        ReservaModel reserva = new ReservaModel();
        reserva.setLivro(livroSalvo);
        reserva.setUsuario(usuarioSalvo2);
        reservaRepositorio.save(reserva);

        reserva.setLivro(livroSalvo2);
        reserva.setUsuario(usuarioSalvo2);
        reservaRepositorio.save(reserva);

        Assertions.assertThat(reserva.getLivro()).isEqualTo(livroSalvo2);
        Assertions.assertThat(reserva.getUsuario()).isEqualTo(usuarioSalvo2);
        Assertions.assertThat((reserva.getId())).isNotNull();
        Assertions.assertThat(reserva).isNotNull();

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

    public LivroModel criarLivro() {
        return LivroModel.builder()
                .nome("Extraordinário")
                .anoDeLancamento("2012")
                .autor("R.J. Palacio")
                .quantidade(1L)
                .status(Status.NAO_RESERVADO)
                .build();
    }


}



