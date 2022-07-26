package com.example.projeto.resources;

import com.example.projeto.model.PessoaFilter;
import com.example.projeto.model.input.PessoaInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.projeto.model.Service.PessoaService;
import com.example.projeto.model.entity.Pessoa;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pessoas")
@Api(value = "API pessoas")
public class PessoaResource {
    
    @Autowired private PessoaService service;

    @ApiOperation(value = "retorna lista não paginada de pessoas")
    @GetMapping("/listAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @ApiOperation(value = "retorna lista paginada de pessoas")
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenacao dos registros") })
    public ResponseEntity<?> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                         @ApiIgnore Pageable pageable, PessoaFilter filter){
        return ResponseEntity.ok(service.findAll(pageable, filter));
    }

    @ApiOperation(value = "salva uma nova pessoa")
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid PessoaInput pessoaInput) {
        return ResponseEntity.ok(service.post(pessoaInput));
    }

    @ApiOperation(value = "atualiza uma pessoas")
    @PutMapping
    public ResponseEntity<?> put(@RequestBody @Valid PessoaInput pessoaInput) {
        return ResponseEntity.ok(service.put(pessoaInput));
    }

    @ApiOperation(value = "deleta uma pessoa por id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
       service.deleteById(id);
    }


}
