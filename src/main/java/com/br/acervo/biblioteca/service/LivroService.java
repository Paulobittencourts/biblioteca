package com.br.acervo.biblioteca.service;

import com.br.acervo.biblioteca.dto.LivroDto;
import com.br.acervo.biblioteca.model.LivroModel;
import com.br.acervo.biblioteca.model.Status;
import com.br.acervo.biblioteca.model.UsuarioModel;
import com.br.acervo.biblioteca.repository.LivroRepository;
import com.br.acervo.biblioteca.repository.UsuarioRepositorio;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    public List<LivroDto> getLivros() {
        return livroRepository.findAll().stream()
                .map(livro -> modelMapper.map(livro, LivroDto.class)).toList();
    }

    public List<LivroDto> getLivrosByID(Long id) {
        return livroRepository.findById(id).stream()
                .map(livro -> modelMapper.map(livro, LivroDto.class)).toList();
    }

    public LivroDto incluiLivro(Long id, LivroDto livroDto) {
        LivroModel criaLivro = modelMapper.map(livroDto, LivroModel.class);
        UsuarioModel usuarioModel = usuarioRepositorio.findById(id)
                .orElseThrow(EntityExistsException::new);

        criaLivro.setStatus(Status.NAO_RESERVADO);
        criaLivro.setUsuarioModel(usuarioModel);
        livroRepository.save(criaLivro);

        return modelMapper.map(criaLivro, LivroDto.class);
    }

    public void deleteLivro(Long id) {
        LivroModel delete = livroRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        livroRepository.delete(delete);
    }

    public LivroDto updateLivro(Long id, LivroDto livroDto) {
        LivroModel update = livroRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        update.setNome(livroDto.getNome());
        update.setAutor(livroDto.getAutor());
        update.setStatus(Status.NAO_RESERVADO);
        update.setAnoDeLancamento(livroDto.getAnoDeLancamento());
        update.setQuantidade(livroDto.getQuantidade());

        livroRepository.save(update);
        return modelMapper.map(update, LivroDto.class);

    }
}
