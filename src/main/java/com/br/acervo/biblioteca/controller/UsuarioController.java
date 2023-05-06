package com.br.acervo.biblioteca.controller;

import com.br.acervo.biblioteca.dto.UsuarioDto;
import com.br.acervo.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @GetMapping
    public List<UsuarioDto> buscarTodos() {
        return userService.getUsuarios();
    }

}