package com.duoc.msordenesmascotas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenRequestDto {

    @NotBlank(message = "El cliente es obligatorio")
    @Size(max = 100, message = "El cliente no puede superar 100 caracteres")
    private String cliente;

    @NotBlank(message = "El producto es obligatorio")
    @Size(max = 150, message = "El producto no puede superar 150 caracteres")
    private String producto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(
            regexp = "^(?i)(PENDIENTE|PAGADA|ENVIADA|ENTREGADA|CANCELADA)$",
            message = "El estado debe ser PENDIENTE, PAGADA, ENVIADA, ENTREGADA o CANCELADA"
    )
    private String estado;


}