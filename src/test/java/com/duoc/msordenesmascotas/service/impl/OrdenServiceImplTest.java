package com.duoc.msordenesmascotas.service.impl;

import com.duoc.msordenesmascotas.dto.request.OrdenRequestDto;
import com.duoc.msordenesmascotas.dto.response.MensajeResponseDto;
import com.duoc.msordenesmascotas.dto.response.OrdenResponseDto;
import com.duoc.msordenesmascotas.model.OrdenCompra;
import com.duoc.msordenesmascotas.repository.OrdenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrdenServiceImplTest {

    @Mock
    private OrdenRepository ordenRepository;

    @InjectMocks
    private OrdenServiceImpl ordenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deberiaObtenerOrdenes() {
        OrdenCompra orden1 = new OrdenCompra();
        orden1.setId(1L);
        orden1.setCliente("Ana");
        orden1.setProducto("Alimento");
        orden1.setCantidad(2);
        orden1.setPrecio(new BigDecimal("15000"));
        orden1.setEstado("PENDIENTE");

        OrdenCompra orden2 = new OrdenCompra();
        orden2.setId(2L);
        orden2.setCliente("Luis");
        orden2.setProducto("Arena");
        orden2.setCantidad(1);
        orden2.setPrecio(new BigDecimal("12000"));
        orden2.setEstado("PAGADA");

        when(ordenRepository.findAll()).thenReturn(List.of(orden1, orden2));

        List<OrdenResponseDto> resultado = ordenService.obtenerOrdenes();

        assertEquals(2, resultado.size());
        assertEquals("Ana", resultado.get(0).getCliente());
        assertEquals("PAGADA", resultado.get(1).getEstado());
        verify(ordenRepository, times(1)).findAll();
    }

    @Test
    void deberiaBuscarOrdenPorId() {
        OrdenCompra orden = new OrdenCompra();
        orden.setId(1L);
        orden.setCliente("Ana");
        orden.setProducto("Alimento");
        orden.setCantidad(2);
        orden.setPrecio(new BigDecimal("15000"));
        orden.setEstado("PENDIENTE");

        when(ordenRepository.findById(1L)).thenReturn(Optional.of(orden));

        OrdenResponseDto resultado = ordenService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getCliente());
        assertEquals("PENDIENTE", resultado.getEstado());
        verify(ordenRepository).findById(1L);
    }

    @Test
    void deberiaCrearOrden() {
        OrdenRequestDto request = new OrdenRequestDto();
        request.setCliente("Carlos");
        request.setProducto("Pelota");
        request.setCantidad(2);
        request.setPrecio(new BigDecimal("4500"));
        request.setEstado("PENDIENTE");

        OrdenCompra guardada = new OrdenCompra();
        guardada.setId(9L);
        guardada.setCliente("Carlos");
        guardada.setProducto("Pelota");
        guardada.setCantidad(2);
        guardada.setPrecio(new BigDecimal("4500"));
        guardada.setEstado("PENDIENTE");

        when(ordenRepository.save(any(OrdenCompra.class))).thenReturn(guardada);

        MensajeResponseDto resultado = ordenService.crearOrden(request);

        assertNotNull(resultado);
        assertEquals("Orden creada correctamente con id: 9", resultado.getMensaje());
        verify(ordenRepository).save(any(OrdenCompra.class));
    }

    @Test
    void deberiaLanzarNoSuchElementExceptionCuandoNoExisteOrden() {
        when(ordenRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> ordenService.buscarPorId(99L));

        verify(ordenRepository).findById(99L);
    }
}