package com.duoc.msordenesmascotas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompra {
    private int id;
    private String cliente;
    private String producto;
    private int cantidad;
    private double precio;
    private String estado;
}
