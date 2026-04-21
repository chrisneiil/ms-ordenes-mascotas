package com.duoc.msordenesmascotas.service;

import com.duoc.msordenesmascotas.dto.request.OrdenRequestDto;
import com.duoc.msordenesmascotas.dto.response.MensajeResponseDto;
import com.duoc.msordenesmascotas.dto.response.OrdenResponseDto;

import java.util.List;

public interface OrdenService {

    List<OrdenResponseDto> obtenerOrdenes();

    OrdenResponseDto buscarPorId(Long id);

    List<OrdenResponseDto> buscarPorEstado(String estado);

    MensajeResponseDto crearOrden(OrdenRequestDto nuevaOrden);

    MensajeResponseDto actualizarOrden(Long id, OrdenRequestDto ordenActualizada);

    MensajeResponseDto eliminarOrden(Long id);
}