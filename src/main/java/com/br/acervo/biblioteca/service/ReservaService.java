package com.br.acervo.biblioteca.service;

import javax.swing.text.html.parser.Entity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.acervo.biblioteca.dto.ReservaDto;
import com.br.acervo.biblioteca.model.LivroModel;
import com.br.acervo.biblioteca.model.ReservaModel;
import com.br.acervo.biblioteca.model.UsuarioModel;
import com.br.acervo.biblioteca.repository.LivroRepository;
import com.br.acervo.biblioteca.repository.ReservaRepositorio;
import com.br.acervo.biblioteca.repository.UsuarioRepositorio;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {

    @Autowired 
    private LivroRepository livroRepository;

    @Autowired 
    private ReservaRepositorio reservaRepository;

    @Autowired 
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    public ReservaDto reservinha(long id, ReservaDto reservaDto){
        ReservaModel reservaModel = modelMapper.map(reservaDto, ReservaModel.class);
        UsuarioModel usuarioModel = usuarioRepositorio.findById(id).orElseThrow(EntityExistsException::new);
        LivroModel livroModel = livroRepository.findById(reservaDto.getLivro()).orElseThrow(EntityExistsException::new);

        reservaModel.setUsuario(usuarioModel);
        reservaModel.setLivro(livroModel);
        reservaRepository.save(reservaModel);

        return modelMapper.map(livroModel, ReservaDto.class);


    }
    
}
