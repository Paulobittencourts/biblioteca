package com.br.acervo.biblioteca.controller;

import com.br.acervo.biblioteca.dto.UsuarioDto;
import com.br.acervo.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        UsuarioDto create = userService.criacaoUsuario(usuarioDto);
        return ResponseEntity.ok(create);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteUsuario (@PathVariable @Valid Long id){
        userService.deleteUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@RequestBody @Valid UsuarioDto usuarioDto, @PathVariable @Valid Long id){
        UsuarioDto updated = userService.updateUsuario(id, usuarioDto);
        return ResponseEntity.ok(updated);
    }


}

