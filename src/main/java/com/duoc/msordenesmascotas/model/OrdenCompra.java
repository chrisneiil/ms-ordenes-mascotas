package com.duoc.msordenesmascotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDEN_COMPRA")
public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_compra_seq_gen")
    @SequenceGenerator(
            name = "orden_compra_seq_gen",
            sequenceName = "SEQ_ORDEN_COMPRA",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLIENTE", nullable = false, length = 100)
    private String cliente;

    @Column(name = "PRODUCTO", nullable = false, length = 150)
    private String producto;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "PRECIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;
}
