package com.rasmoo.cliente.escola.gradecurricular.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MateriaDto {

    private Long id;
    @NotBlank(message = "Por favor preencha o campo de nome")
    private String nome;

    @Min(value = 32, message = "valor minimo de 32 horas")
    @Max(value = 120, message = "Valor maximo de 120 horas")
    private int horas;
    @NotBlank(message = "Por favor preencha o campo de codigo")
    private String codigo;
    @Min(value = 1, message = "frequencia minima de 1")
    @Max(value = 2, message = "frequencia maxima de 2")
    private int frequencia;
}
