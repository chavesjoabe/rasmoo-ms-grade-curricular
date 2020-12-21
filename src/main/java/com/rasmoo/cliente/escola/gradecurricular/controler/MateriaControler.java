package com.rasmoo.cliente.escola.gradecurricular.controler;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.service.MateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaControler {

    private static final String DELETE = "DELETE";
    private static final String UPDATE = "UPDATE";
    private static final String LISTAR = "LISTAR";
    private static final String BUSCAR_POR_ID = "BUSCAR POR ID";

    private MateriaDto materia;


    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> listarMaterias(){
        Response<List<MateriaDto>> response = new Response<>();
        response.setData(this.materiaService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                .listarMaterias()).withSelfRel());
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaEntity>> buscarMateria(@PathVariable Long id){
        Response<MateriaEntity> response = new Response<>();
        response.setData(this.materiaService.buscarPorId(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                        .buscarMateria(id)).withSelfRel());
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                        .deletarMateria(id)).withRel(DELETE));
        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                        .atualizarMateria(materia)).withRel(UPDATE));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrarMateria(@Valid @RequestBody MateriaDto materia){
        Response<Boolean> response = new Response();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(this.materiaService.cadastrar(materia));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                .atualizarMateria(materia)).withRel(UPDATE));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deletarMateria(@PathVariable Long id){
        Response<Boolean> response = new Response();
        response.setData(this.materiaService.excluir(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                .listarMaterias()).withRel(LISTAR));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizarMateria(@Valid @RequestBody MateriaDto materia){
        Response<Boolean> response = new Response();
        response.setData(this.materiaService.atualizar(materia));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                .listarMaterias()).withRel(LISTAR));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaControler.class)
                .buscarMateria(materia.getId())).withRel(BUSCAR_POR_ID));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/hora-minima/{horaMinima}")
    public ResponseEntity<Response<List<MateriaEntity>>> listarPorHoraMinima(@PathVariable int horaMinima){
        Response<List<MateriaEntity>> response = new Response();
        response.setData(this.materiaService.listarPorHoraMinima(horaMinima));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
