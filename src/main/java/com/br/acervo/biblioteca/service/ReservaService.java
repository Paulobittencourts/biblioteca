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

import java.util.List;

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

    public List<ReservaDto> getAllReservas() {
        return reservaRepository.findAll()
                .stream()
                .map(reserva -> modelMapper.map(reserva, ReservaDto.class))
                .toList();
    }

    public void deleteReserva(Long id) {
        ReservaModel reserva = reservaRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        reservaRepository.delete(reserva);
    }

    public ReservaDto updatedReserva(Long id, String usuarioId, String livroId) {
        ReservaModel updateReserva = reservaRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        UsuarioModel usuario = usuarioRepositorio.findById(Long.valueOf(usuarioId))
                .orElseThrow(EntityExistsException::new);
        LivroModel livro = livroRepository.findById(Long.parseLong(livroId))
                .orElseThrow(EntityExistsException::new);

        updateReserva.setUsuario(usuario);
        updateReserva.setLivro(livro);
        reservaRepository.save(updateReserva);

        return modelMapper.map(updateReserva, ReservaDto.class);
    }
}
