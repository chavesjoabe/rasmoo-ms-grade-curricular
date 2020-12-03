package com.rasmoo.cliente.escola.gradecurricular.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materia")
public class MateriaControler {

    @GetMapping("/")
    public ResponseEntity<String> helloWorldRest(){

        String saudacao = "Ola mundo REST";
        return ResponseEntity.ok().body(saudacao);
    }
}
