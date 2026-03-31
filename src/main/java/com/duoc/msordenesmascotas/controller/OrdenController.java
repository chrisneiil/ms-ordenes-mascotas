package com.duoc.msordenesmascotas.controller;

import com.duoc.msordenesmascotas.model.OrdenCompra;
import com.duoc.msordenesmascotas.service.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping("/ordenes")
    public List<OrdenCompra> listarOrdenes() {
        return new ResponseEntity<>(ordenService.obtenerOrdenes(), HttpStatus.OK).getBody();
    }

    @GetMapping("/ordenes/{id}")
    public OrdenCompra obtenerOrdenPorId(@PathVariable int id) {
        return new ResponseEntity<>(ordenService.buscarPorId(id), HttpStatus.OK).getBody();
    }

    @GetMapping("/ordenes/estado/{estado}")
    public List<OrdenCompra> obtenerOrdenesPorEstado(@PathVariable String estado) {
        return new ResponseEntity<>(ordenService.buscarPorEstado(estado), HttpStatus.OK).getBody();
    }

    @PostMapping("/ordenes")
    public String crearOrden(@RequestBody OrdenCompra nuevaOrden) {
        return new ResponseEntity<>(ordenService.crearOrden(nuevaOrden), HttpStatus.OK).getBody();
    }

    @PutMapping("/ordenes/{id}")
    public String actualizarOrden(@PathVariable int id, @RequestBody OrdenCompra ordenActualizada) {
        return new ResponseEntity<>(ordenService.actualizarOrden(id, ordenActualizada), HttpStatus.OK).getBody();
    }

    @DeleteMapping("/ordenes/{id}")
    public String eliminarOrden(@PathVariable int id) {
        return new ResponseEntity<>(ordenService.eliminarOrden(id), HttpStatus.OK).getBody();
    }
}