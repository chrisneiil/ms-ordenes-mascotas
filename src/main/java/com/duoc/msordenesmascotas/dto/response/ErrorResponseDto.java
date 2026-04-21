package com.duoc.msordenesmascotas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Object details;

}