package com.br.acervo.biblioteca.service;

import com.br.acervo.biblioteca.dto.ReservaDto;
import com.br.acervo.biblioteca.model.LivroModel;
import com.br.acervo.biblioteca.model.ReservaModel;
import com.br.acervo.biblioteca.model.UsuarioModel;
import com.br.acervo.biblioteca.repository.LivroRepository;
import com.br.acervo.biblioteca.repository.ReservaRepositorio;
import com.br.acervo.biblioteca.repository.UsuarioRepositorio;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ReservaDto reservinha(Long id, String reservaID) {
        ReservaModel reservaModel = new ReservaModel();
        UsuarioModel usuarioModel = usuarioRepositorio.findById(id).orElseThrow(EntityExistsException::new);
        LivroModel livroModel = livroRepository.findById(Long.parseLong(reservaID)).orElseThrow(EntityExistsException::new);

        reservaModel.setUsuario(usuarioModel);
        reservaModel.setLivro(livroModel);
        reservaRepository.save(reservaModel);

        return modelMapper.map(reservaModel, ReservaDto.class);


    }

}
