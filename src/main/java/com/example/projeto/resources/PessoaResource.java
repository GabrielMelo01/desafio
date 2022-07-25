package com.example.projeto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.model.Service.PessoaService;
import com.example.projeto.model.entity.Pessoa;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
    
    @Autowired private PessoaService service;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Pessoa pessoas){
        return ResponseEntity.ok(service.post(pessoas));
    }

    

}
