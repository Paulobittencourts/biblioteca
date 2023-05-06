package com.br.acervo.biblioteca.service;

import com.br.acervo.biblioteca.dto.UsuarioDto;
import com.br.acervo.biblioteca.model.UsuarioModel;
import com.br.acervo.biblioteca.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio userRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    public List<UsuarioDto> getUsuarios(){
        return userRepositorio.findAll().stream()
                .map(user->modelMapper.map(user, UsuarioDto.class)).toList();
    }
}
