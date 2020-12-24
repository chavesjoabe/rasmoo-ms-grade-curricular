package com.rasmoo.cliente.escola.gradecurricular.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_curso")
public class CursoEntity implements Serializable {
    private static final long serialVersionUID = 8717247788621161955L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "nome")
    private String nome;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "cod")
    private String codigo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private List<MateriaEntity> materias;

}
