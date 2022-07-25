package com.example.projeto.model.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projeto.model.Repository.PessoaRepository;
import com.example.projeto.model.entity.Pessoa;

@Service
public class PessoaService {
    
    @Autowired private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa post(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
