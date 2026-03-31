package com.duoc.msordenesmascotas.service;

import com.duoc.msordenesmascotas.model.OrdenCompra;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenService {
    private List<OrdenCompra> ordenes = new ArrayList<>();

    public OrdenService() {
        ordenes.add(new OrdenCompra(1, "Ana", "Alimento para perro", 2, 15000, "PENDIENTE"));
        ordenes.add(new OrdenCompra(2, "Luis", "Arena para gato", 1, 12000, "PAGADA"));
        ordenes.add(new OrdenCompra(3, "Camila", "Juguete para gato", 3, 5000, "ENVIADA"));
        ordenes.add(new OrdenCompra(4, "Pedro", "Shampoo canino", 1, 8000, "PENDIENTE"));
        ordenes.add(new OrdenCompra(5, "Maria", "Collar antipulgas", 2, 9500, "ENTREGADA"));
        ordenes.add(new OrdenCompra(6, "Diego", "Cama para perro", 1, 25000, "CANCELADA"));
        ordenes.add(new OrdenCompra(7, "Fernanda", "Snack dental", 4, 3000, "PAGADA"));
        ordenes.add(new OrdenCompra(8, "Javiera", "Correa", 1, 7000, "PENDIENTE"));
    }

    public List<OrdenCompra> obtenerOrdenes() {
        return ordenes;
    }

    public OrdenCompra buscarPorId(int id) {
        for (OrdenCompra orden : ordenes) {
            if (orden.getId() == id) {
                return orden;
            }
        }
        return null;
    }

    public List<OrdenCompra> buscarPorEstado(String estado) {
        List<OrdenCompra> resultado = new ArrayList<>();

        for (OrdenCompra orden : ordenes) {
            if (orden.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(orden);
            }
        }

        return resultado;
    }

    public String crearOrden(OrdenCompra nuevaOrden) {
        if (nuevaOrden.getId() <= 0) {
            return "Error: el id debe ser mayor a 0";
        }

        if (buscarPorId(nuevaOrden.getId()) != null) {
            return "Error: ya existe una orden con ese id";
        }

        if (nuevaOrden.getCliente() == null || nuevaOrden.getCliente().trim().isEmpty()) {
            return "Error: el cliente no puede estar vacío";
        }

        if (nuevaOrden.getProducto() == null || nuevaOrden.getProducto().trim().isEmpty()) {
            return "Error: el producto no puede estar vacío";
        }

        if (nuevaOrden.getCantidad() <= 0) {
            return "Error: la cantidad debe ser mayor a 0";
        }

        if (nuevaOrden.getPrecio() <= 0) {
            return "Error: el precio debe ser mayor a 0";
        }

        if (nuevaOrden.getEstado() == null || nuevaOrden.getEstado().trim().isEmpty()) {
            return "Error: el estado no puede estar vacío";
        }

        ordenes.add(nuevaOrden);
        return "Orden creada correctamente";
    }

    public String actualizarOrden(int id, OrdenCompra ordenActualizada) {
        OrdenCompra ordenEncontrada = buscarPorId(id);

        if (ordenEncontrada == null) {
            return "Error: orden no encontrada";
        }

        if (ordenActualizada.getCliente() == null || ordenActualizada.getCliente().trim().isEmpty()) {
            return "Error: el cliente no puede estar vacío";
        }

        if (ordenActualizada.getProducto() == null || ordenActualizada.getProducto().trim().isEmpty()) {
            return "Error: el producto no puede estar vacío";
        }

        if (ordenActualizada.getCantidad() <= 0) {
            return "Error: la cantidad debe ser mayor a 0";
        }

        if (ordenActualizada.getPrecio() <= 0) {
            return "Error: el precio debe ser mayor a 0";
        }

        if (ordenActualizada.getEstado() == null || ordenActualizada.getEstado().trim().isEmpty()) {
            return "Error: el estado no puede estar vacío";
        }

        ordenEncontrada.setCliente(ordenActualizada.getCliente());
        ordenEncontrada.setProducto(ordenActualizada.getProducto());
        ordenEncontrada.setCantidad(ordenActualizada.getCantidad());
        ordenEncontrada.setPrecio(ordenActualizada.getPrecio());
        ordenEncontrada.setEstado(ordenActualizada.getEstado());

        return "Orden actualizada correctamente";
    }

    public String eliminarOrden(int id) {
        OrdenCompra ordenEncontrada = buscarPorId(id);

        if (ordenEncontrada == null) {
            return "Error: orden no encontrada";
        }

        ordenes.remove(ordenEncontrada);
        return "Orden eliminada correctamente";
    }
}
