package com.rasmoo.cliente.escola.gradecurricular.controler;

import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.model.CursoModel;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoControler {

    @Autowired
    private CursoService service;

    @Autowired
    private IMateriaRepository materiaRepository;

    @GetMapping
    public ResponseEntity<Response<List<CursoEntity>>> listar(){
        Response<List<CursoEntity>> response  = new Response();
        response.setData(this.service.listar());
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{codCurso}")
    public ResponseEntity<Response<CursoEntity>> buscarPorCodigo(@PathVariable String codCurso){
        Response<CursoEntity> response = new Response<>();
        response.setData(this.service.buscarPorCodigo(codCurso));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<Boolean>> cadastrar(@Valid @RequestBody CursoModel curso){
        Response<Boolean> response = new Response<>();
        response.setData(service.cadastrar(curso));
        response.setStatusCode(HttpStatus.CREATED.value());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Boolean>> atualizar(@RequestBody CursoModel curso){
        Response<Boolean> response = new Response<>();
        response.setData(this.service.atualizar(curso));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable Long id){
        Response<Boolean> response = new Response<>();
        response.setData(this.service.deletar(id));
        response.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
