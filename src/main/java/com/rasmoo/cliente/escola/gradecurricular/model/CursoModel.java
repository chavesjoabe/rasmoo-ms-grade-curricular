package com.rasmoo.cliente.escola.gradecurricular.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class CursoModel {
    private Long id;

    @NotBlank(message = "Favor, preencher o nome")
    private String nome;

    @NotBlank(message = "Favor, preencher o codigo do curso")
    private String codCurso;

    private List<Long> materias;
}
