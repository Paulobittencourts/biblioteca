package com.br.acervo.biblioteca.controller;

import com.br.acervo.biblioteca.dto.ReservaDto;
import com.br.acervo.biblioteca.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/{id}")
    public ResponseEntity <ReservaDto> reservao(@PathVariable @Valid Long id, @RequestParam @Valid String reservaId){
        ReservaDto reserva = reservaService.reservinha(id, reservaId);
        return ResponseEntity.ok(reserva);
    }
    
}
