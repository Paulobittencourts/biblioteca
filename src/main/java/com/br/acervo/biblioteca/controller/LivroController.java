package com.br.acervo.biblioteca.controller;

import com.br.acervo.biblioteca.dto.LivroDto;
import com.br.acervo.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {


    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<LivroDto> buscarTodos() {
        return livroService.getLivros();
    }

    @GetMapping("/{id}")
    public List<LivroDto> buscarPorID(@PathVariable @Valid Long id) {
        return livroService.getLivrosByID(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<LivroDto> criaLivro(@RequestBody @Valid LivroDto dto, @PathVariable @Valid Long id) {
        LivroDto create = livroService.incluiLivro(id, dto);
        return ResponseEntity.ok(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> update(@RequestBody @Valid LivroDto dto, @PathVariable @Valid Long id){
        LivroDto updated = livroService.updateLivro(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivro(@PathVariable @Valid Long id) {
        livroService.deleteLivro(id);
    }
}
