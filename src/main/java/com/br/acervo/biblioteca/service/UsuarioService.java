package com.br.acervo.biblioteca.service;

import com.br.acervo.biblioteca.dto.UsuarioDto;
import com.br.acervo.biblioteca.model.Status;
import com.br.acervo.biblioteca.model.UsuarioModel;
import com.br.acervo.biblioteca.repository.UsuarioRepositorio;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public UsuarioDto criacaoUsuario(UsuarioDto usuarioDto){
        UsuarioModel criaUsuario = modelMapper.map(usuarioDto, UsuarioModel.class);
        criaUsuario.setData(LocalDateTime.now());
        criaUsuario.getLivros().forEach(livro -> livro.setUsuarioModel(criaUsuario));
        criaUsuario.getLivros().forEach(livro -> livro.setStatus(Status.NAO_RESERVADO));
        userRepositorio.save(criaUsuario);
        return modelMapper.map(criaUsuario,UsuarioDto.class);
    }

    public void deleteUsuario(Long id) {
        UsuarioModel deleteUsuario = userRepositorio.findById(id)
                .orElseThrow(EntityExistsException::new);
        userRepositorio.delete(deleteUsuario);
    }

    public UsuarioDto updateUsuario (Long id, UsuarioDto usuarioDto){
        UsuarioModel updateUsuario = userRepositorio.findById(id)
                .orElseThrow(EntityExistsException::new);
        updateUsuario.setNome(usuarioDto.getNome());
        updateUsuario.setEmail(usuarioDto.getEmail());
        updateUsuario.setData(LocalDateTime.now());
        userRepositorio.save(updateUsuario);
        return modelMapper.map(updateUsuario,UsuarioDto.class);

    }
}
