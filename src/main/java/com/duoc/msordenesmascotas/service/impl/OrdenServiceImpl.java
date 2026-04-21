package com.duoc.msordenesmascotas.service.impl;

import com.duoc.msordenesmascotas.dto.request.OrdenRequestDto;
import com.duoc.msordenesmascotas.dto.response.MensajeResponseDto;
import com.duoc.msordenesmascotas.dto.response.OrdenResponseDto;
import com.duoc.msordenesmascotas.model.OrdenCompra;
import com.duoc.msordenesmascotas.repository.OrdenRepository;
import com.duoc.msordenesmascotas.service.OrdenService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;

    public OrdenServiceImpl(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    @Override
    public List<OrdenResponseDto> obtenerOrdenes() {
        return ordenRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrdenResponseDto buscarPorId(Long id) {
        OrdenCompra orden = ordenRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Orden no encontrada"));

        return mapToResponseDto(orden);
    }

    @Override
    public List<OrdenResponseDto> buscarPorEstado(String estado) {
        String estadoNormalizado = normalizarEstado(estado);

        return ordenRepository.findByEstadoIgnoreCase(estadoNormalizado)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MensajeResponseDto crearOrden(OrdenRequestDto nuevaOrden) {
        OrdenCompra orden = new OrdenCompra();
        orden.setCliente(nuevaOrden.getCliente().trim());
        orden.setProducto(nuevaOrden.getProducto().trim());
        orden.setCantidad(nuevaOrden.getCantidad());
        orden.setPrecio(nuevaOrden.getPrecio());
        orden.setEstado(normalizarEstado(nuevaOrden.getEstado()));

        OrdenCompra ordenGuardada = ordenRepository.save(orden);

        return new MensajeResponseDto("Orden creada correctamente con id: " + ordenGuardada.getId());
    }

    @Override
    public MensajeResponseDto actualizarOrden(Long id, OrdenRequestDto ordenActualizada) {
        OrdenCompra orden = ordenRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Orden no encontrada"));

        orden.setCliente(ordenActualizada.getCliente().trim());
        orden.setProducto(ordenActualizada.getProducto().trim());
        orden.setCantidad(ordenActualizada.getCantidad());
        orden.setPrecio(ordenActualizada.getPrecio());
        orden.setEstado(normalizarEstado(ordenActualizada.getEstado()));

        ordenRepository.save(orden);

        return new MensajeResponseDto("Orden actualizada correctamente");
    }

    @Override
    public MensajeResponseDto eliminarOrden(Long id) {
        OrdenCompra orden = ordenRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Orden no encontrada"));

        ordenRepository.delete(orden);

        return new MensajeResponseDto("Orden eliminada correctamente");
    }

    private OrdenResponseDto mapToResponseDto(OrdenCompra orden) {
        return new OrdenResponseDto(
                orden.getId(),
                orden.getCliente(),
                orden.getProducto(),
                orden.getCantidad(),
                orden.getPrecio(),
                orden.getEstado()
        );
    }

    private String normalizarEstado(String estado) {
        String valor = estado.trim().toUpperCase();

        if (!"PENDIENTE".equals(valor)
                && !"PAGADA".equals(valor)
                && !"ENVIADA".equals(valor)
                && !"ENTREGADA".equals(valor)
                && !"CANCELADA".equals(valor)) {
            throw new IllegalArgumentException("El estado debe ser PENDIENTE, PAGADA, ENVIADA, ENTREGADA o CANCELADA");
        }

        return valor;
    }
}