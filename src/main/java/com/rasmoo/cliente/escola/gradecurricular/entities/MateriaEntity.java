package com.rasmoo.cliente.escola.gradecurricular.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_materia")
public class MateriaEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)

    @Column(name = "nome")
    private String nome;

    @Column(name = "hrs")
    private int horas;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "cod")
    private String codigo;

    @Column(name = "freq")
    private int frequencia;
}
