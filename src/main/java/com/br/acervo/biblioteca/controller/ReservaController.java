package com.br.acervo.biblioteca.controller;

import com.br.acervo.biblioteca.dto.ReservaDto;
import com.br.acervo.biblioteca.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/{id}")
    public ResponseEntity <ReservaDto> reservao(@PathVariable @Valid Long id, @RequestParam @Valid String livroId){
        ReservaDto reserva = reservaService.reservinha(id, livroId);
        return ResponseEntity.ok(reserva);
    }
    @GetMapping
    public List<ReservaDto> getReservas(){
        return reservaService.getAllReservas();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservaDto> updatedReserva(@PathVariable @Valid Long id,
                                                     @RequestParam @Valid String usuarioId, @RequestParam @Valid String livroId){
        ReservaDto updateReserva = reservaService.updatedReserva(id, usuarioId, livroId);
        return ResponseEntity.ok(updateReserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReserva(@PathVariable @Valid Long id){
        reservaService.deleteReserva(id);
    }
    
}
