package com.duoc.msordenesmascotas.controller;

import com.duoc.msordenesmascotas.dto.request.OrdenRequestDto;
import com.duoc.msordenesmascotas.dto.response.MensajeResponseDto;
import com.duoc.msordenesmascotas.dto.response.OrdenResponseDto;
import com.duoc.msordenesmascotas.service.OrdenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<List<OrdenResponseDto>> listarOrdenes() {
        return ResponseEntity.ok(ordenService.obtenerOrdenes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenResponseDto> obtenerOrdenPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.buscarPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenResponseDto>> obtenerOrdenesPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(ordenService.buscarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<MensajeResponseDto> crearOrden(@Valid @RequestBody OrdenRequestDto nuevaOrden) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crearOrden(nuevaOrden));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponseDto> actualizarOrden(
            @PathVariable Long id,
            @Valid @RequestBody OrdenRequestDto ordenActualizada
    ) {
        return ResponseEntity.ok(ordenService.actualizarOrden(id, ordenActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponseDto> eliminarOrden(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.eliminarOrden(id));
    }
}