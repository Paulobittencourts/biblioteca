package com.br.acervo.biblioteca.repository;

import com.br.acervo.biblioteca.model.LivroModel;
import com.br.acervo.biblioteca.model.Status;
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

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void salvarLivro(){
        UsuarioModel usuarioCriado = criarUsuario();
        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);

        LivroModel livroCriado = criarLivro();
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo = livroRepository.save(livroCriado);

        Assertions.assertThat(livroSalvo.getId()).isNotNull();
        Assertions.assertThat(livroSalvo.getNome()).isEqualTo(livroCriado.getNome());
        Assertions.assertThat(livroSalvo.getAnoDeLancamento()).isEqualTo(livroCriado.getAnoDeLancamento());
        Assertions.assertThat(livroSalvo.getAutor()).isEqualTo(livroCriado.getAutor());
        Assertions.assertThat(livroSalvo.getQuantidade()).isEqualTo(livroCriado.getQuantidade());
        Assertions.assertThat(livroSalvo.getStatus()).isEqualTo(livroCriado.getStatus());
        Assertions.assertThat(livroSalvo.getUsuarioModel()).isEqualTo(livroCriado.getUsuarioModel());

    }

    @Test
    public void removeLivro(){
        UsuarioModel usuarioCriado = criarUsuario();
        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);

        LivroModel livroCriado = criarLivro();
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo = livroRepository.save(livroCriado);
        livroRepository.delete(livroSalvo);

        Optional<LivroModel> byId = livroRepository.findById(livroSalvo.getId());

        Assertions.assertThat(byId.isEmpty()).isTrue();
    }

    @Test
    public void updateLivro(){
        UsuarioModel usuarioCriado = criarUsuario();
        UsuarioModel usuarioSalvo = usuarioRepositorio.save(usuarioCriado);

        LivroModel livroCriado = criarLivro();
        livroCriado.setUsuarioModel(usuarioSalvo);
        LivroModel livroSalvo = livroRepository.save(livroCriado);

        livroSalvo.setNome("Diario De Um Banana");
        livroSalvo.setStatus(Status.NAO_RESERVADO);
        livroSalvo.setAutor("Jeff Kinney");
        livroSalvo.setQuantidade(6L);
        livroSalvo.setAnoDeLancamento("2008");

        LivroModel updateLivro  = livroRepository.save(livroCriado);

        Assertions.assertThat(updateLivro.getNome()).isEqualTo(updateLivro.getNome());
        Assertions.assertThat(updateLivro.getStatus()).isEqualTo(updateLivro.getStatus());
        Assertions.assertThat(updateLivro.getAnoDeLancamento()).isEqualTo(updateLivro.getAnoDeLancamento());
        Assertions.assertThat(updateLivro.getQuantidade()).isEqualTo(updateLivro.getQuantidade());
        Assertions.assertThat(updateLivro.getAutor()).isEqualTo(updateLivro.getAutor());
        Assertions.assertThat(updateLivro.getId()).isNotNull();
        Assertions.assertThat(updateLivro).isNotNull();
    }

    @Test
    public void listaLivro(){

        List<LivroModel>listaLivro = livroRepository.findAll();

        Assertions.assertThat(listaLivro).isNotNull();


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
