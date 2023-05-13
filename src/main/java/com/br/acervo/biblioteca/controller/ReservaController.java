package com.br.acervo.biblioteca.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.acervo.biblioteca.dto.ReservaDto;
import com.br.acervo.biblioteca.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/{id}")
    public ResponseEntity <ReservaDto> reservao(@PathVariable @Valid long id,@RequestBody @Valid ReservaDto reservaDto){
        ReservaDto reserva = reservaService.reservinha(id, reservaDto);
        return ResponseEntity.ok(reserva);
    }
    
}
