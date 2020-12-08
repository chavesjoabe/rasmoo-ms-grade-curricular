package com.rasmoo.cliente.escola.gradecurricular.controler;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repositories.IMateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaControler {

    @Autowired
    private IMateriaRepository materiaRepository;
    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listarMaterias(){
        return ResponseEntity.ok().body(this.materiaService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> buscarMateria(@PathVariable Long id){
        return ResponseEntity.ok().body(this.materiaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia){
        return ResponseEntity.ok().body(this.materiaService.cadastrar(materia));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id){
        return ResponseEntity.ok().body(this.materiaService.excluir(id));
    }

    @PutMapping
    public ResponseEntity<Boolean> atualizarMateria(@RequestBody MateriaEntity materia){
        return ResponseEntity.ok().body(this.materiaService.atualizar(materia));
    }

}
